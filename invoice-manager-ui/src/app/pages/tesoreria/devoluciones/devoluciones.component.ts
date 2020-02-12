import { Component, OnInit } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { UsersData } from '../../../@core/data/users-data';
import { PaymentsData } from '../../../@core/data/payments-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
import { NbDialogService } from '@nebular/theme';
import { Pago } from '../../../models/pago';
import { SolicitudDevolucionComponent } from './solicitud-devolucion/solicitud-devolucion.component';

@Component({
  selector: 'ngx-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss']
})
export class DevolucionesComponent implements OnInit {

  public userEmail : string;
  public filterParams: any = { formaPago: '*', status: 'DEVOLUCION', banco: '*', since: '', to: '' };
  public errors : string[]=[];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(
    private userService : UsersData,
    private paymentService : PaymentsData,
    private donwloadService: DownloadCsvService,
    private dialogService: NbDialogService
    ) { }

    ngOnInit() {
      this.updateDataTable();
      this.filterParams= { formaPago: '*', status: 'DEVOLUCION', banco: '*', since: '', to: '' };
      this.userService.getUserInfo().subscribe(user => this.userEmail = user.email);
    }
  
    public updateDataTable(currentPage?: number, pageSize?: number) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.paymentService.getExpenses(pageValue,sizeValue, this.filterParams)
        .subscribe((result:GenericPage<any>) => this.page = result);
    }
  
    public onChangePageSize(pageSize: number) {
      this.updateDataTable(this.page.number, pageSize);
    }
  
    public downloadHandler() {
      this.paymentService.getExpenses(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content, 'SolicitudesDevolucion')
      });
    }

    openDialog(payment:Pago) {
      this.dialogService.open(SolicitudDevolucionComponent, {
        context: {
          pago: payment,
        },
      }).onClose.subscribe(()=>this.updateDataTable(this.page.number,this.page.size));
    }

}
