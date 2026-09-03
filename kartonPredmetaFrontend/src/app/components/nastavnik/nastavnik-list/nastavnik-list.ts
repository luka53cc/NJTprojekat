import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Nastavnik } from '../../../models/nastavnik.model';
import { NastavnikService } from '../../../services/nastavnik.service';
import { AuthService } from '../../../services/auth.service'; // Dodat import

@Component({
  selector: 'app-nastavnik-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './nastavnik-list.html',
  styleUrl: './nastavnik-list.css'
})
export class NastavnikListComponent implements OnInit {

  nastavnici: Nastavnik[] = [];
  ucitavanje: boolean = true;
  greska: string = '';

  constructor(
    private service: NastavnikService,
    public authService: AuthService, // Ubaceno ovde sa public
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.ucitaj();
  }

  ucitaj(): void {
    this.ucitavanje = true;
    this.service.findAll().subscribe({
      next: (podaci) => {
        this.nastavnici = podaci;
        this.ucitavanje = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.greska = 'Greška prilikom učitavanja nastavnika';
        this.ucitavanje = false;
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite da obrišete ovog nastavnika?')) {
      return;
    }
    this.service.delete(id).subscribe({
      next: () => this.ucitaj(),
      error: (err) => {
        this.greska = 'Greška prilikom brisanja (možda je nastavnik dodeljen nekom predmetu)';
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }
}