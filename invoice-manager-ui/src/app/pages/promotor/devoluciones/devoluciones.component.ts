import { Component, OnInit } from '@angular/core';
import { DevolutionData } from '../../../@core/data/devolution-data';
import { Devolucion } from '../../../models/devolucion';
import { DownloadCsvService } from '../../../@core/back-services/download-csv.service';
import { Router } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';
import { SolicitudDevolucion } from '../../../models/solicitud-devolucion';

@Component({
  selector: 'ngx-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss']
})
export class DevolucionesComponent implements OnInit {

  public devolutions: Devolucion[]=[];
  public userEmail:string;
  public montoDevolucion : number;
  public solicitud : SolicitudDevolucion = new SolicitudDevolucion();
  public filterParams : any = {tipoReceptor:'PROMOTOR',idReceptor:'promotor@gmail.com',statusDevolucion:'*'};

  constructor(private devolutionService:DevolutionData,
              private donwloadService:DownloadCsvService,
              private userService: UsersData,
              private router: Router) { }

  ngOnInit() {
    this.montoDevolucion = 0.0;
    this.solicitud = new SolicitudDevolucion();
    this.solicitud.formaPago = 'TRANSFERENCIA';
    this.solicitud.banco = 'BBVA';
    this.userService.getUserInfo()
      .subscribe(user => {
        this.userEmail = user.email;
        this.solicitud.user = user.email;
        this.filterParams= {tipoReceptor:'PROMOTOR',idReceptor:this.userEmail,statusDevolucion:'PENDIENTE'};
        this.updateDataTable();
      });
  }

  public onReceptorTypeSelected(type:string){
    if(type=='PROMOTOR'){ this.filterParams.idReceptor = this.userEmail}
    else{ this.filterParams.idReceptor = ''}
    this.filterParams.tipoReceptor = type;
  }

  public onDevolutionStatusSelected(status:string){
    this.filterParams.statusPago= status;
  }

  public updateDataTable() {
    this.devolutionService.getDevolutionsByReceptor(this.filterParams.tipoReceptor,this.filterParams.idReceptor,this.filterParams.statusDevolucion)
          .subscribe((result:Devolucion[]) => this.devolutions = result);
  }

  public downloadHandler() {
    this.devolutionService.getDevolutionsByReceptor(this.filterParams.tipoReceptor, this.filterParams.idReceptor,this.filterParams.statusDevolucion).subscribe(result => {
      this.donwloadService.exportCsv(result,'Devoluciones')
    });
  }

  public addDevolucion(devolucion:Devolucion){
    devolucion.solicitud  = !devolucion.solicitud;
    if(devolucion.solicitud === true){
      devolucion.solicitud = true;
      this.solicitud.devoluciones.push(devolucion);
    }else{
      devolucion.solicitud = false;
      let index =this.solicitud.devoluciones.findIndex(e=> e.id == devolucion.id);
      if(index > -1){
        this.solicitud.devoluciones.splice(index,1);
      }
    }
    if(this.solicitud.devoluciones!=undefined && this.solicitud.devoluciones.length>0){
      this.montoDevolucion = this.solicitud.devoluciones.map(d=>d.monto).reduce((a,c)=>a+c);
    }else{
      this.montoDevolucion = 0.0;
    }
    
  }


  public solicitudDevoluciones(){
    this.devolutionService.requestMultipleDevolution(this.solicitud)
      .subscribe(pago=>{this.updateDataTable(); this.solicitud= new SolicitudDevolucion()});
  }

  public redirectToCfdi(folio:string){
    this.router.navigate([`./pages/promotor/precfdi/${folio}`])
  }

  public redirectToPago(id:number){
    console.log("searching payment with id :"+ id);
  }

}
