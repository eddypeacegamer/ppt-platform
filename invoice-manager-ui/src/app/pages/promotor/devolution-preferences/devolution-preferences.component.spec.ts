import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DevolutionPreferencesComponent } from './devolution-preferences.component';

describe('DevolutionPreferencesComponent', () => {
  let component: DevolutionPreferencesComponent;
  let fixture: ComponentFixture<DevolutionPreferencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DevolutionPreferencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DevolutionPreferencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
