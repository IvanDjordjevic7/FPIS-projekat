import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule} from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { PrijaveComponent } from './components/prijave/prijave.component';
import { ReactiveFormsModule } from '@angular/forms';



const routes: Routes = [
  { path: 'manifestacije', component: HomeComponent },
  { path: 'izlozbe', component: HomeComponent },
  { path: 'prijave', component: PrijaveComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PrijaveComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
