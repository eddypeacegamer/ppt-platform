import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContabilidadComponent } from './contabilidad.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CargaMasivaComponent } from './carga-masiva/carga-masiva.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';

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
      path: 'carga-masiva',
      component : CargaMasivaComponent,
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContabilidadRoutingModule { }
