import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root',
})
export class UsersService {


  private currentUser = undefined;

  constructor(private httpClient: HttpClient) { }

  public getUsers(page: number, size: number): Observable<Object> {
    let pageParams: HttpParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
    return this.httpClient.get('../api/users', { params: pageParams });
  }

  public async getUserInfo(): Promise<any> {
    return new Promise(resolve => {
      if (this.currentUser !== undefined) {
        resolve(this.currentUser);
      } else {
        this.httpClient.get('../api/users/myInfo')
          .subscribe(user => {
            this.currentUser = user;
            resolve(user);
          });
      }
    });
  }

  public logout(): Observable<any> {
    return this.httpClient.post('../api/logout', {});
  }

  public insertNewUser(user: User): Observable<Object> {
    return this.httpClient.post('../api/users', user);

  }

  public insertRoles(rol: string, id: number): Observable<Object> {

    console.log('rooooooool ' + rol + ' / ' + id);

    return this.httpClient.post(`../api/users/${id}/roles`,

      {


        "role": rol,
        "description": id

      });

  }

  public getOneUser(id: number): Observable<Object> {
    return this.httpClient.get(`../api/users/${id}`);
  }

  public UpdateUser(id: number, user: User): Observable<Object> {
    return this.httpClient.put(`../api/users/${id}`, user);
  }

    public DeleteRoles(userid: number,rolId: number): Observable<Object> {
      return this.httpClient.delete(`../api/users/${userid}/roles/${rolId}`);
  }

}
