import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { takeWhile } from 'rxjs/operators';
let DashboardComponent = class DashboardComponent {
    constructor(themeService) {
        this.themeService = themeService;
        this.alive = true;
        this.lightCard = {
            title: '10 nuevos clientes',
            iconClass: 'nb-person',
            type: 'primary',
        };
        this.rollerShadesCard = {
            title: '15 facturas revision',
            iconClass: 'nb-edit',
            type: 'success',
        };
        this.wirelessAudioCard = {
            title: '3 nuevas empresas',
            iconClass: 'nb-home',
            type: 'info',
        };
        this.coffeeMakerCard = {
            title: '15 pagos pendientes',
            iconClass: 'nb-lightbulb',
            type: 'warning',
        };
        this.commonStatusCardsSet = [
            this.lightCard,
            this.rollerShadesCard,
            this.wirelessAudioCard,
            this.coffeeMakerCard,
        ];
        this.statusCardsByThemes = {
            default: this.commonStatusCardsSet,
            cosmic: this.commonStatusCardsSet,
            corporate: [
                Object.assign({}, this.lightCard, { type: 'warning' }),
                Object.assign({}, this.rollerShadesCard, { type: 'primary' }),
                Object.assign({}, this.wirelessAudioCard, { type: 'danger' }),
                Object.assign({}, this.coffeeMakerCard, { type: 'info' }),
            ],
            dark: this.commonStatusCardsSet,
        };
        this.themeService.getJsTheme()
            .pipe(takeWhile(() => this.alive))
            .subscribe(theme => {
            this.statusCards = this.statusCardsByThemes[theme.name];
        });
        this.solarValue = 42;
    }
    ngOnDestroy() {
        this.alive = false;
    }
};
DashboardComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-dashboard',
        styleUrls: ['./dashboard.component.scss'],
        templateUrl: './dashboard.component.html',
    }),
    tslib_1.__metadata("design:paramtypes", [NbThemeService])
], DashboardComponent);
export { DashboardComponent };
//# sourceMappingURL=dashboard.component.js.map