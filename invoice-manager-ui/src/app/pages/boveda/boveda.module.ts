import { NgModule } from '@angular/core';
import { BovedaComponent } from './boveda.component';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { CommonsModule } from '../commons/commons.module';
import { SolicitudesComponent } from './solicitudes/solicitudes.component';
import { BovedaRoutingModule } from './boveda-routing.module';
import { ValidacionDevolucionComponent } from './solicitudes/validacion-devolucion/validacion-devolucion.component';

@NgModule({
  declarations: [
    BovedaComponent,
    SolicitudesComponent,
    ValidacionDevolucionComponent,
  ],
  imports: [
    BovedaRoutingModule,
    CommonsModule,
  ],
  entryComponents: [
    ValidacionDevolucionComponent],
  providers: [DownloadCsvService],
})
export class BovedaModule { }
