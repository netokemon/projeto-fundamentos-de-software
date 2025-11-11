import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent implements OnInit{

  mensagem: string = '';

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.api.getMensagem().subscribe(res => {
      this.mensagem = res;
    });
  }

}
