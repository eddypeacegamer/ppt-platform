import { async, TestBed } from '@angular/core/testing';
import { InvoiceGeneratorComponent } from './invoice-generator.component';
describe('InvoiceGeneratorComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [InvoiceGeneratorComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(InvoiceGeneratorComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=invoice-generator.component.spec.js.map