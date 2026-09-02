import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login';
import { StudijskiProgramListComponent } from './components/studijski-program/studijski-program-list/studijski-program-list';
import { StudijskiProgramFormComponent } from './components/studijski-program/studijski-program-form/studijski-program-form';
import { NastavnikListComponent } from './components/nastavnik/nastavnik-list/nastavnik-list';
import { NastavnikFormComponent } from './components/nastavnik/nastavnik-form/nastavnik-form';
import { ModulListComponent } from './components/modul/modul-list/modul-list';
import { ModulFormComponent } from './components/modul/modul-form/modul-form';
import { PredmetListComponent } from './components/predmet/predmet-list/predmet-list';
import { PredmetFormComponent } from './components/predmet/predmet-form/predmet-form';
import { PredmetDetaljiComponent } from './components/predmet/predmet-detalji/predmet-detalji';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'predmeti', pathMatch: 'full' },

  { path: 'predmeti', component: PredmetListComponent, canActivate: [authGuard] },
  { path: 'predmeti/novi', component: PredmetFormComponent, canActivate: [authGuard] },
  { path: 'predmeti/detalji/:id', component: PredmetDetaljiComponent, canActivate: [authGuard] },
  { path: 'predmeti/:id', component: PredmetFormComponent, canActivate: [authGuard] },

  { path: 'nastavnici', component: NastavnikListComponent, canActivate: [authGuard] },
  { path: 'nastavnici/novi', component: NastavnikFormComponent, canActivate: [authGuard] },
  { path: 'nastavnici/:id', component: NastavnikFormComponent, canActivate: [authGuard] },

  { path: 'moduli', component: ModulListComponent, canActivate: [authGuard] },
  { path: 'moduli/novi', component: ModulFormComponent, canActivate: [authGuard] },
  { path: 'moduli/:id', component: ModulFormComponent, canActivate: [authGuard] },

  { path: 'studijski-programi', component: StudijskiProgramListComponent, canActivate: [authGuard] },
  { path: 'studijski-programi/novi', component: StudijskiProgramFormComponent, canActivate: [authGuard] },
  { path: 'studijski-programi/:id', component: StudijskiProgramFormComponent, canActivate: [authGuard] },
];