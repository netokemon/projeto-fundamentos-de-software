import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { MetasService } from '../../services/metas.service';
import { forkJoin } from 'rxjs'; // <--- Importação Essencial

@Component({
  selector: 'app-definir-metas',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './definir-metas.html',
  styleUrl: './definir-metas.css'
})
export class DefinirMetasComponent implements OnInit {

  metasForm: FormGroup;
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private metasService: MetasService,
    private router: Router
  ) {
    this.metasForm = this.fb.group({
      itens: this.fb.array([])
    });
  }

  ngOnInit(): void {
    // Cria 6 campos vazios
    const itensArray = this.metasForm.get('itens') as FormArray;
    for (let i = 0; i < 6; i++) {
      itensArray.push(this.fb.control(''));
    }
  }

  get itensControls() {
    return (this.metasForm.get('itens') as FormArray).controls;
  }

  salvarMetas() {
    // 1. Filtra metas vazias (para não enviar lixo pro banco)
    const todasAsMetas: string[] = this.metasForm.value.itens;
    const metasParaSalvar = todasAsMetas.filter(m => m && m.trim().length > 0);

    if (metasParaSalvar.length === 0) {
      alert('Por favor, escreva pelo menos uma meta.');
      return;
    }

    this.carregando = true;

    // 2. Prepara o pacote de requisições
    const requisicoes = metasParaSalvar.map(meta => 
      this.metasService.criarMeta(meta)
    );

    // 3. Envia tudo junto e espera terminar
    forkJoin(requisicoes).subscribe({
      next: (respostas) => {
        console.log('Sucesso!', respostas);
        alert('Metas salvas com sucesso! Vamos para o progresso.');
        this.router.navigate(['/metas/progresso']);
      },
      error: (erro) => {
        console.error('Erro ao salvar:', erro);
        this.carregando = false;
        
        if (erro.status === 403) {
          alert('Sessão expirada. Faça login novamente.');
          this.router.navigate(['/login']);
        } else {
          alert('Erro ao salvar. Verifique o console.');
        }
      }
    });
  }

  voltar() {
    this.router.navigate(['/']);
  }
}