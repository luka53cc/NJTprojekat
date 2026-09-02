import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Predmet } from '../../../models/predmet.model';
import { StatusPredmeta } from '../../../models/status-predmeta.model';
import { Literatura } from '../../../models/literatura.model';
import { TipLiterature } from '../../../models/tip-literature.model';
import { PredispitnaObaveza } from '../../../models/predispitna-obaveza.model';
import { StudijskiProgram } from '../../../models/studijski-program.model';
import { Modul } from '../../../models/modul.model';
import { Nastavnik } from '../../../models/nastavnik.model';
import { PredmetService } from '../../../services/predmet.service';
import { StudijskiProgramService } from '../../../services/studijski-program.service';
import { ModulService } from '../../../services/modul.service';
import { NastavnikService } from '../../../services/nastavnik.service';

@Component({
  selector: 'app-predmet-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './predmet-form.html',
  styleUrl: './predmet-form.css'
})
export class PredmetFormComponent implements OnInit {

  model: Predmet = this.praznModel();

  statusi = Object.values(StatusPredmeta);
  tipoviLiterature = Object.values(TipLiterature);

  studijskiProgrami: StudijskiProgram[] = [];
  moduliZaProgram: Modul[] = [];
  nastavnici: Nastavnik[] = [];

  izmena: boolean = false;
  greska: string = '';
  cuvanje: boolean = false;

  constructor(
    private service: PredmetService,
    private studijskiProgramService: StudijskiProgramService,
    private modulService: ModulService,
    private nastavnikService: NastavnikService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  praznModel(): Predmet {
    return {
      id: 0,
      naziv: '',
      sifra: '',
      godinaStudija: 1,
      semestar: 1,
      espb: 6,
      fondPredavanja: 2,
      fondVezbi: 2,
      status: StatusPredmeta.OBAVEZAN,
      cilj: '',
      ishodiUcenja: '',
      sadrzajPredavanja: '',
      sadrzajVezbi: '',
      nacinPolaganja: '',
      poeniIspit: 50,
      studijskiProgramId: 0,
      modulId: undefined,
      nosilacId: 0,
      nastavniciIds: [],
      literatura: [],
      predispitneObaveze: []
    };
  }

  ngOnInit(): void {
    this.studijskiProgramService.findAll().subscribe({
      next: (podaci) => this.studijskiProgrami = podaci,
      error: (err) => console.error(err)
    });

    this.nastavnikService.findAll().subscribe({
      next: (podaci) => this.nastavnici = podaci,
      error: (err) => console.error(err)
    });

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.izmena = true;
      const id = Number(idParam);
      this.service.findById(id).subscribe({
        next: (podaci) => {
          this.model = podaci;
          this.ucitajModuleZaProgram(podaci.studijskiProgramId);
        },
        error: (err) => {
          this.greska = 'Greška prilikom učitavanja podataka';
          console.error(err);
        }
      });
    }
  }

  onPromenaStudijskogProgramaId(): void {
    this.model.modulId = undefined;
    this.ucitajModuleZaProgram(this.model.studijskiProgramId);
  }

  ucitajModuleZaProgram(studijskiProgramId: number): void {
    if (!studijskiProgramId) {
      this.moduliZaProgram = [];
      return;
    }
    this.modulService.findByStudijskiProgram(studijskiProgramId).subscribe({
      next: (podaci) => this.moduliZaProgram = podaci,
      error: (err) => console.error(err)
    });
  }

  toggleNastavnik(nastavnikId: number, checked: boolean): void {
    if (checked) {
      if (!this.model.nastavniciIds.includes(nastavnikId)) {
        this.model.nastavniciIds.push(nastavnikId);
      }
    } else {
      this.model.nastavniciIds = this.model.nastavniciIds.filter(id => id !== nastavnikId);
    }
  }

  jeNastavnikOznacen(nastavnikId: number): boolean {
    return this.model.nastavniciIds.includes(nastavnikId);
  }

  dodajLiteraturu(): void {
    this.model.literatura.push({ naziv: '', autor: '', tip: TipLiterature.OBAVEZNA });
  }

  ukloniLiteraturu(index: number): void {
    this.model.literatura.splice(index, 1);
  }

  dodajPredispitnuObavezu(): void {
    this.model.predispitneObaveze.push({ naziv: '', brojPoena: 0 });
  }

  ukloniPredispitnuObavezu(index: number): void {
    this.model.predispitneObaveze.splice(index, 1);
  }

  zbirPoena(): number {
    const zbirPredispitnih = this.model.predispitneObaveze.reduce((zbir, o) => zbir + (o.brojPoena || 0), 0);
    return zbirPredispitnih + (this.model.poeniIspit || 0);
  }

  sacuvaj(): void {
    this.greska = '';

    if (this.zbirPoena() !== 100) {
      this.greska = `Zbir predispitnih poena i poena na ispitu mora biti tačno 100 (trenutno: ${this.zbirPoena()})`;
      return;
    }

    if (!this.model.nosilacId) {
      this.greska = 'Nosilac predmeta je obavezan';
      return;
    }

    this.cuvanje = true;

    const zahtev = this.izmena
      ? this.service.update(this.model.id, this.model)
      : this.service.add(this.model);

    zahtev.subscribe({
      next: () => {
        this.cuvanje = false;
        this.router.navigate(['/predmeti']);
      },
      error: (err) => {
        this.cuvanje = false;
        this.greska = err.error?.messages?.join(', ') || 'Greška prilikom čuvanja';
        console.error(err);
      }
    });
  }

  otkazi(): void {
    this.router.navigate(['/predmeti']);
  }
}