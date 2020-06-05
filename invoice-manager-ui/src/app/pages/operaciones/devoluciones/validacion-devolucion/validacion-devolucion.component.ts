import { Component, OnInit, Input } from '@angular/core';
import { PagoDevolucion } from '../../../../models/pago-devolucion';
import { NbDialogRef } from '@nebular/theme';
import { HttpErrorResponse } from '@angular/common/http';
import { DevolutionData } from '../../../../@core/data/devolution-data';
import { UsersData } from '../../../../@core/data/users-data';
import { DonwloadFileService } from '../../../../@core/util-services/download-file-service';
import { FilesData } from '../../../../@core/data/files-data';
import { ResourceFile } from '../../../../models/resource-file';
import { User } from '../../../../models/user';

@Component({
  selector: 'ngx-validacion-devolucion',
  templateUrl: './validacion-devolucion.component.html',
  styleUrls: ['./validacion-devolucion.component.scss']
})
export class ValidacionDevolucionComponent implements OnInit {

  @Input() payment: PagoDevolucion;
  public errorMesage: string;
  public user: User;

  constructor(protected ref: NbDialogRef<ValidacionDevolucionComponent>,
    private downloadService: DonwloadFileService,
    private resourceService: FilesData,
    private userService: UsersData,
    private devolutionsService: DevolutionData) { }

  ngOnInit() {
    this.errorMesage = '';
    this.userService.getUserInfo().then(user => this.user = user);
  }

  exit() {
    this.ref.close();
  }

  acceptDevolution() {
    this.errorMesage = '';
    const solicitud = {... this.payment};
    solicitud.autorizador = this.user.email;
    solicitud.status = 'ACEPTADO';
    this.devolutionsService.updateDevolution(this.payment.id, solicitud)
      .subscribe(success => this.ref.close(),
      (error: HttpErrorResponse) => this.errorMesage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  rejectDevolution() {
    this.errorMesage = '';
    const solicitud = {... this.payment};
    solicitud.autorizador = this.user.email;
    solicitud.status = 'RECHAZADO';
    this.devolutionsService.updateDevolution(this.payment.id, solicitud)
      .subscribe(success => this.ref.close(),
      (error: HttpErrorResponse) => this.errorMesage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  public donwloadRefFile(referencia: string) {
    this.resourceService.getResourceFile(referencia, 'DEVOLUCION', 'ARCHIVO')
          .subscribe((file: ResourceFile) => {
            const fileType = file.data.substring(5, file.data.indexOf('base64') + 7);
            this.downloadService.downloadFile(file.data.replace('data:' + fileType, ''),
              this.payment.referencia, fileType);
          });
  }

}
