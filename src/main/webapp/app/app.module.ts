import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { FormsModule }      from '@angular/forms';
import { HttpModule }       from '@angular/http';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalModule }      from 'ng2-bootstrap';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { routing }          from './app.routing';

import { AppComponent }     from './app.component';
import { AdminComponent }   from './admin.component';
import { AboutComponent }   from './about.component';
import { LoginComponent }   from './login.component';
import { TestingComponent } from './testing.component';

import { TestService }      from './test.service';
import { AuthGuard }        from './auth-guard.service';
import { AuthenticationService }      from './authentication.service';


@NgModule({
  imports: [
    DataTableModule,
    SharedModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    ModalModule.forRoot()
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    AboutComponent,
    TestingComponent
  ],
  providers: [ 
    TestService, 
    AuthenticationService ,
    AuthGuard,
    {
            provide: LocationStrategy,
            useClass: HashLocationStrategy
    }
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }


