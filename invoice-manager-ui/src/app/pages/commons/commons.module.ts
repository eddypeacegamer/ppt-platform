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
  NbTreeGridModule,
  NbSpinnerModule,
} from '@nebular/theme';

import { ClientesComponent } from './clientes/clientes.component';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { PagosFacturaComponent } from './pagos-factura/pagos-factura.component';
import { ConceptosComponent } from './conceptos/conceptos.component';
import { InvoiceReportsComponent } from './invoice-reports/invoice-reports.component';
import { DevolutionsDetailsComponent } from './devolutions-details/devolutions-details.component';
import { CfdiComponent } from './cfdi/cfdi.component';

@NgModule({
  declarations: [
    ClientesComponent,
    ClienteComponent,
    EmpresaComponent,
    EmpresasComponent,
    PagosFacturaComponent,
    ConceptosComponent,
    InvoiceReportsComponent,
    DevolutionsDetailsComponent,
    CfdiComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
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
    NbDialogModule.forChild(),
    NbIconModule,
    NbSpinnerModule,
    NbTreeGridModule,
  ],
  exports: [
    ClientesComponent,
    ClienteComponent,
    EmpresaComponent,
    EmpresasComponent,
    InvoiceReportsComponent,
    DevolutionsDetailsComponent,
    PagosFacturaComponent,
    ConceptosComponent,
    CfdiComponent,
    FormsModule,
    CommonModule,
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
    NbSpinnerModule,
    NbTreeGridModule,
  ]})
export class CommonsModule { }
