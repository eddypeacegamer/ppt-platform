import { NgModule } from '@angular/core';
import { NbDialogModule } from '@nebular/theme';

import { ContabilidadRoutingModule } from './contabilidad-routing.module';
import { ContabilidadComponent } from './contabilidad.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { CommonsModule } from '../commons/commons.module';
import { DonwloadFileService } from '../../@core/util-services/download-file-service';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { CargaMasivaComponent } from './carga-masiva/carga-masiva.component';

@NgModule({
  declarations: [ContabilidadComponent,
    PreCfdiComponent,
    CargaMasivaComponent],
  imports: [
    ContabilidadRoutingModule,
    CommonsModule,
    NbDialogModule.forChild(),
  ],
  providers: [ DownloadCsvService , DonwloadFileService ],
})
export class ContabilidadModule { }
