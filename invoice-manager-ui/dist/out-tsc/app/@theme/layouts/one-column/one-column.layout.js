import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
let OneColumnLayoutComponent = class OneColumnLayoutComponent {
};
OneColumnLayoutComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-one-column-layout',
        styleUrls: ['./one-column.layout.scss'],
        template: `
    <nb-layout windowMode>
      <nb-layout-header fixed>
        <ngx-header></ngx-header>
      </nb-layout-header>

      <nb-sidebar class="menu-sidebar" tag="menu-sidebar" responsive>
        <ng-content select="nb-menu"></ng-content>
      </nb-sidebar>

      <nb-layout-column>
        <ng-content select="router-outlet"></ng-content>
      </nb-layout-column>
    </nb-layout>
  `,
    })
], OneColumnLayoutComponent);
export { OneColumnLayoutComponent };
//# sourceMappingURL=one-column.layout.js.map