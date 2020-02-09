import * as tslib_1 from "tslib";
import { Pipe } from '@angular/core';
let NumberWithCommasPipe = class NumberWithCommasPipe {
    transform(input) {
        return new Intl.NumberFormat().format(input);
    }
};
NumberWithCommasPipe = tslib_1.__decorate([
    Pipe({ name: 'ngxNumberWithCommas' })
], NumberWithCommasPipe);
export { NumberWithCommasPipe };
//# sourceMappingURL=number-with-commas.pipe.js.map