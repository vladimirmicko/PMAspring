import { RouterModule, Routes } from '@angular/router';

import { AuthGuard }            from './auth-guard.service';

import { TestAdminComponent }   from './test-admin.component';
import { AboutComponent }       from './about.component';
import { LoginComponent }       from './login.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'tests',
    component: TestAdminComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'about',
    component: AboutComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];


export const routing = RouterModule.forRoot(routes);