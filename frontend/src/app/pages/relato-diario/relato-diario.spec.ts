import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoDiario } from './relato-diario';

describe('RelatoDiario', () => {
  let component: RelatoDiario;
  let fixture: ComponentFixture<RelatoDiario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoDiario]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoDiario);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
