import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { PagosComponent } from './pagos/pagos.component';
import { TransferenciasComponent } from './transferencias/transferencias.component';

const routes: Routes = [{
  path: '',
  component: TesoreriaComponent,
  children:[
    {
      path: 'pagos',
      component: PagosComponent,
    },
    {
      path: 'devoluciones',
      component: DevolucionesComponent,
    },
    {
      path: 'transferencias',
      component: TransferenciasComponent,
    }
  ]

}]; 

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TesoreriaRoutingModule { }
