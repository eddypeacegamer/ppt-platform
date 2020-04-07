import { NgModule } from '@angular/core';
import { TesoreriaRoutingModule } from './tesoreria-routing.module';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { ValidacionPagoComponent } from './validacion-pagos/validacion-pago/validacion-pago.component';
import { ValidacionPagosComponent } from './validacion-pagos/validacion-pagos.component';
import { IngresosComponent } from './ingresos/ingresos.component';
import { EgresosComponent } from './egresos/egresos.component';
import { ConciliacionComponent } from './conciliacion/conciliacion.component';
import { ValidacionDevolucionComponent } from './devoluciones/validacion-devolucion/validacion-devolucion.component';
import { CommonsModule } from '../commons/commons.module';
import { DonwloadFileService } from '../../@core/util-services/download-file-service';


@NgModule({
  declarations: [
    TesoreriaComponent,
    DevolucionesComponent,
    ValidacionPagosComponent ,
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
