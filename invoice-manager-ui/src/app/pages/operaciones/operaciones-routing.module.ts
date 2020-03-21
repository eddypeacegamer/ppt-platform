import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OperacionesComponent } from './operaciones.component';
import { ReportesComponent } from './reportes/reportes.component';
import { RevisionComponent } from './revision/revision.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { EmpresasComponent } from '../commons/empresas/empresas.component';
import { EmpresaComponent } from '../commons/empresa/empresa.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ValidateInvoicesComponent } from './validate-invoices/validate-invoices.component';
import { StampInvoicesComponent } from './stamp-invoices/stamp-invoices.component';
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
      path: 'reportes',
      component : ReportesComponent,
    }, {
      path: 'validacion',
      component : ValidateInvoicesComponent,
    }, {
      path: 'timbrar-facturas',
      component : StampInvoicesComponent,
    }, {
      path: 'revision/:folio',
      component : RevisionComponent,
    }]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperacionesRoutingModule { }
