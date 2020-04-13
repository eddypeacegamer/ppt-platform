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
}

export abstract class UsersData {
  abstract getUsers(): Observable<GenericPage<User>>;
  abstract getUserInfo(): Promise<User>;
  abstract logout():Observable<any>;
}
