import { Component, OnInit, TemplateRef } from '@angular/core';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { Devolucion } from '../../../models/devolucion';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { DevolucionValidatorService } from '../../../@core/util-services/devolucion-validator.service';
import { Router } from '@angular/router';
import { UsersData, User } from '../../../@core/data/users-data';
import { NbDialogService } from '@nebular/theme';
import { PagoDevolucion } from '../../../models/pago-devolucion';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { GenericPage } from '../../../models/generic-page';
import { HttpErrorResponse } from '@angular/common/http';
import { ResourceFile } from '../../../models/resource-file';
import { FilesData } from '../../../@core/data/files-data';
import { Client } from '../../../models/client';
import { ClientsData } from '../../../@core/data/clients-data';
import { DonwloadFileService } from '../../../@core/util-services/download-file-service';

@Component({
  selector: 'ngx-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss'],
})
export class DevolucionesComponent implements OnInit {

  public banksCat: Catalogo[] = [];
  public refTypesCat: Catalogo[] = [];
  public clientsCat : Client[] = [];
  public contactosCat: string[] = [];
  public pageCommissions: GenericPage<Devolucion> = new GenericPage();
  public pageDevolutions: GenericPage<PagoDevolucion> = new GenericPage();

  public user: User;
  public filename: string = '';
  public loading: boolean = false;
  public montoDevolucion: Number = 0;
  public solicitud: PagoDevolucion;
  public fileInput: any = {};
  public filterParams: any = { tipoReceptor: 'PROMOTOR', idReceptor: '' };
  public messages: string[] = [];

  private referenceFile: ResourceFile;

  constructor(private dialogService: NbDialogService,
    private devolutionService: DevolutionData,
    private catalogService: CatalogsData,
    private clientsService: ClientsData,
    private resourceService: FilesData,
    private donwloadCsvService: DownloadCsvService,
    private downloadService: DonwloadFileService,
    private devolutionValidator: DevolucionValidatorService,
    private userService: UsersData,
    private router: Router) { }

  ngOnInit() {
    this.montoDevolucion = 0.0;
    this.solicitud = new PagoDevolucion();
    this.userService.getUserInfo().then(user => {
        this.user = user;
        this.filterParams.idReceptor = user.email;
        this.searchDevolutionsData();
        this.clientsService.getClientsByPromotor(user.email)
          .subscribe(clients => {
            this.clientsCat = clients;
            const contacts = clients.map(c => c.correoContacto);
            this.contactosCat = contacts.filter((item, index) => contacts.indexOf(item) === index);
          });
      });
    this.catalogService.getBancos().then(banks => this.banksCat = banks);

  }

  public onReceptorTypeSelected(type: string) {
    if (type === 'PROMOTOR') {
      this.filterParams.idReceptor = this.user.email;
    } else {
      this.filterParams.idReceptor = '*';
    }
    this.filterParams.tipoReceptor = type;
    this.searchDevolutionsData();
  }

  public searchDevolutionsData() {
    this.messages = [];
    this.solicitud = new PagoDevolucion();
    if(this.filterParams.idReceptor !== '*'){
      this.updateCommissions().subscribe((result: GenericPage<Devolucion>) => this.pageCommissions = result,
      (error: HttpErrorResponse) => this.messages.push(error.error.message || `${error.statusText} : ${error.message}`));
    this.updateDevolutions().subscribe((result: GenericPage<PagoDevolucion>) => this.pageDevolutions = result,
      (error: HttpErrorResponse) => this.messages.push(error.error.message || `${error.statusText} : ${error.message}`));
    this.devolutionService.getAmmountDevolutions(this.filterParams.tipoReceptor, this.filterParams.idReceptor)
      .subscribe(ammount => this.montoDevolucion = ammount);
    }else {
      this.montoDevolucion = 0.0;
      this.pageCommissions = new GenericPage();
      this.pageDevolutions = new GenericPage();
    }
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
      this.donwloadCsvService.exportCsv(result.content, 'Comisiones');
    });
  }

  public downloadPagosDevolucion() {
    this.updateDevolutions(0, 10000).subscribe(result => {
      this.donwloadCsvService.exportCsv(result.content, 'Devoluciones');
    });
  }

  public onPayFormSelected(formaPago: string) {
    if (formaPago !== '*') {
      this.catalogService.getTiposReferencia(formaPago)
        .then(types => {
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

  public fileUploadListener(event: any): void {
    this.fileInput = event.target;
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      if (file.size > 1000000) {
        alert('El archivo demasiado grande, intenta con un archivo mas pequeño.');
      } else {
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.filename = file.name;
          this.referenceFile = new ResourceFile();
          this.referenceFile.tipoArchivo = 'ARCHIVO';
          this.referenceFile.tipoRecurso = 'DEVOLUCION';
          this.referenceFile.data = reader.result.toString();
        };
        reader.onerror = (error) => { this.messages.push('Error parsing image file'); };
      }
    }
  }

  public devolutionRequest(solicitud: PagoDevolucion) {
    this.loading = true;
    this.messages = [];
    this.fileInput.value = '';
    solicitud.tipoReceptor = this.filterParams.tipoReceptor;
    solicitud.receptor = this.filterParams.idReceptor;
    solicitud.solicitante = this.user.email;
    solicitud.status = 'VALIDACION';
    if (solicitud.formaPago === 'PAGO_MULTIPLE') {
      solicitud.referencia = this.filename;
    }
    this.messages = this.devolutionValidator.validateDevolution(this.montoDevolucion , solicitud);
    if (this.messages.length === 0) {
      this.devolutionService.requestDevolution(solicitud)
        .subscribe( request => {
          if (solicitud.formaPago === 'PAGO_MULTIPLE') {
            this.referenceFile.referencia = request.id.toString();
          this.resourceService.insertResourceFile(this.referenceFile)
          .subscribe((resource: ResourceFile) => console.log(resource));
          }
          this.searchDevolutionsData();
          this.solicitud = new PagoDevolucion();
          this.solicitud.formaPago = 'TRANSFERENCIA';
          this.loading = false;
        }, (error: HttpErrorResponse) => {
          this.loading = false;
          this.messages.push(error.error.message || `${error.statusText} : ${error.message}`); });
    } else {
      this.loading = false;
      this.solicitud = new PagoDevolucion();
      this.solicitud.formaPago = 'TRANSFERENCIA';
    }
  }

  public downloadPdf(folio: string) {
    this.resourceService.getFacturaFile(folio, 'PDF').subscribe(
      file => this.downloadService.downloadFile(file.data, `${folio}.pdf`, 'application/pdf;')
    )
  }

  public redirectToCfdi(folio: string) {
    this.router.navigate([`./pages/promotor/precfdi/${folio}`]);
  }

  public openPaymentDetails(dialog: TemplateRef<any>, payment: PagoDevolucion) {
        this.dialogService.open(dialog, { context: payment })
          .onClose.subscribe(() => this.searchDevolutionsData());
  }

 
}
