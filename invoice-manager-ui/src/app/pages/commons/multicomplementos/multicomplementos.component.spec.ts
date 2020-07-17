import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MulticomplementosComponent } from './multicomplementos.component';

describe('MulticomplementosComponent', () => {
  let component: MulticomplementosComponent;
  let fixture: ComponentFixture<MulticomplementosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MulticomplementosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MulticomplementosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
