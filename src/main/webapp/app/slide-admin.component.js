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
var forms_1 = require('@angular/forms');
var router_1 = require('@angular/router');
var SlideAdminComponent = (function () {
    function SlideAdminComponent(testService, route) {
        this.testService = testService;
        this.route = route;
        this.events = [];
        this.tests = [];
        this.test = new test_1.Test();
    }
    SlideAdminComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.getTests();
        this.setEditForm();
        this.sub = this.route.params.subscribe(function (params) {
            _this.id = +params['id'];
            console.log("This is the id: " + _this.id);
            // In a real app: dispatch action to load the details here.
        });
    };
    SlideAdminComponent.prototype.setEditForm = function (test) {
        if (!test) {
            test = new test_1.Test();
        }
        this.editForm = new forms_1.FormGroup({
            id: new forms_1.FormControl(test.id),
            testName: new forms_1.FormControl(test.testName, [forms_1.Validators.required, forms_1.Validators.minLength(5)]),
            description: new forms_1.FormControl(test.description),
            creationDate: new forms_1.FormControl(test.creationDate)
        });
    };
    SlideAdminComponent.prototype.editTestModal = function (test, modal) {
        this.setEditForm(test);
        console.log(test.testName);
        this.test = test;
        modal.show();
    };
    SlideAdminComponent.prototype.addNewTestModal = function (modal) {
        var test = new test_1.Test();
        this.test = test;
        this.setEditForm(test);
        modal.show();
    };
    SlideAdminComponent.prototype.deleteTestModal = function (test, modal) {
        console.log(test.testName);
        this.test = test;
        modal.show();
    };
    SlideAdminComponent.prototype.addEditTest = function (test, isValid, modal) {
        var _this = this;
        this.submitted = true;
        console.log(test, isValid);
        modal.hide();
        var formData = new FormData();
        formData.append('imageFile', this.imageFile);
        formData.append('test', new Blob([JSON.stringify(test)], { type: "application/json" }));
        this.subscriptions = this.testService.uploadTest(formData)
            .subscribe(function (res) {
            _this.getTests();
        }, function (err) {
        });
    };
    SlideAdminComponent.prototype.deleteTest = function (test, modal) {
        var _this = this;
        console.log('Test deleted:' + test.testName);
        modal.hide();
        this.subscriptions = this.testService.deleteTest(test)
            .subscribe(function (res) {
            _this.getTests();
        }, function (err) {
        });
    };
    SlideAdminComponent.prototype.onChangeJavaFile = function (event) {
        if (event.target.files[0]) {
            this.imageFile = event.target.files[0];
        }
    };
    SlideAdminComponent.prototype.getTests = function () {
        var _this = this;
        this.testService.getTests()
            .subscribe(function (tests) { return _this.tests = tests; }, function (error) { return _this.errorMessage = error; });
    };
    SlideAdminComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    SlideAdminComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'slide-admin',
            templateUrl: './slide-admin.component.html',
            styleUrls: ['./slide-admin.component.css']
        }), 
        __metadata('design:paramtypes', [test_service_1.TestService, router_1.ActivatedRoute])
    ], SlideAdminComponent);
    return SlideAdminComponent;
}());
exports.SlideAdminComponent = SlideAdminComponent;
//# sourceMappingURL=slide-admin.component.js.map