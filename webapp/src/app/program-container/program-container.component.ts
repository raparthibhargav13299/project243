import { Component, OnInit } from '@angular/core';
import { GraphQueryService } from '../service/graph-query.service';

@Component({
  selector: 'app-program-container',
  templateUrl: './program-container.component.html',
  styleUrls: ['./program-container.component.css']
})
export class ProgramContainerComponent implements OnInit {

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


  // ngOnInit(): void {
  //   this.graphQueryService.getProgramsByEmailId().subscribe(response => {
  //     console.log('program array', response);
  //     this.programArray = response;

  //   }, error => {
  //     console.log('error', error);
  //   })
  // }

}
