import { Component, OnInit } from '@angular/core';
import { PagoDevolucion } from '../../../models/pago-devolucion';
import { GenericPage } from '../../../models/generic-page';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { NbDialogService } from '@nebular/theme';
import { ValidacionDevolucionComponent } from './validacion-devolucion/validacion-devolucion.component';

@Component({
  selector: 'ngx-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss'],
})
export class DevolucionesComponent implements OnInit {

  public filterParams: any =
    { formaPago: '*', status: 'VALIDACION', tipoReceptor: '*', beneficiario: '', idReceptor: '' };
  public errors: string[] = [];
  public page: GenericPage<PagoDevolucion> = new GenericPage();
  public pageSize = '10';

  constructor(
    private devolutionsService: DevolutionData,
    private donwloadService: DownloadCsvService,
    private dialogService: NbDialogService,
    ) { }

    ngOnInit() {
      this.updateDataTable();
      this.filterParams = { formaPago: '*', status: 'VALIDACION', tipoReceptor: '*', beneficiario: '', idReceptor: '' };
    }
    public updateDataTable(currentPage?: number, pageSize?: number) {
      const pageValue = currentPage || 0;
      const sizeValue = pageSize || 10;
      this.devolutionsService.findDevolutionsRequests(pageValue, sizeValue, this.filterParams)
        .subscribe((result: GenericPage<any>) => this.page = result);
    }
    public onChangePageSize(pageSize: number) {
      this.updateDataTable(this.page.number, pageSize);
    }
    public downloadHandler() {
      this.devolutionsService.findDevolutionsRequests(0, 10000, this.filterParams).subscribe(result => {
        this.donwloadService.exportCsv(result.content, 'SolicitudesDevolucion');
      });
    }

    public openDialog(pago: PagoDevolucion) {
      this.dialogService.open(ValidacionDevolucionComponent, {
        context: {
          payment : pago,
        },
      }).onClose.subscribe(() => this.updateDataTable(this.page.number, this.page.size));
    }
}
