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
require('rxjs/Rx');
require('rxjs/add/observable/throw');
var http_2 = require('@angular/http');
var ResultService = (function () {
    function ResultService(http) {
        this.http = http;
        this.headers = new http_1.Headers();
        this.prepareHeaders();
    }
    ResultService.prototype.prepareHeaders = function () {
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.options = new http_2.RequestOptions({ headers: this.headers });
    };
    ResultService.prototype.getResults = function () {
        this.prepareHeaders();
        return this.http.get('rest/results', this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ResultService.prototype.getResult = function (id) {
        this.prepareHeaders();
        return this.http.get('rest/results/' + id, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ResultService.prototype.toggleSupervised = function (id) {
        this.prepareHeaders();
        return this.http.get('rest/results/toggleSupervised/' + id, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ResultService.prototype.deleteResult = function (result) {
        this.prepareHeaders();
        return this.http.delete('rest/results/' + result.id, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ResultService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    ResultService.prototype.handleError = function (error) {
        var errMsg;
        var body;
        if (error instanceof http_1.Response) {
            body = error.json() || '';
            var err = body.message || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable_1.Observable.throw(body);
        ;
    };
    ResultService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], ResultService);
    return ResultService;
}());
exports.ResultService = ResultService;
//# sourceMappingURL=result.service.js.map