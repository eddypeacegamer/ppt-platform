import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { delay, share } from 'rxjs/operators';
let LayoutService = class LayoutService {
    constructor() {
        this.layoutSize$ = new Subject();
    }
    changeLayoutSize() {
        this.layoutSize$.next();
    }
    onChangeLayoutSize() {
        return this.layoutSize$.pipe(share(), delay(1));
    }
};
LayoutService = tslib_1.__decorate([
    Injectable()
], LayoutService);
export { LayoutService };
//# sourceMappingURL=layout.service.js.map