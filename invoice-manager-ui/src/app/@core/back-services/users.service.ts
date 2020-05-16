import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';

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

  public insertNewUser(correo: string, activo: boolean = true): Observable<Object> {

    console.log('Creadooo nuevo usuario22 ' + correo + ' / ' + activo);

    return this.httpClient.post('../api/users',
      {

        "email": correo,
        "activo": activo

      });

  }

  public insertRoles(rol: string, id: number): Observable<Object> {

    console.log('rooooooool ' + rol + ' / ' + id);

    return this.httpClient.post(`../api/users/${id}/roles`,

      {


        "role": rol,
        "description": id


      });

  }

  public Checkcorreo(rol: string, id: number): Observable<Object> {

    console.log('rooooooool ' + rol + ' / ' + id);

    return this.httpClient.post(`../api/users/${id}/roles`,

      {


        "role": rol,
        "description": id


      });

  }


}
