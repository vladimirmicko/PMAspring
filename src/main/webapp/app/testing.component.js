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
var test_1 = require('./test');
var test_service_1 = require('./test.service');
var ng2_bootstrap_1 = require('ng2-bootstrap');
var TestingComponent = (function () {
    function TestingComponent(testService) {
        this.testService = testService;
        this.test = new test_1.Test();
    }
    TestingComponent.prototype.ngOnInit = function () {
        this.getTests();
    };
    TestingComponent.prototype.showChildModal = function () {
        this.childModal.show();
    };
    TestingComponent.prototype.hideChildModal = function () {
        this.childModal.hide();
    };
    TestingComponent.prototype.getTests = function () {
        var _this = this;
        this.testService.getTest()
            .subscribe(function (test) { return _this.test = test; }, function (error) { return _this.errorMessage = error; });
    };
    TestingComponent.prototype.onChangeJavaFile = function (event) {
        if (event.target.files[0]) {
            this.imageFile = event.target.files[0];
        }
    };
    TestingComponent.prototype.upload = function () {
        var formData = new FormData();
        formData.append('imageFile', this.imageFile);
        this.subscriptions['upload'] = this.testService.uploadRest(formData)
            .subscribe(function (res) {
        }, function (err) {
        });
    };
    __decorate([
        core_1.ViewChild('childModal'), 
        __metadata('design:type', ng2_bootstrap_1.ModalDirective)
    ], TestingComponent.prototype, "childModal", void 0);
    TestingComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'testing',
            templateUrl: './testing.component.html',
            styleUrls: ['./testing.component.css']
        }), 
        __metadata('design:paramtypes', [test_service_1.TestService])
    ], TestingComponent);
    return TestingComponent;
}());
exports.TestingComponent = TestingComponent;
//# sourceMappingURL=testing.component.js.map