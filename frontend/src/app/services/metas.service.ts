import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MetasService {

  private apiUrl = 'https://transcare-api.onrender.com/metas';

  constructor(private http: HttpClient) { }

  criarMeta(nome: string): Observable<any> {
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.post(this.apiUrl, { nome }, { headers });
    
  }

  getMinhasMetas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/minhas`);
  }
}
