import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core'; // Adicione Inject e PLATFORM_ID
import { RouterLink, Router } from '@angular/router'; 
import { CommonModule, isPlatformBrowser } from '@angular/common'; // Adicione isPlatformBrowser

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, CommonModule], 
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class HomeComponent implements OnInit {

  estaLogado: boolean = false;

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object // Injeção mágica para saber onde estamos
  ) {}

  ngOnInit(): void {
    // A CORREÇÃO ESTÁ AQUI:
    // Só tenta ler o localStorage se for o Navegador
    if (isPlatformBrowser(this.platformId)) {
      this.estaLogado = !!localStorage.getItem('token');
    }
  }

  logout() {
    // Aqui não precisa de verificação obrigatória pq o clique só ocorre no navegador,
    // mas é bom garantir.
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
      this.estaLogado = false;
      window.location.reload(); 
    }
  }

  voltarAgendar(){
    this.router.navigate(['/agendar']);
  }

  voltarMetas(){
    this.router.navigate(['/metas/progresso'])
  }
}