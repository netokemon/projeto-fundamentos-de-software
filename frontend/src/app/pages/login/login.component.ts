import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router'; 
import { AuthService } from '../../services/auth.service'; 

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink], 
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {

  loginData = {
    email: '',
    senha: ''
  };

  mostrarSenha = false; 
  mensagemErro = '';

  constructor(
    private authService: AuthService, 
    private router: Router
  ) {}

  toggleSenha() {
    this.mostrarSenha = !this.mostrarSenha;
  }

  onSubmit() {
    this.mensagemErro = '';

    if (!this.loginData.email || !this.loginData.senha) {
      this.mensagemErro = 'Por favor, preencha email e senha.';
      return;
    }

    this.authService.login(this.loginData).subscribe({
      next: (res: any) => {
        console.log('Login Sucesso:', res);
        

        localStorage.setItem('token', res.token);
        
        this.router.navigate(['/home']); 
      },
      error: (err) => {
        console.error('Erro Login:', err);
        this.mensagemErro = 'Email ou senha incorretos.';
      }
    });
  }
}