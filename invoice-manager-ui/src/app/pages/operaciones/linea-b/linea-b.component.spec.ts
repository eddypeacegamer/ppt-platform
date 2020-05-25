import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LineaBComponent } from './linea-b.component';

describe('LineaBComponent', () => {
  let component: LineaBComponent;
  let fixture: ComponentFixture<LineaBComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LineaBComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LineaBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
