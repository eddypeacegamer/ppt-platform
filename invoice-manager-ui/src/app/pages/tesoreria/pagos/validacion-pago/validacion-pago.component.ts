import { Component, OnInit, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { PagoFactura } from '../../../../models/pago-factura';
import { FilesData } from '../../../../@core/data/files-data';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'ngx-validacion-pago',
  templateUrl: './validacion-pago.component.html',
  styleUrls: ['./validacion-pago.component.scss'],
})
export class ValidacionPagoComponent implements OnInit {


  @Input() pago: PagoFactura;
  public comprobanteUrl: SafeUrl;
  public updatedPayment: PagoFactura;

  constructor(protected ref: NbDialogRef<ValidacionPagoComponent>,
    private filesService: FilesData,
    private sanitizer: DomSanitizer) {}

  ngOnInit() {
    this.mostrarComprobante(this.pago);
    this.updatedPayment = {... this.pago};
  }

  public mostrarComprobante(pago:PagoFactura){
    this.comprobanteUrl = undefined;
    if(pago.formaPago!='CREDITO'){
      this.filesService.getResourceFile(`${pago.id}_${pago.folio}`, 'PAGO', 'IMAGEN').subscribe(
        (file) => this.comprobanteUrl = this.sanitizer.bypassSecurityTrustUrl(file.data));
    }
  }
  cancel() {
    this.ref.close();
  }
  onRecahzarPago() {
    this.updatedPayment.statusPago = 'RECHAZADO';
    this.ref.close(this.updatedPayment);
  }

  onValidarPago() {
    this.ref.close(this.updatedPayment);
  }

}