import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { MetasService } from '../../services/metas.service'; // Ajuste o caminho se necessário

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
    // Cria 6 campos vazios para o usuário preencher
    const itensArray = this.metasForm.get('itens') as FormArray;
    for (let i = 0; i < 6; i++) {
      itensArray.push(this.fb.control(''));
    }
  }

  // Getter para usar no HTML
  get itensControls() {
    return (this.metasForm.get('itens') as FormArray).controls;
  }

  salvarMetas() {
    this.carregando = true;
    const metasStrings: string[] = this.metasForm.value.itens;
    
    // Filtra apenas as que foram preenchidas
    const metasValidas = metasStrings.filter(m => m && m.trim() !== '');

    if (metasValidas.length === 0) {
      alert('Preencha pelo menos uma meta!');
      this.carregando = false;
      return;
    }

    // Salva uma por uma (Loop)
    let processados = 0;
    metasValidas.forEach(nome => {
      this.metasService.criarMeta(nome).subscribe({
        next: () => {
          processados++;
          if (processados === metasValidas.length) {
            alert('Metas definidas com sucesso!');
            this.router.navigate(['/progresso-metas']); // Redireciona para visualizar
          }
        },
        error: (err) => {
          console.error(err);
          this.carregando = false;
        }
      });
    });
  }
  
  voltar() {
    this.router.navigate(['/']); // Volta para home
  }
}