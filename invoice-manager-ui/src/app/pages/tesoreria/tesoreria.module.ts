import { NgModule } from '@angular/core';
import { TesoreriaRoutingModule } from './tesoreria-routing.module';
import { TesoreriaComponent } from './tesoreria.component';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { ValidacionPagoComponent } from './pagos/validacion-pago/validacion-pago.component';
import { PagosComponent } from './pagos/pagos.component';
import { IngresosComponent } from './ingresos/ingresos.component';
import { EgresosComponent } from './egresos/egresos.component';
import { ConciliacionComponent } from './conciliacion/conciliacion.component';
import { CommonsModule } from '../commons/commons.module';
import { DonwloadFileService } from '../../@core/util-services/download-file-service';
import { DevolutionRequestsComponent } from './devolution-requests/devolution-requests.component';
import { ValidacionDevolucionComponent } from './devolution-requests/validacion-devolucion/validacion-devolucion.component';


@NgModule({
  declarations: [
    TesoreriaComponent,
    DevolutionRequestsComponent,
    PagosComponent ,
    ValidacionPagoComponent,
    IngresosComponent,
    EgresosComponent,
    ConciliacionComponent,
    ValidacionDevolucionComponent,
  ],
  imports: [
    TesoreriaRoutingModule,
    CommonsModule,
  ],
  entryComponents: [
    ValidacionPagoComponent,
    ValidacionDevolucionComponent],
  providers: [ DownloadCsvService , DonwloadFileService ],
})
export class TesoreriaModule { }
