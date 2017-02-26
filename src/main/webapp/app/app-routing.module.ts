import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard }               from './auth-guard.service';

import { AdminComponent }   from './admin.component';
import { TestingComponent }      from './testing.component';


const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canLoad: [AuthGuard]
    // outlet: 'popup'
  },
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}