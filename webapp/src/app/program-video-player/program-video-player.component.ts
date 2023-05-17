import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmbedVideoService } from 'ngx-embed-video';

import { GraphQueryService } from '../service/graph-query.service';
@Component({
  selector: 'app-program-video-player',
  templateUrl: './program-video-player.component.html',
  styleUrls: ['./program-video-player.component.css']
})
export class ProgramVideoPlayerComponent implements OnInit {
  iframe_html: any;
  youtubeUrl = "https://www.youtube.com/watch?v=ZZ_vnqvW4DQ";
  vid:any;
  
  @Input() documentObject;
  // documentObject="";
  // doc:any=this.documentObject.type;
  constructor(private embedService: EmbedVideoService,private graphQueryService : GraphQueryService, private activateRoute: ActivatedRoute) { this.iframe_html = this.embedService.embed(this.youtubeUrl);
  }

  ngOnInit(): void {

    
    
    
  }




  // getDur(){
  //   this.vid=document.getElementById("video");
  //   alert(this.vid.duration);
  // }
}
