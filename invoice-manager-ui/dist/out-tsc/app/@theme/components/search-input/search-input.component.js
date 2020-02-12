import * as tslib_1 from "tslib";
import { Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
let SearchInputComponent = class SearchInputComponent {
    constructor() {
        this.search = new EventEmitter();
        this.isInputShown = false;
    }
    showInput() {
        this.isInputShown = true;
        this.input.nativeElement.focus();
    }
    hideInput() {
        this.isInputShown = false;
    }
    onInput(val) {
        this.search.emit(val);
    }
};
tslib_1.__decorate([
    ViewChild('input', { static: true }),
    tslib_1.__metadata("design:type", ElementRef)
], SearchInputComponent.prototype, "input", void 0);
tslib_1.__decorate([
    Output(),
    tslib_1.__metadata("design:type", EventEmitter)
], SearchInputComponent.prototype, "search", void 0);
SearchInputComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-search-input',
        styleUrls: ['./search-input.component.scss'],
        template: `
    <i class="control-icon ion ion-ios-search"
       (click)="showInput()"></i>
    <input placeholder="Type your search request here..."
           #input
           [class.hidden]="!isInputShown"
           (blur)="hideInput()"
           (input)="onInput($event)">
  `,
    })
], SearchInputComponent);
export { SearchInputComponent };
//# sourceMappingURL=search-input.component.js.map