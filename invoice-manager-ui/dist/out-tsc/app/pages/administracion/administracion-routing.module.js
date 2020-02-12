import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
const routes = [{
        path: '',
        component: AdministracionComponent,
        children: [
            {
                path: 'users',
                component: UsersComponent,
            }
        ]
    }];
let AdministracionRoutingModule = class AdministracionRoutingModule {
};
AdministracionRoutingModule = tslib_1.__decorate([
    NgModule({
        imports: [RouterModule.forChild(routes)],
        exports: [RouterModule]
    })
], AdministracionRoutingModule);
export { AdministracionRoutingModule };
//# sourceMappingURL=administracion-routing.module.js.map