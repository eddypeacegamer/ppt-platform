import { NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PromotorComponent} from './promotor.component';
import { ClientesComponent } from '../commons/clientes/clientes.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { DevolucionesComponent } from './devoluciones/devoluciones.component';
import { ClienteComponent } from '../commons/cliente/cliente.component';
import { DevolutionPreferencesComponent } from './devolution-preferences/devolution-preferences.component';
import { InvoiceReportsComponent } from '../commons/invoice-reports/invoice-reports.component';
import { PagoFactura } from '../../models/pago-factura';

const routes: Routes = [{
  path: '',
  component: PromotorComponent,
  children: [
    {
      path: 'precfdi/:folio',
      component: PreCfdiComponent,
    }, {
      path: 'precfdi/:folio/preferencias',
      component: DevolutionPreferencesComponent,
    }, {
      path: 'clientes',
      component: ClientesComponent,
    }, {
      path: 'cliente/:rfc',
      component: ClienteComponent,
    }, {
      path: 'reportes/:linea/:status',
      component: InvoiceReportsComponent,
    }, {
      path: 'devoluciones',
      component: DevolucionesComponent,
    }, {
      path: 'pago-facturas',
      component: PagoFactura,
    },
  ]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PromotorRoutingModule { }
