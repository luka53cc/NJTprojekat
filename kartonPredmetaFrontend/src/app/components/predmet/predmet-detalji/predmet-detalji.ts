import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Predmet } from '../../../models/predmet.model';
import { PredmetService } from '../../../services/predmet.service';
import { IstorijaIzmene } from '../../../models/istorija-izmene.model';
import { IstorijaIzmeneService } from '../../../services/istorija-izmene.service';


@Component({
  selector: 'app-predmet-detalji',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './predmet-detalji.html',
  styleUrl: './predmet-detalji.css'
})
export class PredmetDetaljiComponent implements OnInit {

  predmet?: Predmet;
  ucitavanje: boolean = true;
  greska: string = '';
  istorija: IstorijaIzmene[] = [];
prikaziIstoriju: boolean = false;
ucitavanjeIstorije: boolean = false;

  constructor(
    private service: PredmetService,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef,
    private istorijaService: IstorijaIzmeneService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.findById(id).subscribe({
      next: (podaci) => {
        this.predmet = podaci;
        this.ucitavanje = false;
        this.cdr.detectChanges(); // Odmah gasi loader i iscrtava detalje predmeta
      },
      error: (err) => {
        this.greska = 'Greška prilikom učitavanja predmeta';
        this.ucitavanje = false;
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }

  preuzmiPdf(): void {
    if (!this.predmet) {
      return;
    }
    this.service.downloadPdf(this.predmet.id).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `karton_${this.predmet!.sifra}.pdf`;
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
  
  prikaziIliSakrijIstoriju(): void {
  this.prikaziIstoriju = !this.prikaziIstoriju;

  if (this.prikaziIstoriju && this.istorija.length === 0 && this.predmet) {
    this.ucitavanjeIstorije = true;
    this.istorijaService.findByPredmet(this.predmet.id).subscribe({
      next: (podaci) => {
        this.istorija = podaci;
        this.ucitavanjeIstorije = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.ucitavanjeIstorije = false;
        console.error(err);
        this.cdr.detectChanges();
      }
    });
    }
  }
}