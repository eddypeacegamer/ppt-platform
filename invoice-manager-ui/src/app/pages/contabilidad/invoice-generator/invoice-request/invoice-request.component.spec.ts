import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceRequestComponent } from './invoice-request.component';

describe('InvoiceRequestComponent', () => {
  let component: InvoiceRequestComponent;
  let fixture: ComponentFixture<InvoiceRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
