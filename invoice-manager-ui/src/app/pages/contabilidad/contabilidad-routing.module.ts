import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContabilidadComponent } from './contabilidad.component';
import { ReportesComponent } from './reportes/reportes.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { InvoiceGeneratorComponent } from './invoice-generator/invoice-generator.component';
import { TransferenciasComponent } from './transferencias/transferencias.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { EmpresasComponent } from '../commons/empresas/empresas.component';
import { EmpresaComponent } from '../commons/empresa/empresa.component';

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
    },{
      path: 'reportes',
      component : ReportesComponent,
    },{
      path: 'cfdi/:folio',
      component : PreCfdiComponent,
    },{
      path: 'carga-facturas',
      component : InvoiceGeneratorComponent,
    },{
      path:'transferencias',
      component : TransferenciasComponent,
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContabilidadRoutingModule { }
