import * as tslib_1 from "tslib";
import { Component, Output, EventEmitter, ElementRef } from '@angular/core';
let TinyMCEComponent = class TinyMCEComponent {
    constructor(host) {
        this.host = host;
        this.editorKeyup = new EventEmitter();
    }
    ngAfterViewInit() {
        tinymce.init({
            target: this.host.nativeElement,
            plugins: ['link', 'paste', 'table'],
            skin_url: 'assets/skins/lightgray',
            setup: editor => {
                this.editor = editor;
                editor.on('keyup', () => {
                    this.editorKeyup.emit(editor.getContent());
                });
            },
            height: '320',
        });
    }
    ngOnDestroy() {
        tinymce.remove(this.editor);
    }
};
tslib_1.__decorate([
    Output(),
    tslib_1.__metadata("design:type", Object)
], TinyMCEComponent.prototype, "editorKeyup", void 0);
TinyMCEComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-tiny-mce',
        template: '',
    }),
    tslib_1.__metadata("design:paramtypes", [ElementRef])
], TinyMCEComponent);
export { TinyMCEComponent };
//# sourceMappingURL=tiny-mce.component.js.map