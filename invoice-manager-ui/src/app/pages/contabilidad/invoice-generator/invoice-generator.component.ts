import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { TransferData } from '../../../@core/data/transfers-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { NbDialogService } from '@nebular/theme';
import { Transferencia } from '../../../models/transferencia';
import { InvoiceRequestComponent } from './invoice-request/invoice-request.component';

@Component({
  selector: 'ngx-invoice-generator',
  templateUrl: './invoice-generator.component.html',
  styleUrls: ['./invoice-generator.component.scss']
})
export class InvoiceGeneratorComponent implements OnInit {

  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams: any = { tipoEmisor: 'B', tipoReceptor: 'A', since: '', to: '' };

  constructor(private tranferService: TransferData,
    private donwloadService: DownloadCsvService,
    private dialogService: NbDialogService) { }

  ngOnInit() {
    this.filterParams = { tipoEmisor: 'B', tipoReceptor: 'A', since: '', to: '' };
    this.updateDataTable();
  }


  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.tranferService.getAllTransfers(pageValue, sizeValue, this.filterParams)
      .subscribe((result: GenericPage<any>) => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.tranferService.getAllTransfers(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Facturas_a_generar');
    });
  }

  public openDialog(transferencia: Transferencia ) {
    this.dialogService.open(InvoiceRequestComponent, {
      context: {
        transfer: transferencia,
      },
    }).onClose.subscribe(()=> this.updateDataTable(this.page.number,this.page.size));
  }

}
