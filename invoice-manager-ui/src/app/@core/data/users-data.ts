import { Observable } from 'rxjs';
import { NbMenuItem } from '@nebular/theme';
import { GenericPage } from '../../models/generic-page';

export interface User {
  email: string;
  activo: boolean;
  name: string;
  urlPicture : string;
  roles : string[];
  menu : NbMenuItem[];
  id:number
}

export abstract class UsersData {
  abstract getUsers(page: number, size: number): Observable<GenericPage<User>>;
  abstract getUserInfo(): Promise<User>;
  
  abstract insertNewUser(correo : string, activo : boolean): Observable<User>;
  abstract  insertRoles(rol : string, id : number): Observable<User>;
 
  abstract logout():Observable<any>;
}
