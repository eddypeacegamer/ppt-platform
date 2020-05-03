import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidacionPagoComponent } from './validacion-pago.component';

describe('ValidacionPagoComponent', () => {
  let component: ValidacionPagoComponent;
  let fixture: ComponentFixture<ValidacionPagoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidacionPagoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidacionPagoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
