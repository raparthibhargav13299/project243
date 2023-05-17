import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { DomSanitizer,SafeUrl,SafeResourceUrl } from "@angular/platform-browser";
@Injectable({
  providedIn: 'root'
})
export class GraphQueryService {
  getUrls: any;

  constructor(private httpClient: HttpClient,private sanitizer: DomSanitizer) { }

  public getAllProgramDetails() {
    return this.httpClient.get<any>("http://localhost:8097/api/v1/programs")
      .pipe(catchError(this.errorHandler));
  }


  public getProgramTitles() {
    return this.httpClient.get<any>("http://localhost:8097/api/v1/test/Cosmetology/BEGINNER")
      .pipe(catchError(this.errorHandler));
  }

  public getUrl(concept,title) {
    return this.httpClient.get<any>("http://localhost:8097/api/v1/documenturl"+"/"+concept+"/"+title)
    .pipe(catchError(this.errorHandler));
  }

  httpOptionsPlain:any = {
    headers: new HttpHeaders({
      'Accept': 'text/plain',
      'Content-Type': 'text/plain' 
    }),
    'responseType': 'text'
  };

  public getHtmlData() {
 
    return this.httpClient.get<any>("https://api.scrapingdog.com/scrape?api_key=60dc52a4b04cbc4bca346932&url=https://angular.io/api/platform-browser/DomSanitizer",this.httpOptionsPlain)
    // this.httpClient.get(urlll, {responseType: 'text'})
    .pipe(catchError(this.errorHandler));

  }







  public getProgramsByEmailId () {
    let email = localStorage.getItem('emailId');
    console.log(email);
    return this.httpClient.get<any>('http://localhost:8097/api/v1/program/' + email)
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
