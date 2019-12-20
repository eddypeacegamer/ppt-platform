import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { PagosComponent } from './pagos/pagos.component';

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
    }
  ]

}]; 

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TesoreriaRoutingModule { }
