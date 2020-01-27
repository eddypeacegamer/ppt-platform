import { NgModule } from '@angular/core';


import { PromotorRoutingModule } from './promotor-routing.module';
import { PromotorComponent } from './promotor.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { ReportesComponent } from './reportes/reportes.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';

import {DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { DownloadInvoiceFilesService } from '../../@core/util-services/download-invoice-files';
import { CommonsModule } from '../commons/commons.module';


@NgModule({
  declarations: [PromotorComponent,
    PreCfdiComponent,
    ReportesComponent,
    DevolucionesComponent],
  imports: [
    PromotorRoutingModule,
    CommonsModule,
  ],
  providers: [ DownloadCsvService , DownloadInvoiceFilesService ],
})
export class PromotorModule { }
