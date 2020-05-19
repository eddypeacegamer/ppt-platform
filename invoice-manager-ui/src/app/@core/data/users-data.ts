import { Observable } from 'rxjs';
import { NbMenuItem } from '@nebular/theme';
import { GenericPage } from '../../models/generic-page';

export interface Rolesdata{
  id: number,
  role: string,
  description: string
}

export interface User {
  email: string;
  activo: boolean;
  name: string;
  urlPicture : string;
  roles : Rolesdata[];
  menu : NbMenuItem[];
  id:number
}

export abstract class UsersData {
  abstract getUsers(page: number, size: number): Observable<GenericPage<User>>;
  abstract getUserInfo(): Promise<User>;
  
  abstract insertNewUser(correo : string, activo : boolean): Observable<User>;
  abstract  insertRoles(rol : string, id : number): Observable<User>;
  
  abstract  getOneUser(id : number): Observable<User>;

  abstract UpdateUser(id: number,activo: boolean): Observable<User>;

  abstract DeleteRoles (userid: number,rolId: number) : Observable<User>;

  abstract logout():Observable<any>;
}
