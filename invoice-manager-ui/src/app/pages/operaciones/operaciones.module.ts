import { NgModule } from '@angular/core';
import { OperacionesRoutingModule } from './operaciones-routing.module';
import { OperacionesComponent } from './operaciones.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { EmpresasComponent } from '../commons/empresas/empresas.component';
import { RevisionComponent } from './revision/revision.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CargaXmlComponent } from './carga-xml/carga-xml.component';
import { EmpresaComponent } from '../commons/empresa/empresa.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { CommonsModule } from '../commons/commons.module';


@NgModule({
  declarations: [OperacionesComponent, RevisionComponent, ReportesComponent, CargaXmlComponent ],
  imports: [
    OperacionesRoutingModule,
    CommonsModule,
  ],
})
export class OperacionesModule { }
