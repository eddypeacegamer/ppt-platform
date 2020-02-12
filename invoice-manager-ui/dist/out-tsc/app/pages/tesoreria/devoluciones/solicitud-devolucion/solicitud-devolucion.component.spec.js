import { async, TestBed } from '@angular/core/testing';
import { SolicitudDevolucionComponent } from './solicitud-devolucion.component';
describe('SolicitudDevolucionComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SolicitudDevolucionComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(SolicitudDevolucionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=solicitud-devolucion.component.spec.js.map