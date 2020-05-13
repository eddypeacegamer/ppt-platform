import { NgModule } from '@angular/core';
import { OperacionesRoutingModule } from './operaciones-routing.module';
import { OperacionesComponent } from './operaciones.component';
import { RevisionComponent } from './revision/revision.component';
import { CommonsModule } from '../commons/commons.module';

import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { DonwloadFileService } from '../../@core/util-services/download-file-service';
import { LineaBComponent } from './linea-b/linea-b.component';
import { LineaCComponent } from './linea-c/linea-c.component';



@NgModule({
  declarations: [
    OperacionesComponent,
    RevisionComponent,
    LineaBComponent,
    LineaCComponent],
  imports: [
    OperacionesRoutingModule,
    CommonsModule,
  ],
  providers: [ DownloadCsvService , DonwloadFileService ],
})
export class OperacionesModule { }
