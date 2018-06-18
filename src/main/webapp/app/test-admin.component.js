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
var TestAdminComponent = (function () {
    function TestAdminComponent(testService, router) {
        this.testService = testService;
        this.router = router;
        this.events = [];
        this.tests = [];
        this.test = new test_1.Test();
        this.classifier = "";
    }
    TestAdminComponent.prototype.ngOnInit = function () {
        this.getTests();
        this.setEditForm();
    };
    TestAdminComponent.prototype.setEditForm = function (test) {
        if (!test) {
            test = new test_1.Test();
        }
        this.editForm = new forms_1.FormGroup({
            id: new forms_1.FormControl(test.id),
            testName: new forms_1.FormControl(test.testName, [forms_1.Validators.required, forms_1.Validators.minLength(5)]),
            description: new forms_1.FormControl(test.description),
            creationDate: new forms_1.FormControl(test.creationDate),
            resultYesDescription: new forms_1.FormControl(test.resultYesDescription),
            resultNoDescription: new forms_1.FormControl(test.resultNoDescription)
        });
    };
    TestAdminComponent.prototype.editTestModal = function (test, modal) {
        this.setEditForm(test);
        console.log(test.testName);
        this.test = test;
        modal.show();
    };
    TestAdminComponent.prototype.addNewTestModal = function (modal) {
        var test = new test_1.Test();
        this.test = test;
        this.setEditForm(test);
        modal.show();
    };
    TestAdminComponent.prototype.deleteTestModal = function (test, modal) {
        console.log(test.testName);
        this.test = test;
        modal.show();
    };
    TestAdminComponent.prototype.trainClassifier = function (test) {
        var _this = this;
        console.log(test.testName);
        this.test = test;
        this.subscriptions = this.testService.trainClassifier(test)
            .subscribe(function (res) {
            _this.classifier = res.message;
            _this.classifierModal.show();
            // this.getTests();
        }, function (err) {
            _this.router.navigateByUrl('login/');
        });
    };
    TestAdminComponent.prototype.addEditTest = function (test, isValid, modal) {
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
            _this.router.navigateByUrl('login/');
        });
    };
    TestAdminComponent.prototype.deleteTest = function (test, modal) {
        var _this = this;
        console.log('Test deleted:' + test.testName);
        modal.hide();
        this.subscriptions = this.testService.deleteTest(test)
            .subscribe(function (res) {
            _this.getTests();
        }, function (err) {
            _this.router.navigateByUrl('login/');
        });
    };
    TestAdminComponent.prototype.onChangeJavaFile = function (event) {
        if (event.target.files[0]) {
            this.imageFile = event.target.files[0];
        }
    };
    TestAdminComponent.prototype.getTests = function () {
        var _this = this;
        this.testService.getTests()
            .subscribe(function (tests) { return _this.tests = tests; }, function (error) {
            _this.errorMessage = error;
            _this.router.navigateByUrl('login/');
        });
    };
    TestAdminComponent.prototype.redirectToTest = function (test, modal) {
        this.router.navigate(['tests/' + test.id]);
    };
    __decorate([
        core_1.ViewChild('classifierModal'), 
        __metadata('design:type', Object)
    ], TestAdminComponent.prototype, "classifierModal", void 0);
    TestAdminComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'test-admin',
            templateUrl: './test-admin.component.html',
            styleUrls: ['./test-admin.component.css']
        }), 
        __metadata('design:paramtypes', [test_service_1.TestService, router_1.Router])
    ], TestAdminComponent);
    return TestAdminComponent;
}());
exports.TestAdminComponent = TestAdminComponent;
//# sourceMappingURL=test-admin.component.js.map