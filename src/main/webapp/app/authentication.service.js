"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var Observable_1 = require('rxjs/Observable');
require('rxjs/add/operator/catch');
require('rxjs/add/operator/map');
var http_2 = require('@angular/http');
var AuthenticationService = (function () {
    function AuthenticationService(http) {
        this.http = http;
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.options = new http_2.RequestOptions({ headers: this.headers });
    }
    AuthenticationService.prototype.login = function (username, password) {
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.options = new http_2.RequestOptions({ headers: this.headers });
        return this.http.post('rest/security/authenticate', JSON.stringify({ username: username, password: password }), this.options)
            .map(function (response) {
            return true;
        })
            .catch(this.handleError);
    };
    AuthenticationService.prototype.logout = function () {
        this.token = null;
        localStorage.removeItem('currentUser');
        var username = "";
        var password = "";
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.options = new http_2.RequestOptions({ headers: this.headers });
        // this.http.get('rest/security/logout').map((response:Response) => {
        //         console.log(response.json());
        //         response.json();
        //     }).subscribe();
        return this.http.get('rest/security/logout', this.options)
            .map(function (response) {
            return true;
        })
            .catch(this.handleError);
    };
    AuthenticationService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    AuthenticationService.prototype.handleError = function (error) {
        var errMsg;
        if (error.status === 401) {
            return Observable_1.Observable.throw('Unauthorized');
        }
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable_1.Observable.throw(errMsg);
    };
    AuthenticationService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], AuthenticationService);
    return AuthenticationService;
}());
exports.AuthenticationService = AuthenticationService;
//# sourceMappingURL=authentication.service.js.map