import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarComplementoComponent } from './generar-complemento.component';

describe('GenerarComplementoComponent', () => {
  let component: GenerarComplementoComponent;
  let fixture: ComponentFixture<GenerarComplementoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenerarComplementoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerarComplementoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
