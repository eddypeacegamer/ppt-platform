import { NgModule } from '@angular/core';
import { OperacionesRoutingModule } from './operaciones-routing.module';
import { OperacionesComponent } from './operaciones.component';
import { RevisionComponent } from './revision/revision.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CommonsModule } from '../commons/commons.module';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ValidacionDevolucionComponent } from './devoluciones/validacion-devolucion/validacion-devolucion.component';



@NgModule({
  declarations: [
    OperacionesComponent,
    RevisionComponent,
    ReportesComponent,
    DevolucionesComponent,
    ValidacionDevolucionComponent],
  imports: [
    OperacionesRoutingModule,
    CommonsModule,
  ],
  entryComponents: [ValidacionDevolucionComponent],
})
export class OperacionesModule { }
