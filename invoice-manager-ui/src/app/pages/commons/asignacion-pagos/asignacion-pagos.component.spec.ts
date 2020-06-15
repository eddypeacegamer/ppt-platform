import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignacionPagosComponent } from './asignacion-pagos.component';

describe('AsignacionPagosComponent', () => {
  let component: AsignacionPagosComponent;
  let fixture: ComponentFixture<AsignacionPagosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsignacionPagosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignacionPagosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
