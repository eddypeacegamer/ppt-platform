import { Component, OnInit } from '@angular/core';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/back-services/download-csv.service'
import { Router } from '@angular/router';
import { Status } from '../../../models/catalogos/status';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Factura } from '../../../models/factura/factura';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { UsersData } from '../../../@core/data/users-data';


@Component({
  selector: 'ngx-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.scss']
})
export class ReportesComponent implements OnInit {


  public headers: string[] = ['Folio', 'RFC Emisor','Emisor', 'RFC Remitente','Remitente','Tipo','Metodo pago', 'Estatus Validacion', 'Estatus Pago','Total','Fecha Solicitud', 'Fecha Timbrado'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public filterParams : any = {emisor:'',remitente:'',folio:'',status:'1',since:'',to:''};

  public validationCat : Status[] = [];
  public payCat : Status[] = [];
  public devolutionCat : Status[] = [];

  public userEmail: string;

  constructor(private invoiceService: InvoicesData,
    private catalogService : CatalogsData,
    private userService: UsersData,
    private donwloadService:DownloadCsvService,
    private router: Router) {}

    ngOnInit() {
      this.catalogService.getStatusValidacion().subscribe(cat=>this.validationCat = cat);
      this.catalogService.getStatusPago().subscribe(cat=>this.payCat = cat);
      this.catalogService.getStatusDevolucion().toPromise()
        .then(cat=>this.devolutionCat = cat).then(()=>{
          if(this.userEmail==undefined){
            this.userService.getUserInfo().subscribe((user)=>{
              this.userEmail = user.email;
              this.filterParams.solicitante = user.email;
              this.updateDataTable();
            });
          }else{
            this.filterParams.solicitante = this.userEmail;
            this.updateDataTable()
          }
        });
    }
  

    public onPayStatus(payStatus:string){
        this.filterParams.payStatus=payStatus;
    }

    public onValidationStatus(validationStatus:string){
        this.filterParams.status=validationStatus;
    }

    public redirectToCfdi(folio:string){
      this.router.navigate([`./pages/promotor/precfdi/${folio}`])
    }

    /***     Funciones tabla      ***/

    /* Mapping function to  return correct information to view */
    public getInvoiceData(pageValue?: number, sizeValue?: number,filterParams?:any):Observable<GenericPage<Factura>>{
      return this.invoiceService.getInvoices(pageValue,sizeValue,filterParams)
      .pipe(
        map((page:GenericPage<Factura>)=>{ 
          let records : Factura[] = page.content.map(record=>{
            record.statusFactura = this.validationCat.find(v=>v.id==record.statusFactura).value;
            record.statusPago = this.payCat.find(v=>v.id==record.statusPago).value;
            record.statusDevolucion = this.devolutionCat.find(v=>v.id==record.statusDevolucion).value;
            return record;
          });
          page.content = records;
          return page;
        })
      )
    }

    public updateDataTable(currentPage?: number, pageSize?: number) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.getInvoiceData(pageValue,sizeValue,this.filterParams)
        .subscribe((result:GenericPage<any>) => this.page = result);
    }

    public onChangePageSize(pageSize: number) {
      this.updateDataTable(this.page.number, pageSize);
    }

    public downloadHandler() {
      this.getInvoiceData(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content,'Facturas')
      });
    }

   

}
