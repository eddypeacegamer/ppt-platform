import { NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PromotorComponent} from './promotor.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { ReportesComponent } from './reportes/reportes.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';

const routes: Routes = [{
  path: '',
  component: PromotorComponent,
  children: [
    {
      path: 'precfdi/:folio',
      component: PreCfdiComponent,
    }, {
      path: 'clientes',
      component: ClientesComponent,
    }, {
      path: 'reportes',
      component: ReportesComponent,
    }, {
      path: 'devoluciones',
      component: DevolucionesComponent,
    },
  ]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PromotorRoutingModule { }
