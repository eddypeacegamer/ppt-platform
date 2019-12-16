import { Component, OnInit, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { DomSanitizer } from '@angular/platform-browser';
import { Transferencia } from '../../../../models/transferencia';

@Component({
  selector: 'ngx-invoice-request',
  templateUrl: './invoice-request.component.html',
  styleUrls: ['./invoice-request.component.scss']
})
export class InvoiceRequestComponent implements OnInit {

  @Input() transfer:Transferencia;

  constructor(protected ref: NbDialogRef<InvoiceRequestComponent>,
    private sanitizer: DomSanitizer) {}


  ngOnInit() {
  }

}
