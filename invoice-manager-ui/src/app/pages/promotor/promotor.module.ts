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

import { PromotorRoutingModule } from './promotor-routing.module';
import { PromotorComponent } from './promotor.component';
import { ClientesComponent } from './clientes/clientes.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { PagosComponent } from './pagos/pagos.component';
import { ReportesComponent } from './reportes/reportes.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component'

import {DownloadCsvService } from '../../@core/back-services/download-csv.service';
import { DownloadInvoiceFilesService } from '../../@core/back-services/download-invoice-files';


@NgModule({
  declarations: [PromotorComponent, ClientesComponent, PreCfdiComponent, PagosComponent, ReportesComponent, DevolucionesComponent],
  imports: [
    PromotorRoutingModule,
    
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
    NbDialogModule,
  ],
  providers:[DownloadCsvService,DownloadInvoiceFilesService]
})
export class PromotorModule { }
