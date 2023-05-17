import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProgramCreationComponent } from './program-creation/program-creation.component';
import { RouterModule } from '@angular/router';
import {MatChipsModule} from '@angular/material/chips';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatToolbarModule}  from  '@angular/material/toolbar'
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientModule} from '@angular/common/http';
import {MatGridListModule} from '@angular/material/grid-list';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ContentGenerationComponent } from './content-generation/content-generation.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProgramCardComponent } from './program-card/program-card.component';
import {MatCardModule} from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio'
import { ProgramContainerComponent } from './program-container/program-container.component';
import { SidenavProgramContentComponent } from './sidenav-program-content/sidenav-program-content.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ProgramVideoPlayerComponent } from './program-video-player/program-video-player.component';
import { EmbedVideo } from 'ngx-embed-video';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatRippleModule} from '@angular/material/core';
import { CourseContainerComponent } from './course-container/course-container.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ContentPageComponent } from './content-page/content-page.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { LandingComponent } from './landing/landing.component';
import { TrainingContentComponent } from './training-content/training-content.component';
import { ProgramDashboardComponent } from './program-dashboard/program-dashboard.component';
import { ContentViewerComponent } from './content-viewer/content-viewer.component';
import { CategorySelectionComponent } from './category-selection/category-selection.component';





@NgModule({
  declarations: [
    AppComponent,
    ProgramCreationComponent,
    ContentGenerationComponent,
    NavBarComponent,
    LoginComponent,
    RegisterComponent,

    ProgramCardComponent,
    ProgramContainerComponent,
    SidenavProgramContentComponent,
    ProgramVideoPlayerComponent,
    CourseContainerComponent,
    SidebarComponent,
    ContentPageComponent,
    LandingComponent,
    TrainingContentComponent,
    ProgramDashboardComponent,
    ContentViewerComponent,
    CategorySelectionComponent,





  ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    HttpClientModule,
    MatGridListModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    RouterModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatTabsModule,
    MatCardModule,
    MatDialogModule,
    MatRadioModule,
    MatTooltipModule,
    EmbedVideo.forRoot(),
    MatSidenavModule,
    MatListModule,
    MatMenuModule,
    MatButtonToggleModule,
    MatExpansionModule,
    MatRippleModule,
    MatSnackBarModule


  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [CategorySelectionComponent]
})
export class AppModule { }
