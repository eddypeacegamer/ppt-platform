import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  NbLoginComponent,
  NbLogoutComponent,
  NbRegisterComponent,
  NbRequestPasswordComponent,
  NbResetPasswordComponent,
} from '@nebular/auth';
import { UnauthorizedComponent } from './auth/unauthorized/unauthorized.component';
import { UnavailableServiceComponent } from './auth/unavailable-service/unavailable-service.component';
import { SesionLostComponent } from './auth/sesion-lost/sesion-lost.component';

const routes: Routes = [
  {
    path: 'pages',
    loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule),
  }, {
    path: 'unauthorized',
    component: UnauthorizedComponent,
  },
  {
    path: 'unavailable',
    component: UnavailableServiceComponent,
  },
  {
    path: 'sesion-lost',
    component: SesionLostComponent,
  },
  
  { path: '', redirectTo: 'pages', pathMatch: 'full' },
  { path: '**', redirectTo: 'pages' },
];

const config: ExtraOptions = {
  useHash: true,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
