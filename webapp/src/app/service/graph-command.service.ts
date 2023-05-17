import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { Program } from '../model/program';

@Injectable({
  providedIn: 'root'
})
export class GraphCommandService {

  constructor(private httpClient:HttpClient) { }

  public createNewProgram( file:File[],programDetail:Program) {
    console.log(file);
    const data:FormData= new FormData();
    data.append('file',file[0]);
    data.append('file',file[1]);
    data.append('details',JSON.stringify(programDetail));
    return this.httpClient.post<Program>("http://localhost:8060/api/v1/program", data,{
      headers:{responseType:'text'}
    })
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
