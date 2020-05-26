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
  public loading = true;
  public user: User = new User();
  public rolesArrayUpdate: IHash = { 'PROMOTOR': false, "TESORERIA": false, "OPERADOR": false, "CONTABILIDAD": false,
    "ADMINISTRADOR": false, "BOVEDA": false, "CONSULTOR": false, "OPERADOR-B": false, "OPERADOR-C": false };

  public errorMessages: string[] = [];
 
  public Params: any = { success: '', message: '', id: '*'};

  constructor(
    private route: ActivatedRoute,
    private userService: UsersData,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.loading = true;
    this.errorMessages = [];
    this.route.paramMap.subscribe(route => {
      const id = route.get('id');
      if (id !== '*') {
        // actualiza informacion usuario
        this.updateUserInfo(+id);

        this.registerForm = this.formBuilder.group({
          email: [{ value: this.user.email, disabled: true }],
          alias: ['', [Validators.required, Validators.pattern('^([0-9a-zA-Z]+)$')]],
          activo: ['Si', Validators.required],
        });
       
      } else {
        //nuevo usuario
        this.registerForm = this.formBuilder.group({
          email: [{ value: this.user.email, disabled: false, },
              [Validators.required, Validators.email, Validators.pattern('^[a-z0-9A-Z._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
          alias: ['', [Validators.required, Validators.pattern('^([0-9a-zA-Z]+)$')] ],
          activo: ['Si', Validators.required],
        });
        this.loading = false;
      }
     
    });
  }

  get f() { return this.registerForm.controls; }

  public  async update() {
    this.submitted = true;
    this.loading = true;
    if (this.registerForm.invalid) { this.loading = false ; return;}
    const id = this.user.id;
      this.userService.updateUser(this.user).toPromise()
      .then(async updateduser => {
          this.Params.success = 'El usuario ha sido actualizado satisfactoriamente.';
          for (const role in this.rolesArrayUpdate) { // QUITA ROLES EXISTENTES
            if (this.rolesArrayUpdate[role] === false // ROLE EN FALSO
                    && this.user.roles.find(x => x.role === role)) { // PERO YA EXISTE EN EL USER
              await this.userService.deleteRoles(this.user.id,
                this.user.roles.find(x => x.role === role).id).toPromise();
            }
          }
          for (const role in this.rolesArrayUpdate) { // AGREGAR NUEVOS ROLES

            if (this.rolesArrayUpdate[role] === true // ROLE EN TRUE
              && !this.user.roles.find(x => x.role === role)) { // PERO NO EXISTE EN EL USER
                await this.userService.insertRoles(new Role(role), updateduser.id).toPromise();
            }
          }
        }, (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
          || `${error.statusText} : ${error.message}`))
      .then(() => this.updateUserInfo(id));
  }

  public registry() {
    this.submitted = true;
    if (this.registerForm.invalid) { this.loading = false ; return;}
    this.errorMessages = [];
    if (this.user.email === undefined) {
      this.errorMessages.push('El email es un campo obligatorio');
    }else if (this.user.email.length === 0) {
      this.errorMessages.push('El email es un campo obligatorio');
    }
    if (this.user.alias === undefined) {
      this.errorMessages.push('El alias es un campo obligatorio');
    }else if ( this.user.alias.length === 0) {
      this.errorMessages.push('El alias es un campo obligatorio');
    }

      this.userService.insertNewUser(this.user).subscribe(
        createdUser => {
          this.Params.success = 'El usuario ha sido creado satisfactoriamente.';
          for (const role in this.rolesArrayUpdate) {
            if (this.rolesArrayUpdate[role] !== false) {
              this.userService.insertRoles(new Role(role), createdUser.id).subscribe(
                data => {
                  this.Params.success = 'El usuario ha sido creado satisfactoriamente.';
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

  public clearInput(){
    this.user = new User();
    for (const role in this.rolesArrayUpdate) {     
        this.rolesArrayUpdate[role] = false;   
    }
    this.Params.success = '';
    this.errorMessages = [];
    this.submitted = false;
  }

  private updateUserInfo(id: number) {
    this.errorMessages = [];

    if (this.user.alias === undefined) {
      this.errorMessages.push('El alias es un campo obligatorio');
    }else if ( this.user.alias.length === 0) {
      this.errorMessages.push('El alias es un campo obligatorio');
    }
    this.userService.getOneUser(id).subscribe(
      userdata => {
        this.user = userdata;
        for (const role in this.user.roles) {
          if (role) {
            this.rolesArrayUpdate[this.user.roles[role].role] = true;
          }
        }
        this.loading = false;
      },
      (error: HttpErrorResponse) => this.errorMessages.push(error.error.message
        || `${error.statusText} : ${error.message}`));
  }

}
