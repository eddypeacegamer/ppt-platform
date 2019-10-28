import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {ClientesComponent} from './clientes/clientes.component';
import { OperacionesComponent } from './operaciones.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { PagosComponent } from './pagos/pagos.component';
import { ReportesComponent } from './reportes/reportes.component';
import { RevisionComponent } from './revision/revision.component';
import { CargaXmlComponent } from './carga-xml/carga-xml.component';
import { EmpresaComponent } from './empresa/empresa.component';
const routes: Routes = [{
  path: '',
  component: OperacionesComponent,
  children: [
    {
      path: 'clientes',
      component: ClientesComponent,
    },
    {
      path: 'empresas',
      component: EmpresasComponent,
    },
    {
      path: 'empresa/:rfc',
      component: EmpresaComponent,
    },
    {
      path: 'devoluciones',
      component: DevolucionesComponent,
    },{
      path: 'pagos',
      component : PagosComponent,
    },{
      path: 'reportes',
      component : ReportesComponent,
    },{
      path: 'revision',
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
