import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StampInvoicesComponent } from './stamp-invoices.component';

describe('StampInvoicesComponent', () => {
  let component: StampInvoicesComponent;
  let fixture: ComponentFixture<StampInvoicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StampInvoicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StampInvoicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
