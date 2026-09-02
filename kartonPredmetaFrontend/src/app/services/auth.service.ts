import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(podaci: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, podaci).pipe(
      tap(odgovor => {
        localStorage.setItem('token', odgovor.token);
        localStorage.setItem('korisnickoIme', odgovor.korisnickoIme);
        localStorage.setItem('uloga', odgovor.uloga);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('korisnickoIme');
    localStorage.removeItem('uloga');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getKorisnickoIme(): string | null {
    return localStorage.getItem('korisnickoIme');
  }
}
