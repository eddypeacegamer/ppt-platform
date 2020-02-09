import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PromotorComponent } from './promotor.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { ReportesComponent } from './reportes/reportes.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
const routes = [{
        path: '',
        component: PromotorComponent,
        children: [
            {
                path: 'precfdi/:folio',
                component: PreCfdiComponent,
            }, {
                path: 'clientes',
                component: ClientesComponent,
            }, {
                path: 'cliente/:rfc',
                component: ClienteComponent,
            }, {
                path: 'reportes',
                component: ReportesComponent,
            }, {
                path: 'devoluciones',
                component: DevolucionesComponent,
            },
        ]
    }];
let PromotorRoutingModule = class PromotorRoutingModule {
};
PromotorRoutingModule = tslib_1.__decorate([
    NgModule({
        imports: [RouterModule.forChild(routes)],
        exports: [RouterModule],
    })
], PromotorRoutingModule);
export { PromotorRoutingModule };
//# sourceMappingURL=promotor-routing.module.js.map