import * as tslib_1 from "tslib";
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
const routes = [{
        path: '',
        component: PagesComponent,
        children: [
            {
                path: 'dashboard',
                component: DashboardComponent,
            },
            {
                path: '',
                redirectTo: 'dashboard',
                pathMatch: 'full',
            },
            {
                path: 'promotor',
                loadChildren: () => import('./promotor/promotor.module')
                    .then(m => m.PromotorModule),
            },
            {
                path: 'operaciones',
                loadChildren: () => import('./operaciones/operaciones.module')
                    .then(m => m.OperacionesModule),
            },
            {
                path: 'contabilidad',
                loadChildren: () => import('./contabilidad/contabilidad.module')
                    .then(m => m.ContabilidadModule),
            },
            {
                path: 'tesoreria',
                loadChildren: () => import('./tesoreria/tesoreria.module')
                    .then(m => m.TesoreriaModule),
            },
            { path: 'administracion',
                loadChildren: () => import('./administracion/administracion.module')
                    .then(m => m.AdministracionModule)
            },
            {
                path: '**',
                component: DashboardComponent,
            }
        ],
    }];
let PagesRoutingModule = class PagesRoutingModule {
};
PagesRoutingModule = tslib_1.__decorate([
    NgModule({
        imports: [RouterModule.forChild(routes)],
        exports: [RouterModule],
    })
], PagesRoutingModule);
export { PagesRoutingModule };
//# sourceMappingURL=pages-routing.module.js.map