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
var TestService = (function () {
    function TestService(http) {
        this.http = http;
        this.headers = new http_1.Headers();
        this.prepareHeaders();
    }
    TestService.prototype.prepareHeaders = function () {
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Authorization', localStorage.getItem('currentUser'));
        this.options = new http_2.RequestOptions({ headers: this.headers });
    };
    TestService.prototype.uploadTest = function (formData) {
        this.headers = new http_1.Headers();
        this.headers.append('Accept', 'application/json');
        this.headers.append('Authorization', localStorage.getItem('currentUser'));
        this.options = new http_2.RequestOptions({ headers: this.headers });
        return this.http.post('rest/tests/upload', formData, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    TestService.prototype.uploadSlide = function (formData) {
        this.headers = new http_1.Headers();
        this.headers.append('Accept', 'application/json');
        this.headers.append('Authorization', localStorage.getItem('currentUser'));
        this.options = new http_2.RequestOptions({ headers: this.headers });
        return this.http.post('rest/tests/slides/upload', formData, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    TestService.prototype.getTests = function () {
        this.prepareHeaders();
        return this.http.get('rest/tests', this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    TestService.prototype.getTest = function (id) {
        this.prepareHeaders();
        return this.http.get('rest/tests/' + id, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    TestService.prototype.deleteTest = function (test) {
        this.prepareHeaders();
        return this.http.delete('rest/tests/' + test.id, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    TestService.prototype.deleteSlide = function (slide) {
        this.prepareHeaders();
        return this.http.delete('rest/tests/slides/' + slide.id, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    TestService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    TestService.prototype.handleError = function (error) {
        var errMsg;
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
    TestService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], TestService);
    return TestService;
}());
exports.TestService = TestService;
//# sourceMappingURL=test.service.js.map