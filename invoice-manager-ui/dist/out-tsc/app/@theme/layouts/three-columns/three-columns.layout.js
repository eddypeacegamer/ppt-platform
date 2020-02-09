import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
let ThreeColumnsLayoutComponent = class ThreeColumnsLayoutComponent {
};
ThreeColumnsLayoutComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-three-columns-layout',
        styleUrls: ['./three-columns.layout.scss'],
        template: `
    <nb-layout windowMode>
      <nb-layout-header fixed>
        <ngx-header></ngx-header>
      </nb-layout-header>

      <nb-sidebar class="menu-sidebar" tag="menu-sidebar" responsive>
        <ng-content select="nb-menu"></ng-content>
      </nb-sidebar>

      <nb-layout-column class="small">
      </nb-layout-column>

      <nb-layout-column>
        <ng-content select="router-outlet"></ng-content>
      </nb-layout-column>

      <nb-layout-column class="small">
      </nb-layout-column>

      <nb-layout-footer fixed>
        <ngx-footer></ngx-footer>
      </nb-layout-footer>
    </nb-layout>
  `,
    })
], ThreeColumnsLayoutComponent);
export { ThreeColumnsLayoutComponent };
//# sourceMappingURL=three-columns.layout.js.map