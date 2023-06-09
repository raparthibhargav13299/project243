import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  showFeature : Boolean;
  constructor(private router: Router) { }

  ngOnInit(): void {
    if(localStorage.getItem('userRole')=="SME"){
        this.showFeature=true;
    }else if(localStorage.getItem('userRole')=="TRAINEE"){
      this.showFeature=false;
    }
    } 
    
  openLogin(){ 
    this.router.navigate(['/login']);
  }

  openRegister(){
    this.router.navigate(['/register']);
  }

}
