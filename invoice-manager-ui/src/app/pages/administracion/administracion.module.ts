import { NgModule } from '@angular/core';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service'
import { AdministracionRoutingModule } from './administracion-routing.module';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
import { CommonsModule } from '../commons/commons.module';
import { UserComponent } from './user/user.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NbTreeGridModule } from '@nebular/theme';


import { TestComponent } from './solicitudes12/test/test.component';

@NgModule({
  declarations: [
    AdministracionComponent,
    UsersComponent,
    UserComponent,
    TestComponent],
   
  imports: [
    AdministracionRoutingModule,
    CommonsModule,
    ReactiveFormsModule,
    NbTreeGridModule
  ],
  providers: [ DownloadCsvService ],
})
export class AdministracionModule { }
