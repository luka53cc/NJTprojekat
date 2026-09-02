import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Modul } from '../../../models/modul.model';
import { StudijskiProgram } from '../../../models/studijski-program.model';
import { ModulService } from '../../../services/modul.service';
import { StudijskiProgramService } from '../../../services/studijski-program.service';

@Component({
  selector: 'app-modul-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modul-form.html',
  styleUrl: './modul-form.css'
})
export class ModulFormComponent implements OnInit {

  model: Modul = { id: 0, naziv: '', studijskiProgramId: 0 };
  studijskiProgrami: StudijskiProgram[] = [];
  izmena: boolean = false;
  greska: string = '';
  cuvanje: boolean = false;

  constructor(
    private service: ModulService,
    private studijskiProgramService: StudijskiProgramService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.studijskiProgramService.findAll().subscribe({
      next: (podaci) => {
        this.studijskiProgrami = podaci;
        this.cdr.detectChanges(); // Odmah osvežava prikaz da se pojave studijski programi u select-u
      },
      error: (err) => console.error(err)
    });

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.izmena = true;
      const id = Number(idParam);
      this.service.findById(id).subscribe({
        next: (podaci) => {
          this.model = podaci;
          this.cdr.detectChanges();
        },
        error: (err) => {
          this.greska = 'Greška prilikom učitavanja podataka';
          this.cdr.detectChanges();
          console.error(err);
        }
      });
    }
  }

  sacuvaj(): void {
    this.greska = '';
    this.cuvanje = true;

    const zahtev = this.izmena
      ? this.service.update(this.model.id, this.model)
      : this.service.add(this.model);

    zahtev.subscribe({
      next: () => {
        this.cuvanje = false;
        this.router.navigate(['/moduli']);
      },
      error: (err) => {
        this.cuvanje = false;
        this.greska = err.error?.messages?.join(', ') || 'Greška prilikom čuvanja';
        this.cdr.detectChanges();
        console.error(err);
      }
    });
  }

  otkazi(): void {
    this.router.navigate(['/moduli']);
  }
}