import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor( private router: Router) {}


    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request).pipe(
        catchError((err: HttpErrorResponse) => {
          console.error(err);
          if (err.status === 401 || err.url.indexOf('/oauth2/authorization/google') > 1) {
              console.error('Unauthorized request');
            this.router.navigateByUrl('/unauthorized');
          }
          return throwError( err );
        }));
    }
}
