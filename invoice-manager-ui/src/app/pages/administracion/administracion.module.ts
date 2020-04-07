import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
  NbStepperModule,
  NbDialogModule,
  NbIconModule
} from '@nebular/theme';

import { DownloadCsvService } from '../../@core/util-services/download-csv.service'
import { AdministracionRoutingModule } from './administracion-routing.module';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
import { DevolutionsAdjustmentComponent } from './devolutions-adjustment/devolutions-adjustment.component';
import { CommonsModule } from '../commons/commons.module';


@NgModule({
  declarations: [
    AdministracionComponent,
    UsersComponent,
    DevolutionsAdjustmentComponent],
  imports: [
    AdministracionRoutingModule,
    CommonsModule,
  ],
  providers: [ DownloadCsvService ],
})
export class AdministracionModule { }
