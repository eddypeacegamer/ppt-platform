import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { OperacionesRoutingModule } from './operaciones-routing.module';
import { OperacionesComponent } from './operaciones.component';
import { RevisionComponent } from './revision/revision.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CommonsModule } from '../commons/commons.module';
let OperacionesModule = class OperacionesModule {
};
OperacionesModule = tslib_1.__decorate([
    NgModule({
        declarations: [OperacionesComponent, RevisionComponent, ReportesComponent],
        imports: [
            OperacionesRoutingModule,
            CommonsModule,
        ],
    })
], OperacionesModule);
export { OperacionesModule };
//# sourceMappingURL=operaciones.module.js.map