import { NgModule } from '@angular/core';

import { ContabilidadRoutingModule } from './contabilidad-routing.module';
import { ContabilidadComponent } from './contabilidad.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { PreCfdiComponent } from './pre-cfdi/pre-cfdi.component';
import { ReportesComponent } from './reportes/reportes.component';
import { CargaMasivaComponent } from './carga-masiva/carga-masiva.component';

@NgModule({
  declarations: [ContabilidadComponent, ClientesComponent, ClienteComponent, EmpresasComponent, EmpresaComponent, PreCfdiComponent, ReportesComponent, CargaMasivaComponent],
  imports: [
    ContabilidadRoutingModule
  ]
})
export class ContabilidadModule { }
