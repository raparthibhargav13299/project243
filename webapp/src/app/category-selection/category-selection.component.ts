import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category-selection',
  templateUrl: './category-selection.component.html',
  styleUrls: ['./category-selection.component.css']
})
export class CategorySelectionComponent implements OnInit {

  constructor(private matDialogRef: MatDialogRef<CategorySelectionComponent>,private router: Router, @Inject(MAT_DIALOG_DATA) public domainName:string) { }

  ngOnInit(): void {
    console.log("domain name"+this.domainName)
  }

  toc(radioGroup:any){
    console.log("value "+radioGroup.value)
    this.matDialogRef.close();
    this.router.navigate(['/home/programcontent', this.domainName, radioGroup.value]);
  }

}
