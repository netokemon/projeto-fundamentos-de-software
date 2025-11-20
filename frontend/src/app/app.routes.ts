import { Routes } from '@angular/router';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { LoginComponent } from './pages/login/login.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { VisualizarAgendamentoComponent } from './pages/visualizar-agendamento/visualizar-agendamento.component';
import { AvaliarProfissionalComponent } from './pages/avaliar-profissional/avaliar-profissional.component';
import { EditarPerfilComponent } from './pages/editar-perfil/editar-perfil.component';
import { BuscarProfissionaisComponent } from './pages/buscar-profissionais/buscar-profissionais.component';
import { FiltrarProfissionaisComponent } from './pages/filtrar-profissionais/filtrar-profissionais.component';

export const routes: Routes = [
    { path: '', component: WelcomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'cadastro', component: CadastroComponent },
    { path: 'agendar', component: AgendamentoComponent },
    { path: 'meus-agendamentos', component: VisualizarAgendamentoComponent },
    { path: 'avaliar', component: AvaliarProfissionalComponent },
    { path: 'perfil/editar', component: EditarPerfilComponent },
    { path: 'buscar', component: BuscarProfissionaisComponent },
    { path: 'filtros', component: FiltrarProfissionaisComponent },
];
