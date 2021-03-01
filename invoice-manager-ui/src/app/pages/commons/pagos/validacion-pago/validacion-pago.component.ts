import { Component, OnInit, Input, SecurityContext } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { PagoBase } from '../../../../models/pago-base';
import { FilesData } from '../../../../@core/data/files-data';
import { DomSanitizer, SafeUrl, SafeResourceUrl } from '@angular/platform-browser';
import { DonwloadFileService } from '../../../../@core/util-services/download-file-service';
import { Router } from '@angular/router';
@Component({
  selector: 'ngx-validacion-pago',
  templateUrl: './validacion-pago.component.html',
  styleUrls: ['./validacion-pago.component.scss'],
})
export class ValidacionPagoComponent implements OnInit {


  @Input() pago: PagoBase;
  public comprobanteUrl: SafeUrl;
  public updatedPayment: PagoBase;
  public errors: string[] = [];
  public module : string = 'operaciones';

  public cosa: string;

  constructor(protected ref: NbDialogRef<ValidacionPagoComponent>,
    private filesService: FilesData,
    private router: Router,
    private downloadService: DonwloadFileService,
    private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.errors = [];
    this.module = this.router.url.split('/')[2];
    this.mostrarComprobante(this.pago);
    this.updatedPayment = { ... this.pago };
  }



  public mostrarComprobante(pago: PagoBase) {
    this.comprobanteUrl = undefined;
    if (pago.formaPago !== 'CREDITO' && pago.formaPago !== 'EFECTIVO') {
      this.filesService.getResourceFile(`${pago.id}`, 'PAGO', 'IMAGEN').subscribe(
        (file) => {
          this.comprobanteUrl = this.sanitizer.bypassSecurityTrustUrl(file.data)
          this.cosa = file.data;
        });
    }

  }
  cancel() {
    this.ref.close();
  }
  onRecahzarPago() {
    this.errors = [];

    if (this.updatedPayment.comentarioPago == undefined) {
      this.errors.push('Es necesaria una razon de rechazo.');
      return;
    }

    if (this.updatedPayment.comentarioPago.length < 6) {
      this.errors.push('Es necesaria un minimo de 5 caracteres.');
      return;
    }


    this.updatedPayment.statusPago = 'RECHAZADO';
    this.ref.close(this.updatedPayment);

  }

  onValidarPago() {
    this.ref.close(this.updatedPayment);
  }

  //TODO talvez mover a servicio de descarga de imagenes
  onDownload() {
    let dataType = this.cosa.split(';base64,')[0].replace('data:',''); 
    console.log('Downloading file : ', dataType);
    this.downloadService.downloadFile(this.cosa.split(';base64,')[1],`${this.pago.banco}-${this.pago.acredor}-${this.pago.fechaPago}.${dataType.split('/')[1]}`,dataType);
  }
}
