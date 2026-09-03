import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IstorijaIzmene } from '../models/istorija-izmene.model';

@Injectable({
  providedIn: 'root'
})
export class IstorijaIzmeneService {

  private baseUrl = 'http://localhost:8080/api/istorija-izmena';

  constructor(private http: HttpClient) {}

  findByPredmet(predmetId: number): Observable<IstorijaIzmene[]> {
    return this.http.get<IstorijaIzmene[]>(`${this.baseUrl}/predmet/${predmetId}`);
  }
}