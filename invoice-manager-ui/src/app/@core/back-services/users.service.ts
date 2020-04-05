import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class UsersService {


  private currentUser = undefined;

  constructor(private httpClient: HttpClient) { }

  public getUsers(): Observable<any> {
    return this.httpClient.get('../api/users');
  }

  public async getUserInfo(): Promise<any> {
    return new Promise(resolve => {
      if (this.currentUser !== undefined) {
        resolve(this.currentUser);
      }else {
        this.httpClient.get('../api/users/myInfo')
        .subscribe(user => {
          this.currentUser = user;
          resolve(user);
        });
      }});
  }

  public logout(): Observable<any> {
    return this.httpClient.get('../api/logout');
  }
}
