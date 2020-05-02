import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SesionLostComponent } from './sesion-lost.component';

describe('SesionLostComponent', () => {
  let component: SesionLostComponent;
  let fixture: ComponentFixture<SesionLostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SesionLostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SesionLostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
