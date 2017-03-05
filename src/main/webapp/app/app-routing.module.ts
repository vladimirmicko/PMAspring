import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard }            from './auth-guard.service';
import { AdminComponent }       from './admin.component';
import { TestingComponent }     from './testing.component';
import { LoginComponent }       from './login.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'admin',
    component: AdminComponent,
    canLoad: [AuthGuard]
  },
    {
    path: 'testing',
    component: TestingComponent,
    canLoad: [AuthGuard]
  },
    {
    path: 'login',
    component: LoginComponent
  },
];


@NgModule({
  imports: 
  [ 
    RouterModule.forRoot(routes, { useHash: true }),
    AuthGuard
   ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}