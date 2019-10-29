import { Component, OnInit } from '@angular/core';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'
import { Router } from '@angular/router';


@Component({
  selector: 'ngx-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.scss']
})
export class ReportesComponent implements OnInit {


  public headers: string[] = ['Folio', 'RFC Emisor','Emisor', 'RFC Remitente','Remitente', 'Estatus Validacion', 'Estatus Pago','Total','Fecha Solicitud', 'Fecha Timbrado'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams : any = {emisor:'',remitente:'',folio:'',payStatus:'*',validationStatus:'*',start:'',end:''};

  constructor(private invoiceService: InvoicesData,
    private donwloadService:DownloadCsvService,
    private router: Router) {}

    ngOnInit() {
      this.updateDataTable();
    }
  

    public onPayStatus(payStatus:string){
        this.filterParams.payStatus=payStatus;
    }

    public onValidationStatus(validationStatus:string){
        this.filterParams.validationStatus=validationStatus;
    }

    public redirectToCfdi(folio:string){
      this.router.navigate([`./pages/promotor/precfdi/${folio}`])
    }

    /***     Funciones tabla      ***/
    public updateDataTable(currentPage?: number, pageSize?: number,filterParams?:any) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.invoiceService.getInvoices(pageValue,sizeValue,filterParams)
        .subscribe(result => this.page = result);
    }

    public onChangePageSize(pageSize: number) {
      this.updateDataTable(this.page.number, pageSize);
    }

    public downloadHandler() {
      this.invoiceService.getInvoices(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content,'Facturas')
      });
    }

   

}
