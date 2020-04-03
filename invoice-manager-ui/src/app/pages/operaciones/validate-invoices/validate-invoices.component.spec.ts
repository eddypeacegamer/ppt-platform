import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidateInvoicesComponent } from './validate-invoices.component';

describe('ValidateInvoicesComponent', () => {
  let component: ValidateInvoicesComponent;
  let fixture: ComponentFixture<ValidateInvoicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidateInvoicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidateInvoicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
