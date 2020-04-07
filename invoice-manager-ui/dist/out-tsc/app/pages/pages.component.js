import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { UsersData } from '../@core/data/users-data';
let PagesComponent = class PagesComponent {
    constructor(userService) {
        this.userService = userService;
        this.menu = [];
    }
    ngOnInit() {
        this.userService.getUserInfo().subscribe((user) => { this.menu = user.menu; });
    }
};
PagesComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-pages',
        styleUrls: ['pages.component.scss'],
        template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
    }),
    tslib_1.__metadata("design:paramtypes", [UsersData])
], PagesComponent);
export { PagesComponent };
//# sourceMappingURL=pages.component.js.map