import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreCfdiComponent } from './pre-cfdi.component';

describe('PreCfdiComponent', () => {
  let component: PreCfdiComponent;
  let fixture: ComponentFixture<PreCfdiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreCfdiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreCfdiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
