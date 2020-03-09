import { NgModule } from '@angular/core';
import { NbDialogModule } from '@nebular/theme';

import { ContabilidadRoutingModule } from './contabilidad-routing.module';
import { ContabilidadComponent } from './contabilidad.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { ReportesComponent } from './reportes/reportes.component';
import { InvoiceGeneratorComponent } from './invoice-generator/invoice-generator.component';
import { InvoiceRequestComponent } from './invoice-generator/invoice-request/invoice-request.component';
import { TransferenciasComponent } from './transferencias/transferencias.component';
import { CommonsModule } from '../commons/commons.module';
import { DonwloadFileService } from '../../@core/util-services/download-file-service';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';

@NgModule({
  declarations: [ContabilidadComponent,
    PreCfdiComponent,
    ReportesComponent,
    InvoiceGeneratorComponent,
    InvoiceRequestComponent,
    TransferenciasComponent],
  imports: [
    ContabilidadRoutingModule,
    CommonsModule,
    NbDialogModule.forChild(),
  ],
  entryComponents:[InvoiceRequestComponent],
  providers: [ DownloadCsvService , DonwloadFileService ],
})
export class ContabilidadModule { }
