import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-cadastro',
  standalone: true, 
  imports: [CommonModule, ReactiveFormsModule], 
  templateUrl: './cadastro.html',
  styleUrl: './cadastro.css', 
})
export class CadastroComponent {
  
  cadastroForm: FormGroup;


  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.cadastroForm = this.fb.group({
      nomeCompleto: ['', Validators.required], 
      email: ['', [Validators.required, Validators.email]], 
      senha: ['', [Validators.required, Validators.minLength(6)]],
      

      nomeSocial: [''],
      pronomes: [''],
      dataNascimento: ['', Validators.required],
      identidadeGenero: [''],
      etapaTransicao: [''],
      regiao: ['']
    });
  }

  onSubmit() {
    console.clear(); 
    console.log('🚀 --- INICIANDO TENTATIVA DE CADASTRO ---');

    console.log('📋 Estado do Formulário:', this.cadastroForm.status);
    console.log('📝 Dados preenchidos:', this.cadastroForm.value);


    if (this.cadastroForm.invalid) {
      console.warn('⚠️ O formulário está INVÁLIDO. O envio foi cancelado.');
      console.group('🔍 Detalhe dos Erros:');
      
      Object.keys(this.cadastroForm.controls).forEach(key => {
        const control = this.cadastroForm.get(key);
        if (control?.invalid) {
          console.error(`❌ Campo [${key}] tem erros:`, control.errors);
        } else {
          console.log(`✅ Campo [${key}] está OK.`);
        }
      });
      console.groupEnd();
      
      alert('Existem campos inválidos. Verifique o console (F12) para detalhes.');
      return; // Para aqui e não tenta enviar pro backend
    }

    // 3. Se chegou aqui, o formulário está OK. Vamos tentar falar com o Backend.
    console.log('📡 Formulário Válido! Enviando requisição para o Backend...');

    this.authService.cadastrar(this.cadastroForm.value).subscribe({
      next: (res) => {
        // 4. Sucesso!
        console.log('✅ SUCESSO! O Backend respondeu:', res);
        alert('Cadastro realizado com sucesso!');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        // 5. Erro no envio ou na resposta
        console.error('❌ ERRO AO CADASTRAR:', err);
        console.log('Status do Erro:', err.status);
        console.log('Mensagem do Backend:', err.error);
        
        // Tratamento específico para aquele erro de texto vs JSON
        if (err.status === 200) {
           console.warn('⚠️ ALERTA: Recebi código 200 (Sucesso) mas caiu no bloco de erro. Provavelmente erro de JSON vs Texto.');
           this.router.navigate(['/login']);
        } else {
           alert('Falha no cadastro. Veja o console para detalhes.');
        }
      }
    });
  }
}