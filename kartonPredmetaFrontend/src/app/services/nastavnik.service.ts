import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nastavnik } from '../models/nastavnik.model';

@Injectable({
  providedIn: 'root'
})
export class NastavnikService {

  private baseUrl = 'http://localhost:8080/api/nastavnici';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Nastavnik[]> {
    return this.http.get<Nastavnik[]>(this.baseUrl);
  }

  findById(id: number): Observable<Nastavnik> {
    return this.http.get<Nastavnik>(`${this.baseUrl}/${id}`);
  }

  add(dto: Nastavnik): Observable<Nastavnik> {
    return this.http.post<Nastavnik>(this.baseUrl, dto);
  }

  update(id: number, dto: Nastavnik): Observable<Nastavnik> {
    return this.http.put<Nastavnik>(`${this.baseUrl}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
