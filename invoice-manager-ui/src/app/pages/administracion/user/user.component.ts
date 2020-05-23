import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../models/user';
import { Role } from '../../../models/role';
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
  public role: Role;
  public rolesArrayUpdate: IHash = { "PROMOTOR": false, "TESORERIA": false, "OPERADOR": false, "CONTABILIDAD": false, "ADMINISTRADOR": false, "BOVEDA": false, "CONSULTOR": false, "OPERADOR-B": false, "OPERADOR-C": false };

  public errorMessages: string[] = [];
  public buttontext: string = "Registrar";;
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
    this.user = new User();


    this.errorMessages = [];
    this.filterParams.correo = "correoprueva" + (Math.floor(Math.random() * 6000) + 1).toString() + "@gmail.com";

    this.route.paramMap.subscribe(route => {
      const id = route.get('id');
      if (id === '*') {
        this.inputabalible = false;
        this.mode = 0;
      } else {
        this.inputabalible = true;

        this.mode = 1;
        this.buttontext = "Actualiza";
        this.user.id = +id;
        this.userService.getOneUser(this.user).subscribe(
          user => {
            this.filterParams.activo = user.activo;
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


    this.registerForm = this.formBuilder.group({
      email: [{ value: this.filterParams.correo, disabled: this.inputabalible }, [Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
    });


  }

  get f() { return this.registerForm.controls; }


  public Registry() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }
    this.errorMessages = [];

    if (this.mode == 0) {
      
      this.user.activo=this.filterParams.activo;
      this.user.email=this.filterParams.correo;
    // this.user.alias="none";
      this.userService.insertNewUser(this.user).subscribe(
        data => {
          this.filterParams.success = 'El usuario ha sido creado satisfactoriamente.'
          for (let i in this.rolesArrayUpdate) {
            if (this.rolesArrayUpdate[i] != false) {
              this.role = new Role(i);
              this.userService.insertRoles(this.role, data.id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido creado satisfactoriamente.'
                },
                (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
            }
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`));
    }
    if (this.mode == 1) {
      this.user.activo = this.filterParams.activo
      this.userService.updateUser(this.user).subscribe(
        data => {
          this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.'
          for (let i in this.user.roles) {

            if (this.rolesArrayUpdate[this.user.roles[i].role] == false && !this.user.roles.find(x => x.role == i)) {

              this.userService.deleteRoles(this.user.id, this.user.roles[i].id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.'
                  this.actialuza();
                }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
            }
          }
          for (let i in this.rolesArrayUpdate) {



            if (this.rolesArrayUpdate[i] == true && !this.user.roles.find(x => x.role == i)) {


              this.userService.insertRoles(i, data.id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.'

                  this.actialuza();
                },
                (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
              this.rolesArrayUpdate[i] = false
            }
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`));


    }
    //this.filterParams.correo = "correoprueva" + (Math.floor(Math.random() * 6000000) + 1).toString() + "@gmail.com";

  }

  toggle(checked: boolean, rol: string) {

    this.rolesArrayUpdate[rol] = checked;

    this.CheckRoles();

  }



  public CheckRoles() {
    for (let i in this.rolesArrayUpdate) {

      console.log(this.rolesArrayUpdate[i]);

    }
  }

  public actialuza() {
    this.userService.getOneUser(this.user).subscribe(
      user => {
        this.filterParams.correo = user.email;
        this.filterParams.activo = user.activo;

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
