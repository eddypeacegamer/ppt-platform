import { async, TestBed } from '@angular/core/testing';
import { InvoiceRequestComponent } from './invoice-request.component';
describe('InvoiceRequestComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [InvoiceRequestComponent]
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
//# sourceMappingURL=invoice-request.component.spec.js.map