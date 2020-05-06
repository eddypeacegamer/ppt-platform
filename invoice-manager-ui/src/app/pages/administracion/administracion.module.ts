import { NgModule } from '@angular/core';
import { DownloadCsvService } from '../../@core/util-services/download-csv.service'
import { AdministracionRoutingModule } from './administracion-routing.module';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';
import { DevolutionsDetailsComponent } from '../commons/devolutions-details/devolutions-details.component';
import { CommonsModule } from '../commons/commons.module';


@NgModule({
  declarations: [
    AdministracionComponent,
    UsersComponent,
    DevolutionsDetailsComponent],
  imports: [
    AdministracionRoutingModule,
    CommonsModule,
  ],
  providers: [ DownloadCsvService ],
})
export class AdministracionModule { }
