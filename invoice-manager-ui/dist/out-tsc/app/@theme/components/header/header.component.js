import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { NbMediaBreakpointsService, NbMenuService, NbSidebarService, NbThemeService } from '@nebular/theme';
import { UsersData } from '../../../@core/data/users-data';
import { LayoutService } from '../../../@core/utils';
import { map, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
let HeaderComponent = class HeaderComponent {
    constructor(sidebarService, menuService, themeService, userService, layoutService, breakpointService) {
        this.sidebarService = sidebarService;
        this.menuService = menuService;
        this.themeService = themeService;
        this.userService = userService;
        this.layoutService = layoutService;
        this.breakpointService = breakpointService;
        this.destroy$ = new Subject();
        this.userPictureOnly = false;
        this.themes = [
            {
                value: 'default',
                name: 'Light',
            },
            {
                value: 'dark',
                name: 'Dark',
            },
            {
                value: 'cosmic',
                name: 'Cosmic',
            },
            {
                value: 'corporate',
                name: 'Corporate',
            },
        ];
        this.currentTheme = 'default';
        this.userMenu = [{ title: 'Profile' }];
    }
    ngOnInit() {
        this.currentTheme = this.themeService.currentTheme;
        this.userService.getUserInfo()
            //.pipe(takeUntil(this.destroy$))
            .subscribe((user) => this.user = user, (error) => { console.log(error.status); if (error.status == 401) {
            this.logout();
        } });
        const { xl } = this.breakpointService.getBreakpointsMap();
        this.themeService.onMediaQueryChange()
            .pipe(map(([, currentBreakpoint]) => currentBreakpoint.width < xl), takeUntil(this.destroy$))
            .subscribe((isLessThanXl) => this.userPictureOnly = isLessThanXl);
        this.themeService.onThemeChange()
            .pipe(map(({ name }) => name), takeUntil(this.destroy$))
            .subscribe(themeName => this.currentTheme = themeName);
    }
    ngOnDestroy() {
        this.destroy$.next();
        this.destroy$.complete();
    }
    changeTheme(themeName) {
        this.themeService.changeTheme(themeName);
    }
    toggleSidebar() {
        this.sidebarService.toggle(true, 'menu-sidebar');
        this.layoutService.changeLayoutSize();
        return false;
    }
    navigateHome() {
        this.menuService.navigateHome();
        return false;
    }
    logout() {
        this.userService.logout().subscribe({
            next(r) { console.log(r); },
            error(e) { console.error(e); },
            complete() { this.document.location.href = "https://mail.google.com/mail/u/0/?logout&hl=en"; }
        });
    }
};
HeaderComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-header',
        styleUrls: ['./header.component.scss'],
        templateUrl: './header.component.html',
    }),
    tslib_1.__metadata("design:paramtypes", [NbSidebarService,
        NbMenuService,
        NbThemeService,
        UsersData,
        LayoutService,
        NbMediaBreakpointsService])
], HeaderComponent);
export { HeaderComponent };
//# sourceMappingURL=header.component.js.map