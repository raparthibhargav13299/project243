import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GraphQueryService } from '../service/graph-query.service';
import { DomSanitizer,SafeUrl,SafeResourceUrl } from "@angular/platform-browser";


@Component({
  selector: 'app-sidenav-program-content',
  templateUrl: './sidenav-program-content.component.html',
  styleUrls: ['./sidenav-program-content.component.css']
})

export class SidenavProgramContentComponent implements OnInit {


@Output() getDocumentUrl= new EventEmitter()

  programName:String="Program Name";
  contentArray: any;
  tocArray: any;
  j:any=44;
  panelOpenState = false;
  documentObject:any;
htmlArray:any;
url:SafeResourceUrl="";
  constructor(private graphQueryService : GraphQueryService, private activateRoute: ActivatedRoute,private sanitizer: DomSanitizer) { }


  ngOnInit() {
   
    this.graphQueryService.getProgramTitles().subscribe(response => {
      console.log(' toc Array',response);
      this. tocArray = response;
    }, error => {
      console.log('error',error);
    })
  }

  getContent(concept,title){
    console.log(title);
 

    this.graphQueryService.getUrl(concept,title).subscribe(response => {
      console.log(concept);
      console.log(title);
    console.log(response.url);
  

    this.graphQueryService.getHtmlData().subscribe((response) => {
      this.htmlArray=response;
      // this.url=this.sanitizer.bypassSecurityTrustScript(this.htmlArray);
      // this.url=this.sanitizer.bypassSecurityTrustResourceUrl(this.htmlArray);
      // this.url=this.sanitizer.bypassSecurityTrustStyle(this.htmlArray);
      // this.url=this.sanitizer.bypassSecurityTrustHtml(this.htmlArray);
      // this.htmlArray = this.sanitizer.bypassSecurityTrustResourceUrl(response);
      console.log(this.htmlArray);
    }, error => {
      console.log('error',error);
    })


    this.documentObject= {title:title,concept:concept,type:"document",response:this.htmlArray};
    // this. tocArray = response;
  }, error => {
    console.log('error',error);
  })







  
  // console.log(concept);

  // this.getDocumentUrl.emit(object);
  }

  getVideo(title,concept){
    console.log(title);
 
    console.log(concept);
    this.documentObject= {title:title,concept:concept,type:"video"};
    // this.getDocumentUrl.emit(object);
  }


 getApiData(concept,title){
     this.graphQueryService.getUrl(concept,title).subscribe(response => {
      console.log(concept);
      console.log(title);
    console.log(response);
    // this. tocArray = response;
  }, error => {
    console.log('error',error);
  })
 }


}
