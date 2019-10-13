import { Component, OnInit , TemplateRef } from '@angular/core';
import { NbIconLibraries } from '@nebular/theme';
import { NbDialogService } from '@nebular/theme';
import { LocalDataSource } from 'ng2-smart-table';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { Giro } from '../../../models/catalogos/giro';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'ngx-pre-cfdi',
  templateUrl: './pre-cfdi.component.html',
  styleUrls: ['./pre-cfdi.component.scss']
})
export class PreCfdiComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();

  

  private data :any[] = [{
    claveProdServ: 500234,
    cantidad: 3,
    claveUnidad: 5,
    unidad: 'kilogramos',
    valorUnitario: 71.52,
    descripcion: 'Aguacates de temporada',
    importe : 253.62,
    descuento : 0
  }, {
    claveProdServ: 500434,
    cantidad: 5,
    claveUnidad: 5,
    unidad: 'kilogramos',
    valorUnitario: 30.52,
    descripcion: 'Naranjas de temporada',
    importe : 153.62,
    descuento : 0
  }];

  settings = {
    hideSubHeader: true,
    actions:{edit: false},
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      claveProdServ: {
        title: 'Clave Prod Serv',
        type: 'string',
      },
      cantidad: {
        title: 'Cantidad',
        type: 'number',
      },
      claveUnidad: {
        title: 'Clave Unidad',
        type: 'number',
      },
      unidad: {
        title: 'Unidad',
        type: 'number',
      },
      valorUnitario: {
        title: 'Valor Unitario',
        type: 'string',
      },
      descripcion: {
        title: 'Descripción',
        type: 'string',
      },
      importe: {
        title: 'Importe',
        type: 'number',
      },
      descuento: {
        title: 'Descuento',
        type: 'number',
      },
    },
  };


  public girosCat : Giro[];
  public messages : any = {catMessage :''};

  constructor(private dialogService: NbDialogService, 
              private iconsLibrary: NbIconLibraries,
              private catalogsService : CatalogsData){
    this.source.load(this.data);
    iconsLibrary.registerFontPack('fa', { packClass: 'fa', iconClassPrefix: 'fa' });
  }

  ngOnInit() {

    /**** LOADING CAT INFO ****/ 
    this.catalogsService.getAllGiros().subscribe(giros=>this.girosCat= giros,
      (error : HttpErrorResponse)=>this.messages.catMessage = error.error.message || `${error.statusText} : ${error.message}`);
  }

  onDeleteConfirm(event): void {
    if (window.confirm('Estas seguro de borar el elemento?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }

  openBuscadorSAT(dialog: TemplateRef<any>) {

    this.dialogService.open(dialog);
  }

}
