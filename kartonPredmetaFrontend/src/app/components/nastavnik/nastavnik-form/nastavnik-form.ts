import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Nastavnik } from '../../../models/nastavnik.model';
import { Zvanje } from '../../../models/zvanje.model';
import { NastavnikService } from '../../../services/nastavnik.service';

@Component({
  selector: 'app-nastavnik-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nastavnik-form.html',
  styleUrl: './nastavnik-form.css'
})
export class NastavnikFormComponent implements OnInit {

  model: Nastavnik = { id: 0, ime: '', prezime: '', email: '', zvanje: Zvanje.ASISTENT };
  zvanja = Object.values(Zvanje);
  izmena: boolean = false;
  greska: string = '';
  cuvanje: boolean = false;

  constructor(
    private service: NastavnikService,
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
        this.router.navigate(['/nastavnici']);
      },
      error: (err) => {
        this.cuvanje = false;
        this.greska = err.error?.messages?.join(', ') || 'Greška prilikom čuvanja';
        console.error(err);
      }
    });
  }

  otkazi(): void {
    this.router.navigate(['/nastavnici']);
  }
}