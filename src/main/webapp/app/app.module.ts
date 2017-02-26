import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { FormsModule }      from '@angular/forms';
import { Router }           from '@angular/router';
import { RouterModule }     from '@angular/router';

import { HttpModule }       from '@angular/http';

import { AppRoutingModule}  from './app-routing.module'

import { AppComponent }     from './app.component';
import { AdminComponent }   from './admin.component'
import { TestingComponent } from './testing.component'
import { HeroService }      from './hero.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalModule }      from 'ng2-bootstrap';



@NgModule({
  imports: [
    DataTableModule,
    SharedModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  declarations: [
    AppComponent,
    AdminComponent,
    TestingComponent
  ],
  providers: [ HeroService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
