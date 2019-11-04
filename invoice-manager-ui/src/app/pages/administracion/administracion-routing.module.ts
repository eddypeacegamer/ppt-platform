import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdministracionComponent } from './administracion.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [{
  path: '',
  component: AdministracionComponent,
  children: [
    {
      path: 'users',
      component: UsersComponent,
    }]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministracionRoutingModule { }
