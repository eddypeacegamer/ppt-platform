import { Observable } from 'rxjs';
import { GenericPage } from '../../models/generic-page';
import { User } from '../../models/user';

export abstract class UsersData {
  abstract getUsers(page: number, size: number): Observable<GenericPage<User>>;
  abstract getUserInfo(): Promise<User>;
  
  abstract insertNewUser(user: User): Observable<User>;
  abstract  insertRoles(rol: string, id: number): Observable<User>;
  
  abstract  getOneUser(id: number): Observable<User>;

  abstract UpdateUser(id: number, user: User): Observable<User>;

  abstract DeleteRoles (userid: number, rolId: number): Observable<User>;

  abstract logout(): Observable<any>;
}
