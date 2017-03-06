import { Component, OnInit, ViewChild } from '@angular/core';
import { TestService } from './test.service';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    error = '';

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) { }


    ngOnInit(): void {
        this.authenticationService.logout();
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['/admin']);
                } else {
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                    this.router.navigate(['/login']);
                }}, 
                err => {
                    this.router.navigateByUrl('/login');
                    if (err === 'Unauthorized') {
                        this.router.navigateByUrl('/login');
                    }
                });
    }

    logout(){
        this.authenticationService.logout();
    }
}


