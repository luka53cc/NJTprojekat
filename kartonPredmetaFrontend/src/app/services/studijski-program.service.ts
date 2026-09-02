import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudijskiProgram } from '../models/studijski-program.model';

@Injectable({
  providedIn: 'root'
})
export class StudijskiProgramService {

  private baseUrl = 'http://localhost:8080/api/studijski-programi';

  constructor(private http: HttpClient) {}

  findAll(): Observable<StudijskiProgram[]> {
    return this.http.get<StudijskiProgram[]>(this.baseUrl);
  }

  findById(id: number): Observable<StudijskiProgram> {
    return this.http.get<StudijskiProgram>(`${this.baseUrl}/${id}`);
  }

  add(dto: StudijskiProgram): Observable<StudijskiProgram> {
    return this.http.post<StudijskiProgram>(this.baseUrl, dto);
  }

  update(id: number, dto: StudijskiProgram): Observable<StudijskiProgram> {
    return this.http.put<StudijskiProgram>(`${this.baseUrl}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
