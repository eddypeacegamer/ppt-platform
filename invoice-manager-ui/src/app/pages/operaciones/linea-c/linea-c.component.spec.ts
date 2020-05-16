import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LineaCComponent } from './linea-c.component';

describe('LineaCComponent', () => {
  let component: LineaCComponent;
  let fixture: ComponentFixture<LineaCComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LineaCComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LineaCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
