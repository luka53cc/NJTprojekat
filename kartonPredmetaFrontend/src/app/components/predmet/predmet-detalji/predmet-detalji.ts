import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Predmet } from '../../../models/predmet.model';
import { PredmetService } from '../../../services/predmet.service';

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

  constructor(
    private service: PredmetService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.findById(id).subscribe({
      next: (podaci) => {
        this.predmet = podaci;
        this.ucitavanje = false;
      },
      error: (err) => {
        this.greska = 'Greška prilikom učitavanja predmeta';
        this.ucitavanje = false;
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
        console.error(err);
      }
    });
  }
}