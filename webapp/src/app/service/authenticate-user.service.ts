import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateUserService 
{

  constructor(private httpClient:HttpClient) { }

  performLogin(userDetail:User) {
    
    return this.httpClient.post<User>("http://localhost:8090/api/v1/login", userDetail)
      .pipe(catchError(this.errorHandler));
    
  }
  
  public errorHandler(error: Response | any) {

    if (error instanceof ErrorEvent) {
      console.error("an error occured:", error.message);
      return throwError("something bad happened");
    }
    else {
      console.error(`backend returned code ${error.status},` +
        `body was:${error.message}`);
      return throwError(error);
    }
  }

}
