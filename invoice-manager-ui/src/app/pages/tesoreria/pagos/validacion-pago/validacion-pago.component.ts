import { Component, OnInit, Input, SecurityContext } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { PagoBase } from '../../../../models/pago-base';
import { FilesData } from '../../../../@core/data/files-data';
import { DomSanitizer, SafeUrl, SafeResourceUrl } from '@angular/platform-browser';
import { DonwloadFileService } from '../../../../@core/util-services/download-file-service';
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

  public cosa: string;

  constructor(protected ref: NbDialogRef<ValidacionPagoComponent>,
    private filesService: FilesData,
    private downloadService: DonwloadFileService,
    private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.errors = [];
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
    const blobData = this.convertBase64ToBlobData(this.cosa.replace(/^data:image\/(png|jpeg|jpg);base64,/, ''));
    let filename = `${this.pago.fechaPago}.jpeg`;
    let contentType = 'image/jpeg';
    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
      window.navigator.msSaveOrOpenBlob(blobData, filename);
    } else {
      const blob = new Blob([blobData], { type: contentType });
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = filename;
      link.click();
    }
  }

  convertBase64ToBlobData(base64Data: string, contentType: string = 'image/png', sliceSize = 512) {
    const byteCharacters = atob(base64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);

      byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: contentType });
    return blob;
  }

}
