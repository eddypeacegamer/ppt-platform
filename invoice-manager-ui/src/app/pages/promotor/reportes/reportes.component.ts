import { Component, OnInit } from '@angular/core';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { GenericPage } from '../../../models/generic-page';
import {DownloadCsvService } from '../../../@core/back-services/download-csv.service'


@Component({
  selector: 'ngx-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.scss']
})
export class ReportesComponent implements OnInit {


  public headers: string[] = ['Clave', 'Descripcion', 'Similares', 'Inicio Vigencia'];
  public page: GenericPage<any> = new GenericPage();
  public pageSize = '10';

  constructor(private catalogService: CatalogsData,
    private donwloadService:DownloadCsvService) {}

  ngOnInit() {
    this.updateDataTable();
  }

  public updateDataTable(currentPage?: number, pageSize?: number) {
    const pageValue = currentPage || 0;
    const sizeValue = pageSize || 10;
    this.catalogService.getAllClavesProductoServicio(pageValue, sizeValue)
      .subscribe(result => this.page = result);
  }

  public onChangePageSize(pageSize: number) {
    this.updateDataTable(this.page.number, pageSize);
  }



  public downloadHandler() {
    let filename: string = 'Facturas.csv';
    this.catalogService.getAllClavesProductoServicio(0, 10000).subscribe(result => {
      this.donwloadService.exportCsv(result.content,'export')
    });

  }
  

  /*
    public data: any[] = [{
      preFolio: 'ALF-236468796',
      fechaEmision: '2019-08-26',
      rfcCliente: 'SVG550612GF2',
      cliente: 'Cliente No 1 y asociados',
      rfcEmpresa: 'AND231115HD5',
      empresa: 'Empresa 1 SA de CV',
      statusPago: 'Revision',
      statusRevision: 'Aprobada',
      formaPago: 'transferencia',
      referenciaPago: '13254688378558895',
      monto: 15622.25,
      pdf: 'http://265.23.52.45/SFGCDHSD/PDF'
    }, {
      preFolio: 'HGF-6598528796',
      fechaEmision: '2019-09-26',
      rfcCliente: 'TRF550612GGB5',
      cliente: 'Cliente No 1 y asociados',
      rfcEmpresa: 'FGT231115TH5',
      empresa: 'Empresa 1 SA de CV',
      statusPago: 'Verificado',
      statusRevision: 'Aprobada',
      referenciaPago: '13254688378558895',
      formaPago: 'Efectivo',
      monto: 15622.25,
      pdf: 'http://263.23.52.45/HGHJJHHSD/PDF'
    }, {
      preFolio: 'HGF-6598528796',
      fechaEmision: '2019-09-26',
      rfcCliente: 'TRF550612GGB5',
      cliente: 'Cliente No 1 y asociados',
      rfcEmpresa: 'FGT231115TH5',
      empresa: 'Empresa 1 SA de CV',
      statusPago: 'Verificado',
      statusRevision: 'Aprobada',
      referenciaPago: '13254688378558895',
      formaPago: 'Efectivo',
      monto: 15622.25,
      pdf: 'http://263.23.52.45/HGHJJHHSD/PDF'
    }];*/





}
