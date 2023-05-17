import { Component, OnInit } from '@angular/core';
import { GraphQueryService } from '../service/graph-query.service';

@Component({
  selector: 'app-program-dashboard',
  templateUrl: './program-dashboard.component.html',
  styleUrls: ['./program-dashboard.component.css']
})
export class ProgramDashboardComponent implements OnInit {

  programArray: any;
  retrievedImage: any;

  constructor(private graphQueryService : GraphQueryService) { }
  ngOnInit(): void {
    this.graphQueryService.getAllProgramDetails().subscribe(response => {
      console.log('program array', response);
      this.programArray = response;

    }, error => {
      console.log('error', error);
    })
  }


}
