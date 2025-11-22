import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarProfissional } from './avaliar-profissional';

describe('AvaliarProfissional', () => {
  let component: AvaliarProfissional;
  let fixture: ComponentFixture<AvaliarProfissional>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvaliarProfissional]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarProfissional);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
