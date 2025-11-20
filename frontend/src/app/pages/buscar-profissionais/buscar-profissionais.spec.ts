import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarProfissionais } from './buscar-profissionais';

describe('BuscarProfissionais', () => {
  let component: BuscarProfissionais;
  let fixture: ComponentFixture<BuscarProfissionais>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuscarProfissionais]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuscarProfissionais);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
