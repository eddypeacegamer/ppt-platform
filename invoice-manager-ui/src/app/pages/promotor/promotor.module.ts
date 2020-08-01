import { NgModule } from '@angular/core';


import { PromotorRoutingModule } from './promotor-routing.module';
import { PromotorComponent } from './promotor.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';

import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { DonwloadFileService } from '../../@core/util-services/download-file-service';
import { CommonsModule } from '../commons/commons.module';
import { DevolutionPreferencesComponent } from './devolution-preferences/devolution-preferences.component';
import { AsignacionPagosComponent } from '../commons/asignacion-pagos/asignacion-pagos.component';


@NgModule({
  declarations: [PromotorComponent,
    PreCfdiComponent,
    DevolucionesComponent,
    DevolutionPreferencesComponent,
  ],
  imports: [
    PromotorRoutingModule,
    CommonsModule,
  ],
  entryComponents: [
    AsignacionPagosComponent,
  ],
  providers: [ DownloadCsvService , DonwloadFileService ],
})
export class PromotorModule { }
