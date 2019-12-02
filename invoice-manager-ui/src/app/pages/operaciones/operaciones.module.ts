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
  NbIconModule,
  NbSpinnerModule
} from '@nebular/theme';

import { OperacionesRoutingModule } from './operaciones-routing.module';
import { OperacionesComponent } from './operaciones.component';
import { ClientesComponent } from './clientes/clientes.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { RevisionComponent } from './revision/revision.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CargaXmlComponent } from './carga-xml/carga-xml.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { ClienteComponent } from './cliente/cliente.component';


@NgModule({
  declarations: [OperacionesComponent, ClientesComponent, EmpresasComponent, RevisionComponent, ReportesComponent, CargaXmlComponent, EmpresaComponent, ClienteComponent],
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
    NbDialogModule,
    NbSpinnerModule
  ]
})
export class OperacionesModule { }
