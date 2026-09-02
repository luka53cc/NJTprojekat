import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Modul } from '../../../models/modul.model';
import { ModulService } from '../../../services/modul.service';

@Component({
  selector: 'app-modul-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './modul-list.html',
  styleUrl: './modul-list.css'
})
export class ModulListComponent implements OnInit {

  moduli: Modul[] = [];
  ucitavanje: boolean = true;
  greska: string = '';

  constructor(private service: ModulService) {}

  ngOnInit(): void {
    this.ucitaj();
  }

  ucitaj(): void {
    this.ucitavanje = true;
    this.service.findAll().subscribe({
      next: (podaci) => {
        this.moduli = podaci;
        this.ucitavanje = false;
      },
      error: (err) => {
        this.greska = 'Greška prilikom učitavanja modula';
        this.ucitavanje = false;
        console.error(err);
      }
    });
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite da obrišete ovaj modul?')) {
      return;
    }
    this.service.delete(id).subscribe({
      next: () => this.ucitaj(),
      error: (err) => {
        this.greska = 'Greška prilikom brisanja (možda postoje predmeti povezani sa ovim modulom)';
        console.error(err);
      }
    });
  }
}