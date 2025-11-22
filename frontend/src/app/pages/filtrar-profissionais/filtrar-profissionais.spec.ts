import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrarProfissionais } from './filtrar-profissionais';

describe('FiltrarProfissionais', () => {
  let component: FiltrarProfissionais;
  let fixture: ComponentFixture<FiltrarProfissionais>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FiltrarProfissionais]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FiltrarProfissionais);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
