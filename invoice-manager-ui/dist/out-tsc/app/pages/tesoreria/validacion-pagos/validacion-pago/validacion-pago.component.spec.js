import { async, TestBed } from '@angular/core/testing';
import { ValidacionPagoComponent } from './validacion-pago.component';
describe('ValidacionPagoComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ValidacionPagoComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(ValidacionPagoComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=validacion-pago.component.spec.js.map