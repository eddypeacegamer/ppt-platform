import { NgModule } from '@angular/core';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service'
import { AdministracionRoutingModule } from './administracion-routing.module';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
import { CommonsModule } from '../commons/commons.module';
import { UserComponent } from './user/user.component';


@NgModule({
  declarations: [
    AdministracionComponent,
    UsersComponent,
    UserComponent],
  imports: [
    AdministracionRoutingModule,
    CommonsModule,
  ],
  providers: [ DownloadCsvService ],
})
export class AdministracionModule { }
