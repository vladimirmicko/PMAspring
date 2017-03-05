import { Injectable }     from '@angular/core';
import { CanActivate }    from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

  
  canActivate() {
    // return false;
    console.log('AuthGuard#canActivate called');
    if(localStorage.getItem('currentUser'))
        return true;
    else
        return false;
  }
}
