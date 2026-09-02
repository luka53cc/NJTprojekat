import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Modul } from '../models/modul.model';

@Injectable({
  providedIn: 'root'
})
export class ModulService {

  private baseUrl = 'http://localhost:8080/api/moduli';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Modul[]> {
    return this.http.get<Modul[]>(this.baseUrl);
  }

  findById(id: number): Observable<Modul> {
    return this.http.get<Modul>(`${this.baseUrl}/${id}`);
  }

  findByStudijskiProgram(studijskiProgramId: number): Observable<Modul[]> {
    return this.http.get<Modul[]>(`${this.baseUrl}/po-programu/${studijskiProgramId}`);
  }

  add(dto: Modul): Observable<Modul> {
    return this.http.post<Modul>(this.baseUrl, dto);
  }

  update(id: number, dto: Modul): Observable<Modul> {
    return this.http.put<Modul>(`${this.baseUrl}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}