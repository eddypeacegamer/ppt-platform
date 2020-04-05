import { Component, OnInit, Input } from '@angular/core';
import { PagoDevolucion } from '../../../../models/pago-devolucion';
import { NbDialogRef } from '@nebular/theme';
import { HttpErrorResponse } from '@angular/common/http';
import { DevolutionData } from '../../../../@core/data/devolution-data';
import { CuentasData } from '../../../../@core/data/cuentas-data';
import { User, UsersData } from '../../../../@core/data/users-data';
import { Cuenta } from '../../../../models/cuenta';
import { DonwloadFileService } from '../../../../@core/util-services/download-file-service';
import { FilesData } from '../../../../@core/data/files-data';
import { ResourceFile } from '../../../../models/resource-file';

@Component({
  selector: 'ngx-validacion-devolucion',
  templateUrl: './validacion-devolucion.component.html',
  styleUrls: ['./validacion-devolucion.component.scss'],
})
export class ValidacionDevolucionComponent implements OnInit {

  @Input() payment: PagoDevolucion;
  public user: User;
  public errorMesage: string;
  public cuentas = [];

  public formInfo: any = { rfc: '', empresa: '*', cuenta: '*'};

  constructor(protected ref: NbDialogRef<ValidacionDevolucionComponent>,
    private downloadService: DonwloadFileService,
    private resourceService: FilesData,
    private devolutionsService: DevolutionData,
    private userService: UsersData,
    private accountsService: CuentasData) { }
  ngOnInit() {
    this.errorMesage = '';
    this.getAccountInfo();
    this.userService.getUserInfo().then(user => this.user = user);
  }


  exit() {
    this.ref.close();
  }

  companySearch(rfc: string) {
    if (rfc !== undefined && rfc.length >= 3) {
      this.getAccountInfo(rfc);
    }
    if (rfc !== undefined && rfc.length === 0) {
      this.getAccountInfo();
    }
  }
  private getAccountInfo(rfc?: string) {
    this.accountsService.getAllCuentas(0, 25, { empresa: rfc || '' })
      .subscribe(accounts => {
        this.cuentas = accounts.content;
        if (!accounts.empty) {
          this.formInfo.cuenta = this.cuentas[0].id;
        } else {
          this.cuentas = [];
          this.formInfo.cuenta = '*';
        }
      });
  }

  acceptDevolution() {
    this.errorMesage = '';
    if (this.cuentas.length > 0) {
      const account: Cuenta = this.cuentas.find(c => c.id === this.formInfo.cuenta);
      const solicitud = { ... this.payment };

      solicitud.autorizador = this.user.email;
      if (solicitud.fechaPago === undefined) {
        this.errorMesage = 'La fecha de pago es requerida';
      } else {
        solicitud.status = 'PAGADO';
        solicitud.cuentaPago = account.cuenta;
        solicitud.rfcEmpresa = account.empresa;
        this.devolutionsService.updateDevolution(this.payment.id, solicitud)
          .subscribe(success => this.ref.close(),
            (error: HttpErrorResponse) => this.errorMesage = error.error.message
              || `${error.statusText} : ${error.message}`);
      }

    } else {
      this.errorMesage = 'No hay informacion de cuentas disponible';
    }
  }

  rejectDevolution() {
    this.errorMesage = '';
    const solicitud = { ... this.payment };
    solicitud.autorizador = this.user.email;
    solicitud.status = 'RECHAZADO';
    this.devolutionsService.updateDevolution(this.payment.id, solicitud)
      .subscribe(success => this.ref.close(),
        (error: HttpErrorResponse) => this.errorMesage = error.error.message
            || `${error.statusText} : ${error.message}`);
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
