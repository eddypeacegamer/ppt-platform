import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContabilidadComponent } from './contabilidad.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { CargaMasivaComponent } from './carga-masiva/carga-masiva.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { EmpresasComponent } from '../commons/empresas/empresas.component';
import { EmpresaComponent } from '../commons/empresa/empresa.component';
import { InvoiceReportsComponent } from '../commons/invoice-reports/invoice-reports.component';

const routes: Routes = [{
  path: '',
  component: ContabilidadComponent,
  children: [
    {
      path: 'clientes',
      component: ClientesComponent,
    },
    {
      path: 'cliente/:rfc',
      component: ClienteComponent,
    },
    {
      path: 'empresas',
      component: EmpresasComponent,
    },
    {
      path: 'empresa/:rfc',
      component: EmpresaComponent,
    }, {
      path: 'reportes/:status',
      component : InvoiceReportsComponent,
    }, {
      path: 'cfdi/:folio',
      component : PreCfdiComponent,
    }, {
      path: 'carga-facturas',
      component : CargaMasivaComponent,
    },
  ]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContabilidadRoutingModule { }
