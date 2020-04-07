import { async, TestBed } from '@angular/core/testing';
import { PreCfdiComponent } from './pre-cfdi.component';
describe('PreCfdiComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [PreCfdiComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(PreCfdiComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=pre-cfdi.component.spec.js.map