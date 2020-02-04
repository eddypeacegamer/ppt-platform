import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {ClientesComponent} from './clientes/clientes.component';
import { OperacionesComponent } from './operaciones.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { ReportesComponent } from './reportes/reportes.component';
import { RevisionComponent } from './revision/revision.component';
import { CargaXmlComponent } from './carga-xml/carga-xml.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { ClienteComponent } from './cliente/cliente.component';
const routes: Routes = [{
  path: '',
  component: OperacionesComponent,
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
      path: 'revision/:folio',
      component : RevisionComponent,
    },{
      path: 'carga-xml',
      component : CargaXmlComponent,
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OperacionesRoutingModule { }
