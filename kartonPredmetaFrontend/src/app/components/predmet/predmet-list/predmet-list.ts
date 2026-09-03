import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Predmet } from '../../../models/predmet.model';
import { StatusPredmeta } from '../../../models/status-predmeta.model';
import { StudijskiProgram } from '../../../models/studijski-program.model';
import { PredmetService } from '../../../services/predmet.service';
import { StudijskiProgramService } from '../../../services/studijski-program.service';
import { AuthService } from '../../../services/auth.service'; // Dodat import

@Component({
  selector: 'app-predmet-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './predmet-list.html',
  styleUrl: './predmet-list.css'
})
export class PredmetListComponent implements OnInit {

  predmeti: Predmet[] = [];
  studijskiProgrami: StudijskiProgram[] = [];
  statusi = Object.values(StatusPredmeta);

  // filteri
  naziv: string = '';
  sifra: string = '';
  godinaStudija?: number;
  semestar?: number;
  status?: string;
  studijskiProgramId?: number;

  // paginacija
  trenutnaStranica: number = 0;
  velicinaStranice: number = 10;
  ukupnoStranica: number = 0;
  ukupnoElemenata: number = 0;

  ucitavanje: boolean = true;
  greska: string = '';

  constructor(
    private service: PredmetService,
    private studijskiProgramService: StudijskiProgramService,
    public authService: AuthService, // Ubaceno ovde sa public da bi radilo u HTML-u
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.studijskiProgramService.findAll().subscribe({
      next: (podaci) => {
        this.studijskiProgrami = podaci;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
    this.pretrazi();
  }

  pretrazi(): void {
    this.ucitavanje = true;
    this.greska = '';

    this.service.pretrazi({
      naziv: this.naziv,
      sifra: this.sifra,
      godinaStudija: this.godinaStudija,
      semestar: this.semestar,
      status: this.status,
      studijskiProgramId: this.studijskiProgramId,
      page: this.trenutnaStranica,
      size: this.velicinaStranice,
      sort: 'naziv,asc'
    }).subscribe({
      next: (odgovor) => {
        this.predmeti = odgovor.content;
        this.ukupnoStranica = odgovor.totalPages;
        this.ukupnoElemenata = odgovor.totalElements;
        this.ucitavanje = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.greska = 'Greška prilikom učitavanja predmeta';
        this.ucitavanje = false;
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }

  primeniFiltere(): void {
    this.trenutnaStranica = 0;
    this.pretrazi();
  }

  ocistiFiltere(): void {
    this.naziv = '';
    this.sifra = '';
    this.godinaStudija = undefined;
    this.semestar = undefined;
    this.status = undefined;
    this.studijskiProgramId = undefined;
    this.trenutnaStranica = 0;
    this.pretrazi();
  }

  idiNaStranicu(stranica: number): void {
    if (stranica < 0 || stranica >= this.ukupnoStranica) {
      return;
    }
    this.trenutnaStranica = stranica;
    this.pretrazi();
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite da obrišete ovaj predmet?')) {
      return;
    }
    this.service.delete(id).subscribe({
      next: () => this.pretrazi(),
      error: (err) => {
        this.greska = 'Greška prilikom brisanja predmeta';
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }

  preuzmiPdf(id: number, sifra: string): void {
    this.service.downloadPdf(id).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `karton_${sifra}.pdf`;
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        this.greska = 'Greška prilikom preuzimanja PDF-a';
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }

  brojeviStranica(): number[] {
    return Array.from({ length: this.ukupnoStranica }, (_, i) => i);
  }
}