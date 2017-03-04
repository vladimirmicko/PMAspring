import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { FormsModule }      from '@angular/forms';
import { Router }           from '@angular/router';
import { RouterModule }     from '@angular/router';

import { HttpModule }       from '@angular/http';

import { AppRoutingModule}  from './app-routing.module'

import { AppComponent }     from './app.component';
import { AdminComponent }   from './admin.component';
import { LoginComponent } from './login.component';
import { TestingComponent } from './testing.component';
import { TestService }      from './test.service';
import { AuthenticationService }      from './authentication.service';
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
    ModalModule.forRoot()
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    TestingComponent
  ],
  providers: [ TestService, AuthenticationService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }

