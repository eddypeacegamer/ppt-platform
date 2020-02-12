import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { NbActionsModule, NbButtonModule, NbCardModule, NbTabsetModule, NbUserModule, NbRadioModule, NbSelectModule, NbListModule, NbIconModule, } from '@nebular/theme';
import { NgxEchartsModule } from 'ngx-echarts';
import { ThemeModule } from '../../@theme/theme.module';
import { DashboardComponent } from './dashboard.component';
import { FormsModule } from '@angular/forms';
import { StatusCardComponent } from './status-card/status-card.component';
let DashboardModule = class DashboardModule {
};
DashboardModule = tslib_1.__decorate([
    NgModule({
        imports: [
            FormsModule,
            ThemeModule,
            NbCardModule,
            NbUserModule,
            NbButtonModule,
            NbTabsetModule,
            NbActionsModule,
            NbRadioModule,
            NbSelectModule,
            NbListModule,
            NbIconModule,
            NbButtonModule,
            NgxEchartsModule,
        ],
        declarations: [
            DashboardComponent,
            StatusCardComponent,
        ],
    })
], DashboardModule);
export { DashboardModule };
//# sourceMappingURL=dashboard.module.js.map