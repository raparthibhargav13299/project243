import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContentGenerationComponent } from './content-generation/content-generation.component';
import { ContentPageComponent } from './content-page/content-page.component';
import { CourseContainerComponent } from './course-container/course-container.component';
import { LoginComponent } from './login/login.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProgramContainerComponent } from './program-container/program-container.component';
import { ProgramCreationComponent } from './program-creation/program-creation.component';
import { RegisterComponent } from './register/register.component';
import { ProgramVideoPlayerComponent } from './program-video-player/program-video-player.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidenavProgramContentComponent } from './sidenav-program-content/sidenav-program-content.component';
import { LandingComponent } from './landing/landing.component';
import { TrainingContentComponent } from './training-content/training-content.component';
import { ProgramDashboardComponent } from './program-dashboard/program-dashboard.component';
import { ContentViewerComponent } from './content-viewer/content-viewer.component';

const routes: Routes = [

  {

    path:'home',component:NavBarComponent,
    children:[
      {
        path:'',component:LandingComponent
      },
      {
        path:'programcreation',component:ProgramCreationComponent
      },
      {
        path:'videoplayer',component: ProgramVideoPlayerComponent
      },
      {
        path:'contentgeneration/:title/:domain', component: ContentGenerationComponent
      },

      {
        path:'programcontent/:domain/:intent', component:SidenavProgramContentComponent
      },
      {
        path:'program',component:ProgramContainerComponent
      },
      {
        path:'content-page',component:ContentPageComponent
      },
      {
        path:'side-bar',component:SidebarComponent
      },
      {
        path:'course-container',component:CourseContainerComponent
      },
      {
        path:'program-dashboard',component:ProgramDashboardComponent
      }



    ]
  },
  {
    path:'login',component:LoginComponent
  },
  {
    path:'register',component:RegisterComponent
  },
  {
    path:'training',component:TrainingContentComponent
  },
  {
    path:'content-viewer',component:ContentViewerComponent
  },

  {
     path:'',redirectTo:'/home',pathMatch:'full'
  },

]

export const appRouting = RouterModule.forRoot(routes);

@NgModule({

  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
