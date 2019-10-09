import { Observable } from 'rxjs';
import { NbMenuItem } from '@nebular/theme';

export interface User {
  email: string;
  activo: boolean;
  name: string;
  urlPicture : string;
  roles : string[];
  menu : NbMenuItem[];
}

export abstract class UsersData {
  abstract getUsers(): Observable<User[]>;
  abstract getUserInfo(): Observable<User>;
  abstract logout():Observable<any>;
}
