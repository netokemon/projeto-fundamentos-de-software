import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { MetasService } from '../../services/metas.service';

@Component({
  selector: 'app-progresso-metas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './progresso-metas.html',
  styleUrl: './progresso-metas.css'
})
export class ProgressoMetasComponent implements OnInit {

  metas: any[] = [];
  carregando = true;

  constructor(private metasService: MetasService, private router: Router) {}

  ngOnInit(): void {
    this.metasService.getMinhasMetas().subscribe({
      next: (dados) => {
        this.metas = dados.map(m => ({
          ...m,
          progressoVisual: Math.floor(Math.random() * 100) // Simulação visual
        }));
        this.carregando = false;
      },
      error: (err) => {
        console.error(err);
        this.carregando = false;
      }
    });
  }
  
  voltarHome() {
      this.router.navigate(['/home']);
  }
}