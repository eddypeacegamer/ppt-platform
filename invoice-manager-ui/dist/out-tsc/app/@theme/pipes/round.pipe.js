import * as tslib_1 from "tslib";
import { Pipe } from '@angular/core';
let RoundPipe = class RoundPipe {
    transform(input) {
        return Math.round(input);
    }
};
RoundPipe = tslib_1.__decorate([
    Pipe({ name: 'ngxRound' })
], RoundPipe);
export { RoundPipe };
//# sourceMappingURL=round.pipe.js.map