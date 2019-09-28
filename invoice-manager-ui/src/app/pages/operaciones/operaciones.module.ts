import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
  NbStepperModule,
  NbDialogModule,
  NbIconModule
} from '@nebular/theme';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { OperacionesRoutingModule } from './operaciones-routing.module';
import { OperacionesComponent } from './operaciones.component';
import { ClientesComponent } from './clientes/clientes.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { RevisionComponent } from './revision/revision.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { PagosComponent } from './pagos/pagos.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CargaXmlComponent } from './carga-xml/carga-xml.component';


@NgModule({
  declarations: [OperacionesComponent, ClientesComponent, EmpresasComponent, RevisionComponent, DevolucionesComponent, PagosComponent, ReportesComponent, CargaXmlComponent],
  imports: [
    CommonModule,
    FormsModule,
    OperacionesRoutingModule,
    NbActionsModule,
    NbButtonModule,
    NbCardModule,
    NbCheckboxModule,
    NbDatepickerModule, 
    NbIconModule,
    NbInputModule,
    NbRadioModule,
    NbSelectModule,
    NbUserModule,
    NbStepperModule,
    Ng2SmartTableModule,
    NbDialogModule
  ]
})
export class OperacionesModule { }
