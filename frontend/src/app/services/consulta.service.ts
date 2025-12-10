import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  private apiUrl = `${environment.apiUrl}/api/consultas`;

  constructor(private http: HttpClient) { }

  agendar(dados: any): Observable<any> {
    return this.http.post(this.apiUrl, dados);
  }

  getMinhasConsultas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/minhas`);
  }
}
