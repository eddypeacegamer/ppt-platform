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

import { ContabilidadRoutingModule } from './contabilidad-routing.module';
import { ContabilidadComponent } from './contabilidad.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { ReportesComponent } from './reportes/reportes.component';
import { InvoiceGeneratorComponent } from './invoice-generator/invoice-generator.component';
import { InvoiceRequestComponent } from './invoice-generator/invoice-request/invoice-request.component';

@NgModule({
  declarations: [ContabilidadComponent, ClientesComponent, ClienteComponent, EmpresasComponent, EmpresaComponent, PreCfdiComponent, ReportesComponent, InvoiceGeneratorComponent, InvoiceRequestComponent],
  imports: [
    ContabilidadRoutingModule,
    CommonModule,
    FormsModule,
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
    NbDialogModule.forChild(),
    NbSpinnerModule
  ],
  entryComponents:[InvoiceRequestComponent]
})
export class ContabilidadModule { }
