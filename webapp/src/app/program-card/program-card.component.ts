import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GraphQueryService } from '../service/graph-query.service';
import { MatDialog } from '@angular/material/dialog';
import { CategorySelectionComponent } from '../category-selection/category-selection.component';

@Component({
  selector: 'app-program-card',
  templateUrl: './program-card.component.html',
  styleUrls: ['./program-card.component.css']
})
export class ProgramCardComponent implements OnInit {

  @Input() public program: any;
  @Input() public generateConcept:any;
  retrievedImage: any;

  constructor(private graphQueryService : GraphQueryService,private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.retrievedImage = 'data:image/jpeg;base64,' + this.program.picByte;
  }

  createContent(program){
    console.log(program.domainName+ " "+ program.programTitle);
    this.router.navigate(['/home/contentgeneration',program.programTitle,program.domainName]);

  }

  getTOC(domain){
    this.router.navigate(['/home/programcontent', domain]);
  }

  levelSelectionDialogBox(domain){
    console.log("name"+domain)
    this.dialog.open(CategorySelectionComponent,{data:domain});
  }
}
