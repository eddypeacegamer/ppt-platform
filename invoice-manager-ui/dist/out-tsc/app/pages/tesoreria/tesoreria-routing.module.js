import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ValidacionPagosComponent } from './validacion-pagos/validacion-pagos.component';
import { IngresosComponent } from './ingresos/ingresos.component';
import { EgresosComponent } from './egresos/egresos.component';
import { ConciliacionComponent } from './conciliacion/conciliacion.component';
const routes = [{
        path: '',
        component: TesoreriaComponent,
        children: [
            {
                path: 'validacion-pagos',
                component: ValidacionPagosComponent,
            },
            {
                path: 'devoluciones',
                component: DevolucionesComponent,
            },
            {
                path: 'ingresos',
                component: IngresosComponent
            }, {
                path: 'egresos',
                component: EgresosComponent
            }, {
                path: 'conciliacion',
                component: ConciliacionComponent
            }
        ]
    }];
let TesoreriaRoutingModule = class TesoreriaRoutingModule {
};
TesoreriaRoutingModule = tslib_1.__decorate([
    NgModule({
        imports: [RouterModule.forChild(routes)],
        exports: [RouterModule]
    })
], TesoreriaRoutingModule);
export { TesoreriaRoutingModule };
//# sourceMappingURL=tesoreria-routing.module.js.map