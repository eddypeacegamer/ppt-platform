import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { PaymentsData } from '../../../@core/data/payments-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-ingresos',
  templateUrl: './ingresos.component.html',
  styleUrls: ['./ingresos.component.scss']
})
export class IngresosComponent implements OnInit {

  public filterParams: any = { formaPago: '*', status: 'ACEPTADO', banco: '*', since: '', to: '' };
  public errors : string[]=[];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';
  public total:number =0.0;

  constructor(private paymentService : PaymentsData,
    private donwloadService: DownloadCsvService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.updateDataTable();
    this.filterParams= { formaPago: '*', status: 'ACEPTADO', banco: '*', since: '', to: '' };
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.paymentService.getIncomes(pageValue,sizeValue, this.filterParams)
      .subscribe((result:GenericPage<any>) => this.page = result);
    this.paymentService.getIncomesSum().subscribe( total => this.total = total);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }

  public downloadHandler() {
    this.paymentService.getIncomes(0, 10000, this.filterParams).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Ingresos')
    });
  }

 

  public redirectToCfdi(folio:string){
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

}
