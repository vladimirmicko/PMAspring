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
var test_service_1 = require('./test.service');
var ng2_bootstrap_1 = require('ng2-bootstrap');
var TestAdminComponent = (function () {
    function TestAdminComponent(testService) {
        this.testService = testService;
        this.tests = [];
    }
    TestAdminComponent.prototype.ngOnInit = function () {
        this.getTests();
    };
    TestAdminComponent.prototype.showChildModal = function () {
        this.childModal.show();
    };
    TestAdminComponent.prototype.hideChildModal = function () {
        this.childModal.hide();
    };
    TestAdminComponent.prototype.proba = function (test) {
        console.log(test.testName);
    };
    TestAdminComponent.prototype.getTests = function () {
        var _this = this;
        this.testService.getTests()
            .subscribe(function (tests) { return _this.tests = tests; }, function (error) { return _this.errorMessage = error; });
    };
    __decorate([
        core_1.ViewChild('childModal'), 
        __metadata('design:type', ng2_bootstrap_1.ModalDirective)
    ], TestAdminComponent.prototype, "childModal", void 0);
    TestAdminComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'test-admin',
            templateUrl: './test-admin.component.html',
            styleUrls: ['./test-admin.component.css']
        }), 
        __metadata('design:paramtypes', [test_service_1.TestService])
    ], TestAdminComponent);
    return TestAdminComponent;
}());
exports.TestAdminComponent = TestAdminComponent;
//# sourceMappingURL=test-admin.component.js.map