import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {

  korisnickoIme: string = '';
  lozinka: string = '';
  greska: string = '';
  ucitavanje: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  prijaviSe(): void {
    this.greska = '';
    this.ucitavanje = true;

    this.authService.login({ korisnickoIme: this.korisnickoIme, lozinka: this.lozinka }).subscribe({
      next: () => {
        this.ucitavanje = false;
        this.router.navigate(['/predmeti']);
      },
      error: (err) => {
        this.ucitavanje = false;
        this.greska = 'Pogrešno korisničko ime ili lozinka';
        console.error(err);
      }
    });
  }

  nastaviKaoGost(): void {
    this.router.navigate(['/predmeti']);
  }
}