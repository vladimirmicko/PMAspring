import { RouterModule, Routes } from '@angular/router';

import { AuthGuard }            from './auth-guard.service';

import { AdminComponent }       from './admin.component';
import { TestingComponent }     from './testing.component';
import { LoginComponent }       from './login.component';


const routes: Routes = [
  { path: '**', redirectTo: '', pathMatch: 'full' },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard]
  },
    {
    path: 'testing',
    component: TestingComponent,
    canActivate: [AuthGuard]
  },
    {
    path: 'login',
    component: LoginComponent
  },
];


export const routing = RouterModule.forRoot(routes);