import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StudijskiProgram } from '../../../models/studijski-program.model';
import { StudijskiProgramService } from '../../../services/studijski-program.service';

@Component({
  selector: 'app-studijski-program-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './studijski-program-form.html',
  styleUrl: './studijski-program-form.css'
})
export class StudijskiProgramFormComponent implements OnInit {

  model: StudijskiProgram = { id: 0, naziv: '', skracenica: '' };
  izmena: boolean = false;
  greska: string = '';
  cuvanje: boolean = false;

  constructor(
    private service: StudijskiProgramService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.izmena = true;
      const id = Number(idParam);
      this.service.findById(id).subscribe({
        next: (podaci) => this.model = podaci,
        error: (err) => {
          this.greska = 'Greška prilikom učitavanja podataka';
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
        this.router.navigate(['/studijski-programi']);
      },
      error: (err) => {
        this.cuvanje = false;
        this.greska = err.error?.messages?.join(', ') || 'Greška prilikom čuvanja';
        console.error(err);
      }
    });
  }

  otkazi(): void {
    this.router.navigate(['/studijski-programi']);
  }
}