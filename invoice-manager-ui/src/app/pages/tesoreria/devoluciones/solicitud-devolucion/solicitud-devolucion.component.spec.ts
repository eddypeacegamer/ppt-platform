import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudDevolucionComponent } from './solicitud-devolucion.component';

describe('SolicitudDevolucionComponent', () => {
  let component: SolicitudDevolucionComponent;
  let fixture: ComponentFixture<SolicitudDevolucionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SolicitudDevolucionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitudDevolucionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
