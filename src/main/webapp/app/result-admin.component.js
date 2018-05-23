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
var result_1 = require('./result');
var result_service_1 = require('./result.service');
var router_1 = require('@angular/router');
var ResultAdminComponent = (function () {
    function ResultAdminComponent(resultService, router) {
        this.resultService = resultService;
        this.router = router;
        this.results = [];
        this.result = new result_1.Result();
    }
    ResultAdminComponent.prototype.ngOnInit = function () {
        this.getResults();
    };
    ResultAdminComponent.prototype.toggleSupervised = function (result) {
        var _this = this;
        this.resultService.toggleSupervised(result.id).subscribe(function (result) { return _this.result = result; }, function (error) { return _this.errorMessage = error; });
    };
    ResultAdminComponent.prototype.deleteResult = function (result) {
        var _this = this;
        console.log('Result deleted:' + result.testName);
        this.subscriptions = this.resultService.deleteResult(result)
            .subscribe(function (res) {
            _this.getResults();
        }, function (err) {
        });
    };
    ResultAdminComponent.prototype.getResults = function () {
        var _this = this;
        this.resultService.getResults()
            .subscribe(function (results) { return _this.results = results; }, function (error) { return _this.errorMessage = error; });
    };
    ResultAdminComponent.prototype.redirectToTest = function (result, modal) {
        this.router.navigate(['results/' + result.id]);
    };
    ResultAdminComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'result-admin',
            templateUrl: './result-admin.component.html',
            styleUrls: ['./result-admin.component.css']
        }), 
        __metadata('design:paramtypes', [result_service_1.ResultService, router_1.Router])
    ], ResultAdminComponent);
    return ResultAdminComponent;
}());
exports.ResultAdminComponent = ResultAdminComponent;
//# sourceMappingURL=result-admin.component.js.map