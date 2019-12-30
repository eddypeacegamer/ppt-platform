import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TesoreriaComponent } from './tesoreria.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ValidacionPagosComponent } from './validacion-pagos/validacion-pagos.component';
import { IngresosComponent } from './ingresos/ingresos.component';
import { EgresosComponent } from './egresos/egresos.component';
import { ConciliacionComponent } from './conciliacion/conciliacion.component';


const routes: Routes = [{
  path: '',
  component: TesoreriaComponent,
  children:[
    {
      path: 'validacion-pagos',
      component: ValidacionPagosComponent,
    },
    {
      path: 'devoluciones',
      component: DevolucionesComponent,
    },
    {
      path:'ingresos',
      component:IngresosComponent
    },{
      path:'egresos',
      component:EgresosComponent
    },{
      path:'conciliacion',
      component :ConciliacionComponent
    }
  ]

}]; 

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TesoreriaRoutingModule { }
