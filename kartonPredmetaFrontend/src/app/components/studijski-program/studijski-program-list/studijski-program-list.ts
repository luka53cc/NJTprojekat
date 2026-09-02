import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { StudijskiProgram } from '../../../models/studijski-program.model';
import { StudijskiProgramService } from '../../../services/studijski-program.service';

@Component({
  selector: 'app-studijski-program-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './studijski-program-list.html',
  styleUrl: './studijski-program-list.css'
})
export class StudijskiProgramListComponent implements OnInit {

  programi: StudijskiProgram[] = [];
  ucitavanje: boolean = true;
  greska: string = '';

  constructor(
    private service: StudijskiProgramService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.ucitaj();
  }

  ucitaj(): void {
    this.ucitavanje = true;
    this.service.findAll().subscribe({
      next: (podaci) => {
        this.programi = podaci;
        this.ucitavanje = false;
        this.cdr.detectChanges(); // Eksplicitno osvežava prikaz i gasi loader
      },
      error: (err) => {
        this.greska = 'Greška prilikom učitavanja studijskih programa';
        this.ucitavanje = false;
        this.cdr.detectChanges(); // Osvežava ekran i u slučaju greške
        console.error(err);
      }
    });
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite da obrišete ovaj studijski program?')) {
      return;
    }
    this.service.delete(id).subscribe({
      next: () => this.ucitaj(),
      error: (err) => {
        this.greska = 'Greška prilikom brisanja (možda postoje predmeti/moduli povezani sa ovim programom)';
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }
}