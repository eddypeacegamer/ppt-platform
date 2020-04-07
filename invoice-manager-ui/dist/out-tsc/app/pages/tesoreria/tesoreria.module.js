import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NbActionsModule, NbButtonModule, NbCardModule, NbCheckboxModule, NbDatepickerModule, NbInputModule, NbRadioModule, NbSelectModule, NbUserModule, NbStepperModule, NbDialogModule, NbIconModule } from '@nebular/theme';
import { TesoreriaRoutingModule } from './tesoreria-routing.module';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { ValidacionPagoComponent } from './validacion-pagos/validacion-pago/validacion-pago.component';
import { ValidacionPagosComponent } from './validacion-pagos/validacion-pagos.component';
import { IngresosComponent } from './ingresos/ingresos.component';
import { EgresosComponent } from './egresos/egresos.component';
import { ConciliacionComponent } from './conciliacion/conciliacion.component';
import { SolicitudDevolucionComponent } from './devoluciones/solicitud-devolucion/solicitud-devolucion.component';
let TesoreriaModule = class TesoreriaModule {
};
TesoreriaModule = tslib_1.__decorate([
    NgModule({
        declarations: [TesoreriaComponent, DevolucionesComponent, ValidacionPagosComponent, ValidacionPagoComponent, IngresosComponent, EgresosComponent, ConciliacionComponent, SolicitudDevolucionComponent],
        imports: [
            TesoreriaRoutingModule,
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
            NbDialogModule.forChild(),
        ],
        entryComponents: [ValidacionPagoComponent, SolicitudDevolucionComponent],
        providers: [DownloadCsvService]
    })
], TesoreriaModule);
export { TesoreriaModule };
//# sourceMappingURL=tesoreria.module.js.map