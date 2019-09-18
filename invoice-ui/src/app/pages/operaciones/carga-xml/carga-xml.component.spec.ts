import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CargaXmlComponent } from './carga-xml.component';

describe('CargaXmlComponent', () => {
  let component: CargaXmlComponent;
  let fixture: ComponentFixture<CargaXmlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CargaXmlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CargaXmlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
