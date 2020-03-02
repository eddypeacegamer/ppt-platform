import { BovedaComponent } from './boveda.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { SolicitudesComponent } from './solicitudes/solicitudes.component';

const routes: Routes = [{
    path: '',
    component: BovedaComponent,
    children: [
      {
        path: 'solicitudes',
        component: SolicitudesComponent,
      },
    ]}];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
  })
export class BovedaRoutingModule { }
