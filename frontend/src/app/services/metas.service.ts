import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetasService {

  private apiUrl = 'http://localhost:8080/api/metas';

  constructor(private http: HttpClient) { }

  criarMeta(nome: string): Observable<any> {
    return this.http.post(this.apiUrl, { nome });
  }

  getMinhasMetas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/minhas`);
  }
}