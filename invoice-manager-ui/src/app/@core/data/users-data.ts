import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { User } from '../../models/user';
import { Role } from '../../models/role';

export abstract class UsersData {

  abstract getUsers(page: number, size: number, filterParams?: any): Observable<GenericPage<User>>;
  abstract getUserInfo(): Promise<User>;
  abstract logout(): Observable<any>;

  abstract insertNewUser(user: User): Observable<User>;
  abstract insertRoles(rol: Role, id: number): Observable<User>;
  abstract getOneUser(user: number): Observable<User>;
  abstract updateUser(user: User): Observable<User>;
  abstract deleteRoles (userid: number, rolId: number): Observable<User>;

}
