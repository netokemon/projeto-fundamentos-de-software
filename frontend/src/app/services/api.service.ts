import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {

  private baseUrl = 'https://transcare-api.onrender.com/auth';

  constructor(private http: HttpClient) {}

  getMensagem() {
    return this.http.get(this.baseUrl + '/mensagem', { responseType: 'text' });
  }
}
