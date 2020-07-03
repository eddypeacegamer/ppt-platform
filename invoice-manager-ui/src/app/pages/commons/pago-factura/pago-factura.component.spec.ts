import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoFacturaComponent } from './pago-factura.component';

describe('PagoFacturaComponent', () => {
  let component: PagoFacturaComponent;
  let fixture: ComponentFixture<PagoFacturaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PagoFacturaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PagoFacturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
