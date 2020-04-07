import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
import { DevolutionsAdjustmentComponent } from './devolutions-adjustment/devolutions-adjustment.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { InvoiceReportsComponent } from '../commons/invoice-reports/invoice-reports.component';

const routes: Routes = [{
  path: '',
  component: AdministracionComponent,
  children: [
    {
      path: 'usuarios',
      component: UsersComponent,
    }, {
      path: 'clientes',
      component: ClientesComponent,
    }, {
      path: 'cliente/:rfc',
      component: ClienteComponent,
    }, {
      path: 'reportes/:status',
      component: InvoiceReportsComponent,
    }, {
      path: 'devoluciones/:folio/ajustes',
      component: DevolutionsAdjustmentComponent,
    }]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdministracionRoutingModule { }
