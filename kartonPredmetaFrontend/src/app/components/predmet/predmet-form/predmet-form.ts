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
import { Zvanje } from '../../../models/zvanje.model';
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
  moguciNosioci: Nastavnik[] = [];

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
      next: (podaci) => {
        this.nastavnici = podaci;
        this.moguciNosioci = podaci.filter(n =>
          n.zvanje === Zvanje.REDOVNI_PROFESOR ||
          n.zvanje === Zvanje.VANREDNI_PROFESOR ||
          n.zvanje === Zvanje.DOCENT
        );
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

    const greskeValidacije: string[] = [];

    if (!this.model.naziv || !this.model.naziv.trim()) {
      greskeValidacije.push('Naziv predmeta je obavezan');
    }
    if (!this.model.sifra || !this.model.sifra.trim()) {
      greskeValidacije.push('Šifra predmeta je obavezna');
    }
    if (!this.model.godinaStudija) {
      greskeValidacije.push('Godina studija je obavezna');
    }
    if (!this.model.semestar) {
      greskeValidacije.push('Semestar je obavezan');
    }
    if (!this.model.espb) {
      greskeValidacije.push('ESPB je obavezan');
    }
    if (this.model.fondPredavanja === null || this.model.fondPredavanja === undefined) {
      greskeValidacije.push('Fond predavanja je obavezan');
    }
    if (this.model.fondVezbi === null || this.model.fondVezbi === undefined) {
      greskeValidacije.push('Fond vežbi je obavezan');
    }
    if (!this.model.status) {
      greskeValidacije.push('Status predmeta je obavezan');
    }
    if (!this.model.cilj || !this.model.cilj.trim()) {
      greskeValidacije.push('Cilj predmeta je obavezan');
    }
    if (!this.model.ishodiUcenja || !this.model.ishodiUcenja.trim()) {
      greskeValidacije.push('Ishodi učenja su obavezni');
    }
    if (!this.model.sadrzajPredavanja || !this.model.sadrzajPredavanja.trim()) {
      greskeValidacije.push('Sadržaj predavanja je obavezan');
    }
    if (!this.model.sadrzajVezbi || !this.model.sadrzajVezbi.trim()) {
      greskeValidacije.push('Sadržaj vežbi je obavezan');
    }
    if (!this.model.nacinPolaganja || !this.model.nacinPolaganja.trim()) {
      greskeValidacije.push('Način polaganja ispita je obavezan');
    }
    if (!this.model.studijskiProgramId) {
      greskeValidacije.push('Studijski program je obavezan');
    }

    if (!this.model.nosilacId) {
      greskeValidacije.push('Nosilac predmeta je obavezan');
    } else if (!this.moguciNosioci.some(n => n.id === this.model.nosilacId)) {
      greskeValidacije.push('Izabrani nosilac predmeta nema odgovarajuće zvanje (mora biti docent, vanredni ili redovni profesor)');
    }

    if (this.model.predispitneObaveze.length === 0) {
      greskeValidacije.push('Mora postojati bar jedna predispitna obaveza');
    } else {
      this.model.predispitneObaveze.forEach((o, i) => {
        if (!o.naziv || !o.naziv.trim()) {
          greskeValidacije.push(`Naziv predispitne obaveze #${i + 1} je obavezan`);
        }
        if (o.brojPoena === null || o.brojPoena === undefined || o.brojPoena < 0) {
          greskeValidacije.push(`Broj poena predispitne obaveze #${i + 1} mora biti unet i ne sme biti negativan`);
        }
      });
    }

    if (this.model.literatura.length === 0) {
      greskeValidacije.push('Mora postojati bar jedna stavka literature');
    } else {
      this.model.literatura.forEach((l, i) => {
        if (!l.naziv || !l.naziv.trim()) {
          greskeValidacije.push(`Naziv literature #${i + 1} je obavezan`);
        }
        if (!l.autor || !l.autor.trim()) {
          greskeValidacije.push(`Autor literature #${i + 1} je obavezan`);
        }
        if (!l.tip) {
          greskeValidacije.push(`Tip literature #${i + 1} je obavezan`);
        }
      });
    }

    if (this.zbirPoena() !== 100) {
      greskeValidacije.push(`Zbir predispitnih poena i poena na ispitu mora biti tačno 100 (trenutno: ${this.zbirPoena()})`);
    }

    if (greskeValidacije.length > 0) {
      this.greska = greskeValidacije.join('; ');
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