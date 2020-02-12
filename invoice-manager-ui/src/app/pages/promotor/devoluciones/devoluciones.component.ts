import { Component, OnInit, TemplateRef } from '@angular/core';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { Devolucion } from '../../../models/devolucion';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { DevolucionValidatorService } from '../../../@core/util-services/devolucion-validator.service';
import { Router } from '@angular/router';
import { UsersData, User } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { PaymentsService } from '../../../@core/back-services/payments.service';
import { PagoDevolucion } from '../../../models/pago-devolucion';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { GenericPage } from '../../../models/generic-page';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'ngx-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss'],
})
export class DevolucionesComponent implements OnInit {

  public banksCat: Catalogo[] = [];
  public refTypesCat: Catalogo[] = [];
  public pageCommissions: GenericPage<Devolucion> = new GenericPage();
  public pageDevolutions: GenericPage<PagoDevolucion> = new GenericPage();

  public user: User;
  public montoDevolucion: Number = 0;
  public solicitud: PagoDevolucion;
  public filterParams: any = { tipoReceptor: 'PROMOTOR', idReceptor: '' };
  public messages: string[] = [];

  constructor(private dialogService: NbDialogService,
    private devolutionService: DevolutionData,
    private catalogService: CatalogsData,
    private paymentsService: PaymentsService,
    private donwloadService: DownloadCsvService,
    private devolutionValidator: DevolucionValidatorService,
    private userService: UsersData,
    private router: Router) { }

  ngOnInit() {
    this.montoDevolucion = 0.0;
    this.solicitud = new PagoDevolucion();
    this.userService.getUserInfo()
      .subscribe(user => {
        this.user = user;
        this.filterParams.idReceptor = user.email;
        this.searchDevolutionsData();
      });
    this.catalogService.getBancos().subscribe(banks => this.banksCat = banks);
  }

  public onReceptorTypeSelected(type: string) {
    if (type === 'PROMOTOR') {
      this.filterParams.idReceptor = this.user.email;
    } else { this.filterParams.idReceptor = ''; }
    this.filterParams.tipoReceptor = type;
  }

  public searchDevolutionsData() {
    this.messages = [];
    this.updateCommissions().subscribe((result: GenericPage<Devolucion>) => this.pageCommissions = result,
      (error: HttpErrorResponse) => this.messages.push(error.error.message || `${error.statusText} : ${error.message}`));
    this.updateDevolutions().subscribe((result: GenericPage<PagoDevolucion>) => this.pageDevolutions = result,
      (error: HttpErrorResponse) => this.messages.push(error.error.message || `${error.statusText} : ${error.message}`));
    this.devolutionService.getAmmountDevolutions(this.filterParams.tipoReceptor, this.filterParams.idReceptor)
      .subscribe(ammount => this.montoDevolucion = ammount);
  }

  public updateCommissions(page?: number, size?: number) {
    const p = page || 0;
    const s = size || 10;
    return this.devolutionService.findDevolutions(p, s, this.filterParams);
  }
  public updateDevolutions(page?: number, size?: number) {
    const p = page || 0;
    const s = size || 10;
    return this.devolutionService.findDevolutionsRequests(p, s, this.filterParams);
  }

  public downloadCommissions() {
    this.updateCommissions(0, 10000).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Comisiones');
    });
  }

  public downloadPagosDevolucion() {
    this.updateDevolutions(0, 10000).subscribe(result => {
      this.donwloadService.exportCsv(result.content, 'Devoluciones');
    });
  }

  public onPayFormSelected(formaPago: string) {
    if (formaPago !== '*') {
      this.catalogService.getTiposReferencia(formaPago)
        .subscribe(types => {
          this.refTypesCat = types;
          this.solicitud.tipoReferencia = types[0].id;
        });
      if (formaPago === 'TRANSFERENCIA') {
        this.solicitud.banco = this.banksCat[0].nombre;
      } else {
        this.solicitud.banco = 'N/A';
      }
    } else {
      this.solicitud.tipoReferencia = '*';
      this.solicitud.banco = 'N/A';
    }
  }

  public devolutionRequest(solicitud: PagoDevolucion) {
    this.messages = [];
    solicitud.solicitante = this.user.email;
    solicitud.tipoReceptor = this.filterParams.tipoReceptor;
    solicitud.receptor = this.filterParams.idReceptor;
    this.messages = this.devolutionValidator.validateDevolution(this.montoDevolucion, solicitud);
    if (this.messages.length === 0) {
      this.devolutionService.requestDevolution(solicitud)
        .subscribe(
          success => {
            this.searchDevolutionsData();
            this.solicitud = new PagoDevolucion();
            this.solicitud.formaPago = 'TRANSFERENCIA';
          },
          (error: HttpErrorResponse) => this.messages.push(error.error.message
            || `${error.statusText} : ${error.message}`));
    } else {
      this.solicitud = new PagoDevolucion();
      this.solicitud.formaPago = 'TRANSFERENCIA';
    }
  }

  public redirectToCfdi(folio: string) {
    this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
  }

  public openPaymentDetails(dialog: TemplateRef<any>, payment: PagoDevolucion) {
        this.dialogService.open(dialog, { context: payment })
          .onClose.subscribe(() => this.searchDevolutionsData());
  }
}
