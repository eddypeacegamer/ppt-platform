import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ValidacionPagosComponent } from './validacion-pagos/validacion-pagos.component';


const routes: Routes = [{
  path: '',
  component: TesoreriaComponent,
  children:[
    {
      path: 'pagos',
      component: ValidacionPagosComponent,
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
