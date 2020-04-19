/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { Component, OnInit } from '@angular/core';
import { CatalogsData } from './@core/data/catalogs-data';
import { UsersData } from './@core/data/users-data';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-app',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit {

  constructor(private catalogsService: CatalogsData,
              private userService: UsersData,
              private router: Router) {
  }

  ngOnInit(): void {
    this.catalogsService.getStatusPago().then(cat => console.log('payment status cat has been loaded : ', cat));
    this.catalogsService.getStatusValidacion().then(cat => console.log('invoice status cat  has been loaded : ', cat));
    this.catalogsService.getStatusDevolucion().then(cat => console.log('devolution status cat  been loaded : ', cat));
    let validar:boolean=false;
    
    this.userService.getUserInfo().then(user => {
      if (user.activo) {
        validar=true;
        this.router.navigate(['./pages/dashboard']);
      }else {
        console.error('El usuario se encuentra inactivo');
        this.router.navigate(['./auth/register']);
      }
    });
    if (!validar) {
        console.error('El usuario se encuentra inactivo');
        this.router.navigate(['./request-password']);
    }
  }
}
