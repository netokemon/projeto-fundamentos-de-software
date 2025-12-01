import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ApiService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getMensagem() {
    return this.http.get(this.baseUrl + '/mensagem', { responseType: 'text' });
  }
}