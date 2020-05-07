import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OperacionesComponent } from './operaciones.component';
import { RevisionComponent } from './revision/revision.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { EmpresasComponent } from '../commons/empresas/empresas.component';
import { EmpresaComponent } from '../commons/empresa/empresa.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { InvoiceReportsComponent } from '../commons/invoice-reports/invoice-reports.component';
import { DevolutionsDetailsComponent } from '../commons/devolutions-details/devolutions-details.component';
const routes: Routes = [{
  path: '',
  component: OperacionesComponent,
  children: [{
      path: 'devoluciones',
      component : DevolucionesComponent,
    },
    {
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
      path: 'reportes/:status',
      component : InvoiceReportsComponent,
    }, {
      path: 'validacion/:status',
      component : InvoiceReportsComponent,
    }, {
      path: 'timbrar-facturas/:status',
      component : InvoiceReportsComponent,
    }, {
      path: 'revision/:folio',
      component : RevisionComponent,
    }, {
      path: 'facturas/:folio/devoluciones',
      component: DevolutionsDetailsComponent,
    }]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperacionesRoutingModule { }
