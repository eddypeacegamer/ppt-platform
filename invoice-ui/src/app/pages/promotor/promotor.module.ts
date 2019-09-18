import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
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

import { PromotorRoutingModule } from './promotor-routing.module';
import { PromotorComponent } from './promotor.component';
import { ClientesComponent } from './clientes/clientes.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { PagosComponent } from './pagos/pagos.component';
import { ReportesComponent } from './reportes/reportes.component';

import {DownloadCsvService } from '../../@core/back-services/download-csv.service'

@NgModule({
  declarations: [PromotorComponent, ClientesComponent, PreCfdiComponent, PagosComponent, ReportesComponent],
  imports: [
    CommonModule,
    PromotorRoutingModule,
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
    NbDialogModule,
  ],
  providers:[DownloadCsvService]
})
export class PromotorModule { }
