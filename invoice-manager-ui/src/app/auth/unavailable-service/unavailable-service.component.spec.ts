import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnavailableServiceComponent } from './unavailable-service.component';

describe('UnavailableServiceComponent', () => {
  let component: UnavailableServiceComponent;
  let fixture: ComponentFixture<UnavailableServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnavailableServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnavailableServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
