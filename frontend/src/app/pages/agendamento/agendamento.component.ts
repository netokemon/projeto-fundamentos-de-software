import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConsultaService } from '../../services/consulta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agendamento',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './agendamento.html',
  styleUrl: './agendamento.css' 
})
export class AgendamentoComponent {

  mostrarModal = false;
  agendamentoForm: FormGroup;
  tipoConsultaSelecionada: string = '';

  constructor(
    private fb: FormBuilder,
    private consultaService: ConsultaService,
    private router: Router
  ) {
    this.agendamentoForm = this.fb.group({
      nomeMedico: ['', Validators.required],
      local: ['', Validators.required], // Será preenchido automático ou manual
      especializacao: ['', Validators.required],
      dataHora: ['', Validators.required]
    });
  }

  // Abre o modal e já configura o local dependendo do botão clicado
  abrirModal(tipo: 'TELEMEDICINA' | 'PRESENCIAL') {
    this.tipoConsultaSelecionada = tipo;
    this.mostrarModal = true;

    if (tipo === 'TELEMEDICINA') {
      this.agendamentoForm.patchValue({ local: 'Consulta Online (Meet/Zoom)' });
    } else {
      this.agendamentoForm.patchValue({ local: '' }); // Limpa para pessoa digitar ou deixa padrão
    }
  }

  fecharModal() {
    this.mostrarModal = false;
  }

  onSubmit() {
    console.clear();
    console.log('🚀 Tentando agendar...');

    // 1. Verificar Validade
    if (this.agendamentoForm.invalid) {
      console.warn('⚠️ Formulário Inválido! Veja os erros:');
      
      Object.keys(this.agendamentoForm.controls).forEach(key => {
        const control = this.agendamentoForm.get(key);
        if (control?.invalid) {
          console.error(`❌ Campo [${key}] está errado:`, control.errors);
        } else {
          console.log(`✅ Campo [${key}] está OK.`);
        }
      });
      alert('Preencha todos os campos corretamente!');
      return;
    }

    // 2. Verificar o JSON exato que será enviado
    const dadosParaEnviar = this.agendamentoForm.value;
    console.log('📦 JSON Pronto para envio:', dadosParaEnviar);

    // 3. Enviar e monitorar resposta
    this.consultaService.agendar(dadosParaEnviar).subscribe({
      next: (res) => {
        console.log('✅ SUCESSO! Resposta do servidor:', res);
        alert('Consulta agendada com sucesso!');
        this.fecharModal();
      },
      error: (err) => {
        console.error('❌ ERRO NO BACKEND:', err);
        console.log('Status:', err.status);
        console.log('Detalhes do erro:', err.error);
        
        if(err.status === 403) alert('Erro 403: Você está logado? O token expirou?');
        else if(err.status === 400) alert('Erro 400: Dados inválidos (Verifique Data ou Enum)');
        else alert('Erro desconhecido. Olhe o console.');
      }
    });
  }

  voltarHome() {
    this.router.navigate(['/']); 
  }
}