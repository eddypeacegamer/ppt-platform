import { Component, OnInit } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { InvoicesData } from '../../../@core/data/invoices-data';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../../@core/data/users-data';
import { Factura } from '../../../models/factura/factura';
import { PagoDevolucion } from '../../../models/pago-devolucion';
import { DevolucionValidatorService } from '../../../@core/util-services/devolucion-validator.service';
import { Client } from '../../../models/client';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Catalogo } from '../../../models/catalogos/catalogo';
import { HttpErrorResponse } from '@angular/common/http';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { ResourceFile } from '../../../models/resource-file';
import { FilesData } from '../../../@core/data/files-data';

@Component({
  selector: 'ngx-devolution-preferences',
  templateUrl: './devolution-preferences.component.html',
  styleUrls: ['./devolution-preferences.component.scss'],
})
export class DevolutionPreferencesComponent implements OnInit {

  public folioParam: string;
  public fileInput: any;
  public user: User;
  public factura: Factura= new Factura();
  public formParams: any = {tab: 'CLIENTE', filename: ''};
  public solicitud: PagoDevolucion = new PagoDevolucion();
  public banksCat: Catalogo[] = [];
  public refTypesCat: Catalogo[] = [];
  public messages: string[] = [];
  public clientInfo: Client = new Client();

  private referenceFile: ResourceFile = new ResourceFile();

  constructor(private clientsService: ClientsData,
    private invoiceService: InvoicesData,
    private catalogService: CatalogsData,
    private resourceService: FilesData,
    private devolutionService: DevolutionData,
    private devolutionValidator: DevolucionValidatorService,
    private route: ActivatedRoute) {}


  ngOnInit() {
    this.catalogService.getBancos().subscribe(banks => this.banksCat = banks);
    this.route.paramMap.subscribe(route => {
      this.folioParam = route.get('folio');
      if (this.folioParam !== '*') {
        this.invoiceService.getInvoiceByFolio(this.folioParam)
            .subscribe( invoice => {
              this.factura = invoice;
              this.clientsService.getClientByRFC(invoice.rfcRemitente)
                .subscribe(client => {this.clientInfo = client; this.selectTab('CLIENTE'); });
            });
      } else {
        this.initVariables();
      }
    });
  }

  public initVariables() {
    /** INIT VARIABLES **/
  }

  public selectTab(tiporeceptor: string) {
    this.formParams.tab = tiporeceptor;
    this.solicitud = new PagoDevolucion();
    this.solicitud.tipoReceptor = tiporeceptor;
    this.solicitud.monto = this.devolutionValidator
      .calculateDevolutionAmmount(this.factura.cfdi, this.clientInfo, tiporeceptor);
    if (tiporeceptor === 'CLIENTE') {
      this.solicitud.receptor = this.factura.rfcRemitente;
    }
    if (tiporeceptor === 'PROMOTOR') {
      this.solicitud.receptor = this.clientInfo.correoPromotor;
    }
    if (tiporeceptor === 'CONTACTO') {
      this.solicitud.receptor = this.clientInfo.correoContacto;
    }
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
          this.formParams.filename = file.name;
          this.referenceFile.tipoArchivo = 'ARCHIVO';
          this.referenceFile.tipoRecurso = 'DEVOLUCION';
          this.referenceFile.referencia  = this.folioParam;
          this.referenceFile.data = reader.result.toString();
        };
        reader.onerror = (error) => { this.messages.push('Error parsing image file'); };
      }
    }
  }

  public devolutionRequest(solicitud: PagoDevolucion) {
    this.messages = [];
    solicitud.solicitante = this.user.email;
    this.messages = this.devolutionValidator.validateDevolution(this.solicitud.monto, solicitud);
    if (this.messages.length === 0) {
      if (solicitud.formaPago === 'PAGO_MULTIPLE') {
        this.resourceService.insertResourceFile(this.referenceFile)
          .subscribe(success => console.log(success),
          (error: HttpErrorResponse) => this.messages.push(error.error.message
            || `${error.statusText} : ${error.message}`));
        }
      this.devolutionService.requestDevolution(solicitud)
        .subscribe(
          success => {
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


}
