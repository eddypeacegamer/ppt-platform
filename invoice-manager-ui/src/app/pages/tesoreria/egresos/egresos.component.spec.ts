import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EgresosComponent } from './egresos.component';

describe('EgresosComponent', () => {
  let component: EgresosComponent;
  let fixture: ComponentFixture<EgresosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EgresosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EgresosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
