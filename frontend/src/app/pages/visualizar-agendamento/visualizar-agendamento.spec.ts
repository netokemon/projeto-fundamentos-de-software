import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizarAgendamento } from './visualizar-agendamento';

describe('VisualizarAgendamento', () => {
  let component: VisualizarAgendamento;
  let fixture: ComponentFixture<VisualizarAgendamento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisualizarAgendamento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualizarAgendamento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
