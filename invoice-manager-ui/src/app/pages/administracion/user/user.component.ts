import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersData, User } from '../../../@core/data/users-data';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface IHash {
  [key: string]: boolean;
}



@Component({
  selector: 'ngx-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})

export class UserComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  public user: User;
  public rolesArray: IHash =       { "PROMOTOR": false, "TESORERIA": false, "OPERADOR": false, "CONTABILIDAD": false, "ADMINISTRADOR": false, "BOVEDA": false };
  public rolesArrayUpdate: IHash = { "PROMOTOR": false, "TESORERIA": false, "OPERADOR": false, "CONTABILIDAD": false, "ADMINISTRADOR": false, "BOVEDA": false };
 
  public errorMessages: string[] = [];
  public buttontext:string = "Registrar";;
  public mode: number;
  
  public correorandom: string;
  public inputabalible: boolean;
  public filterParams: any = { correo: this.correorandom, activo: 'true', success: '', message: '', id: '*' };
  // this.isChecked = Number(data['status']) === 0 ? false : true;
  constructor(
    private route: ActivatedRoute,
    private userService: UsersData,
    private formBuilder: FormBuilder

  ) { }

  ngOnInit() {

    //Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"),
    //^[a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,15})$"
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"),]],
    });

    this.errorMessages = [];
    this.filterParams.correo = "correoprueva" + (Math.floor(Math.random() * 6000) + 1).toString() + "@gmail.com";

    this.route.paramMap.subscribe(route => {
      const id = route.get('id');
      console.log(id);
      if (id === '*') {
     //   this.inputabalible = false;
        this.mode = 0;
//      this.registerForm.controls['email'].enable();
        console.log('Crea nuevo usuario');
      } else {
     // this.registerForm.controls['email'].disable();
     // this.inputabalible = true;
      
      this.mode = 1;
      this.buttontext = "Actualiza";
       
      console.log('Prersentar informacion de:', id);
        
      this.userService.getOneUser(+id).subscribe(
          user => {
            console.log("POST Request Roles bien", user);
            this.filterParams.correo = user.email;
            this.filterParams.activo = user.activo;
            this.filterParams.id = user.id;
            this.user = user;
            for (let i in user.roles) {
        
              this.rolesArrayUpdate[user.roles[i].role] = true;
            
            }
            this.CheckRoles();
            
          },
          (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
            || `${error.statusText} : ${error.message}`));
      }
    });
  }

  get f() { return this.registerForm.controls; }


  public Registry() {
    console.log("but  ");
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.errorMessages = [];

    if (this.mode == 0) {
      this.userService.insertNewUser(this.filterParams.correo, this.filterParams.activo).subscribe(
        data => {
          this.filterParams.success = 'El usuario ha sido creado satisfactoriamente.'
          console.log("POST Request Registro bien ", data);
          for (let i in this.rolesArray) {

            console.log("siclo  " + this.rolesArray[i]);

            if (this.rolesArray[i] != false) {
              console.log("novacio  " + this.rolesArray[i]);

              this.userService.insertRoles(i, data.id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido creado satisfactoriamente.'
                  console.log("POST Request Roles bien " + i, data);

                },
                (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));

            }
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`));
      console.log("entro 0 ");
    }
    if (this.mode == 1) {

      this.userService.UpdateUser(+this.filterParams.id, this.filterParams.activo).subscribe(
        data => {
          this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.'
          for (let i in this.user.roles) {
            //console.log("borrando el ", this.user.roles[i].role);
            if (this.rolesArrayUpdate[this.user.roles[i].role] == false)

              this.userService.DeleteRoles(this.user.id, this.user.roles[i].id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.'
                  console.log("Delet Request Roles Borrados bien " + i, data);
                  this.actialuza();
                }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
          }
          for (let i in this.rolesArray) {

            console.log("siclo  " + this.rolesArray[i]);

            if (this.rolesArray[i] == true) {
              console.log("novacio  " + this.rolesArray[i]);

              this.userService.insertRoles(i, data.id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.'
                  console.log("POST Request Roles bien " + i, data);
                  this.actialuza();
                },
                (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
            this.rolesArray[i] = false      
            }
          }

          // this.userService.DeleteRoles 
          console.log("POST Request Registro bien ", data);

        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`));
      console.log("entro 1 " + this.filterParams.id);

    }
    //this.filterParams.correo = "correoprueva" + (Math.floor(Math.random() * 6000000) + 1).toString() + "@gmail.com";

  }

  

  toggle(checked: boolean, rol: string) {
    
    this.rolesArray[rol] = checked;

    this.CheckRoles();
   
  }



  public CheckRoles() {
    for (let i in this.rolesArray) {

      console.log(this.rolesArray[i]);

    }
  }

  public actialuza(){
    this.userService.getOneUser(this.user.id).subscribe(
      user => {
       
        console.log("POST Request Roles bien", user);
        this.filterParams.correo = user.email;
        this.filterParams.activo = user.activo;
        this.filterParams.id = user.id;
        this.user = user;
        for (let i in user.roles) {
    
          this.rolesArrayUpdate[user.roles[i].role] = true;
  
        }
        this.CheckRoles();

      },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

}
