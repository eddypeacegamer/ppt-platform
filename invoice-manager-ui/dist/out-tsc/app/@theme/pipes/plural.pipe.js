import * as tslib_1 from "tslib";
import { Pipe } from '@angular/core';
let PluralPipe = class PluralPipe {
    transform(input, label, pluralLabel = '') {
        input = input || 0;
        return input === 1
            ? `${input} ${label}`
            : pluralLabel
                ? `${input} ${pluralLabel}`
                : `${input} ${label}s`;
    }
};
PluralPipe = tslib_1.__decorate([
    Pipe({ name: 'ngxPlural' })
], PluralPipe);
export { PluralPipe };
//# sourceMappingURL=plural.pipe.js.map