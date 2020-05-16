import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';
import { HttpErrorResponse } from '@angular/common/http';

interface IHash {
  [key: string]: boolean;
}


@Component({
  selector: 'ngx-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})

export class UserComponent implements OnInit {

  public roles: IHash = { "PROMOTOR": false, "TESORERIA": false, "OPERADOR": false, "CONTABILIDAD": false, "ADMINISTRADOR": false, "BOVEDA": false };


  public user: any;

  public correorandom: string;

  public filterParams: any = { correo: this.correorandom, activo: 'true', roles: [] };

  constructor(private route: ActivatedRoute,
    private userService: UsersData) { }

  ngOnInit() {
    this.filterParams.correo = "correoprueva" + (Math.floor(Math.random() * 6000) + 1).toString() + "@gmail.com";

    this.route.paramMap.subscribe(route => {
      const email = route.get('email');
      console.log(email);
      if (email === '*') {

        console.log('Crea nuevo usuario');
      } else {
        console.log('Prersentar informacion de:', email);
        // cambiar este metodo por el que regresa la informacion del usuario
        this.userService.getUserInfo().then(user => this.user = user);
      }
    });
  }

  public Registry() {

    this.userService.insertNewUser(this.filterParams.correo, this.filterParams.activo).subscribe(
      data => {
        console.log("POST Request Registro bien ", data);
        for (let i in this.roles) {
   
          console.log("siclo  " + this.roles[i]);

          if (this.roles[i] != false) {
            console.log("novacio  " + this.roles[i]);
            this.userService.insertRoles(i, data.id).subscribe(
              data => {
                console.log("POST Request Roles bien " + i, data);

              },
              error => {

                console.log("Error", error);

              });
          }
        }
      },
      error => {

        console.log("Error", error);
        if(error.message=="user ya  existe "+this.filterParams.correo)
          console.log("Error", );
      }

    );;

    console.log('Creadooo nuevo usuario1 ' + this.filterParams.correo + ' / ' + true);
    this.filterParams.correo = "correoprueva" + (Math.floor(Math.random() * 6000000) + 1).toString() + "@gmail.com";

  }

  toggle(checked: boolean, i: number, rol: string) {

    this.roles[rol] = checked;
    this.CheckRoles();
    console.log(this.roles);
  }



  public CheckRoles() {
    for (let i in this.roles) {

      console.log(this.roles[i]);

    }
  }


}
