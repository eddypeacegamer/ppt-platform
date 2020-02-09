import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OperacionesComponent } from './operaciones.component';
import { ReportesComponent } from './reportes/reportes.component';
import { RevisionComponent } from './revision/revision.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { EmpresasComponent } from '../commons/empresas/empresas.component';
import { EmpresaComponent } from '../commons/empresa/empresa.component';
const routes = [{
        path: '',
        component: OperacionesComponent,
        children: [{
                path: 'clientes',
                component: ClientesComponent,
            }, {
                path: 'cliente/:rfc',
                component: ClienteComponent,
            }, {
                path: 'empresas',
                component: EmpresasComponent,
            }, {
                path: 'empresa/:rfc',
                component: EmpresaComponent,
            }, {
                path: 'reportes',
                component: ReportesComponent,
            }, {
                path: 'revision/:folio',
                component: RevisionComponent,
            }]
    }];
let OperacionesRoutingModule = class OperacionesRoutingModule {
};
OperacionesRoutingModule = tslib_1.__decorate([
    NgModule({
        imports: [RouterModule.forChild(routes)],
        exports: [RouterModule],
    })
], OperacionesRoutingModule);
export { OperacionesRoutingModule };
//# sourceMappingURL=operaciones-routing.module.js.map