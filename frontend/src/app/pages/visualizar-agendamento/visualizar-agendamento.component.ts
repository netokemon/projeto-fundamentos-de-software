import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Necessário para *ngFor e Pipes de Data
import { ConsultaService } from '../../services/consulta.service';

@Component({
  selector: 'app-visualizar-agendamento',
  standalone: true,
  imports: [CommonModule], // Importe o CommonModule aqui!
  templateUrl: './visualizar-agendamento.html', // Verifique se o nome é .component.html
  styleUrl: './visualizar-agendamento.css',
})
export class VisualizarAgendamentoComponent implements OnInit {

  consultas: any[] = [];
  carregando = true;

  constructor(private consultaService: ConsultaService) {}

  ngOnInit(): void {
    this.buscarConsultas();
  }

  buscarConsultas() {
    this.consultaService.getMinhasConsultas().subscribe({
      next: (dados) => {
        this.consultas = dados;
        this.carregando = false;
        console.log('Consultas carregadas:', dados);
      },
      error: (erro) => {
        console.error('Erro ao buscar consultas:', erro);
        this.carregando = false;
      }
    });
  }
}