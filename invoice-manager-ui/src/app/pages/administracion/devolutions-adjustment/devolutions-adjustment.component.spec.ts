import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DevolutionsAdjustmentComponent } from './devolutions-adjustment.component';

describe('DevolutionsAdjustmentComponent', () => {
  let component: DevolutionsAdjustmentComponent;
  let fixture: ComponentFixture<DevolutionsAdjustmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DevolutionsAdjustmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DevolutionsAdjustmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
