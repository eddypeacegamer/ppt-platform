import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root',
})
export class UsersService {

  constructor(private httpClient: HttpClient) { }

  public getUsers(): Observable<any> {
    return this.httpClient.get('../api/users');
  }

  public getUserInfo(): Observable<any> {
    return this.httpClient.get('../api/users/myInfo');
  }

  public logout(): Observable<any> {
    return this.httpClient.get('../api/logout');
  }
}
