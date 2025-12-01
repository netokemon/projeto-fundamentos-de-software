import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, CommonModule], 
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class HomeComponent implements OnInit {

  estaLogado: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.estaLogado = !!localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    
    this.estaLogado = false;
    
    window.location.reload(); 
  }
}