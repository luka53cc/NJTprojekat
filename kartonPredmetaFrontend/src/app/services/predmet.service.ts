import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Predmet } from '../models/predmet.model';
import { Page } from '../models/page.model';

@Injectable({
  providedIn: 'root'
})
export class PredmetService {

  private baseUrl = 'http://localhost:8080/api/predmeti';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(this.baseUrl);
  }

  findById(id: number): Observable<Predmet> {
    return this.http.get<Predmet>(`${this.baseUrl}/${id}`);
  }

  pretrazi(filteri: {
    naziv?: string;
    sifra?: string;
    godinaStudija?: number;
    semestar?: number;
    status?: string;
    studijskiProgramId?: number;
    modulId?: number;
    page?: number;
    size?: number;
    sort?: string;
  }): Observable<Page<Predmet>> {
    let params = new HttpParams();
    Object.entries(filteri).forEach(([kljuc, vrednost]) => {
      if (vrednost !== undefined && vrednost !== null && vrednost !== '') {
        params = params.set(kljuc, vrednost.toString());
      }
    });
    return this.http.get<Page<Predmet>>(`${this.baseUrl}/pretraga`, { params });
  }

  add(dto: Predmet): Observable<Predmet> {
    return this.http.post<Predmet>(this.baseUrl, dto);
  }

  update(id: number, dto: Predmet): Observable<Predmet> {
    return this.http.put<Predmet>(`${this.baseUrl}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  downloadPdf(id: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/${id}/pdf`, { responseType: 'blob' });
  }
}
