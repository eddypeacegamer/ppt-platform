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
  styleUrls: ['./user.component.scss'],
})

export class UserComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  public user: User = new User();
  public rolesArrayUpdate: IHash = { 'PROMOTOR': false, "TESORERIA": false, "OPERADOR": false, "CONTABILIDAD": false,
    "ADMINISTRADOR": false, "BOVEDA": false, "CONSULTOR": false, "OPERADOR-B": false, "OPERADOR-C": false };

  public errorMessages: string[] = [];
  public correorandom: string;
  public inputabalible: boolean;
  public filterParams: any = { correo: this.correorandom, activo: 'true', success: '', message: '', id: '*' };

  constructor(
    private route: ActivatedRoute,
    private userService: UsersData,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.errorMessages = [];
    this.route.paramMap.subscribe(route => {
      const id = route.get('id');
      if (id !== '*') {
        // actualiza informacion usuario
        this.updateUserInfo(+id);
      } else {
        //nuevo usuario
        this.registerForm = this.formBuilder.group({
          email: [{ value: this.user.email, disabled: false },
            [Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
        });
      }
    });

  }

  get f() { return this.registerForm.controls; }


  public update() {
    const id = this.user.id;
      this.userService.updateUser(this.user).toPromise()
      .then(updateduser => {
          this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.';
        console.log(updateduser);
          for (const role in this.rolesArrayUpdate) { // QUITA ROLES EXISTENTES
            if (this.rolesArrayUpdate[this.user.roles[role].role] === false // ROLE EN FALSO
                    && this.user.roles.find(x => x.role === role)) { // PERO YA EXISTE EN EL USER
              this.userService.deleteRoles(this.user.id, this.user.roles[role].id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.';
                }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
            }
          }

          for (const role in this.rolesArrayUpdate) { // AGREGAR NUEVOS ROLES

            if (this.rolesArrayUpdate[role] === true // ROLE EN TRUE
              && !this.user.roles.find(x => x.role === role)) { // PERO NO EXISTE EN EL USER
              this.userService.insertRoles(new Role(role), updateduser.id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido actualizado satisfactoriamente.';
                },
                (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
            }
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`))
      .then(() => this.updateUserInfo(id));
  }

  public registry() {
    this.submitted = true;
    this.errorMessages = [];
      this.userService.insertNewUser(this.user).subscribe(
        createdUser => {
          this.filterParams.success = 'El usuario ha sido creado satisfactoriamente.';
          for (const role in this.rolesArrayUpdate) {
            if (this.rolesArrayUpdate[role] !== false) {
              this.userService.insertRoles(new Role(role), createdUser.id).subscribe(
                data => {
                  this.filterParams.success = 'El usuario ha sido creado satisfactoriamente.';
                },
                (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
                  || `${error.statusText} : ${error.message}`));
            }
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`));
  }

  toggle(checked: boolean, rol: string) {
    this.rolesArrayUpdate[rol] = checked;
  }

  private updateUserInfo(id: number) {
    this.userService.getOneUser(id).subscribe(
      userdata => {
        this.user = userdata;
        for (const role in this.user.roles) {
          if (role) {
            this.rolesArrayUpdate[this.user.roles[role].role] = true;
          }
        }
        this.registerForm = this.formBuilder.group({
          email: [{ value: this.user.email, disabled: true }],
        });
      },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

}
