import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NbActionsModule, NbButtonModule, NbCardModule, NbCheckboxModule, NbDatepickerModule, NbInputModule, NbRadioModule, NbSelectModule, NbUserModule, NbStepperModule, NbDialogModule, NbIconModule } from '@nebular/theme';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service';
import { AdministracionRoutingModule } from './administracion-routing.module';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
let AdministracionModule = class AdministracionModule {
};
AdministracionModule = tslib_1.__decorate([
    NgModule({
        declarations: [AdministracionComponent, UsersComponent],
        imports: [
            AdministracionRoutingModule,
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
            NbDialogModule,
        ],
        providers: [DownloadCsvService]
    })
], AdministracionModule);
export { AdministracionModule };
//# sourceMappingURL=administracion.module.js.map