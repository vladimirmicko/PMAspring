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
var slide_1 = require('./slide');
var test_service_1 = require('./test.service');
var forms_1 = require('@angular/forms');
var router_1 = require('@angular/router');
var SlideAdminComponent = (function () {
    function SlideAdminComponent(testService, route) {
        this.testService = testService;
        this.route = route;
        this.events = [];
        this.test = new test_1.Test();
        this.slide = new slide_1.Slide();
        this.slides = [];
    }
    SlideAdminComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            _this.id = +params['id'];
            console.log("This is the id: " + _this.id);
            _this.getTest(_this.id);
            _this.setEditForm();
        });
    };
    SlideAdminComponent.prototype.setEditForm = function (slide) {
        if (!slide) {
            slide = new slide_1.Slide();
        }
        this.editForm = new forms_1.FormGroup({
            id: new forms_1.FormControl(slide.id),
            slideName: new forms_1.FormControl(slide.slideName, [forms_1.Validators.required, forms_1.Validators.minLength(5)]),
            delay: new forms_1.FormControl(slide.delay, [forms_1.Validators.required])
        });
    };
    SlideAdminComponent.prototype.editSlideModal = function (slide, modal) {
        this.setEditForm(slide);
        console.log(slide.slideName);
        this.slide = slide;
        modal.show();
    };
    SlideAdminComponent.prototype.addNewSlideModal = function (modal) {
        var slide = new slide_1.Slide();
        this.slide = slide;
        this.setEditForm(slide);
        modal.show();
    };
    SlideAdminComponent.prototype.deleteSlideModal = function (slide, modal) {
        console.log(slide.slideName);
        this.slide = slide;
        modal.show();
    };
    SlideAdminComponent.prototype.addEditSlide = function (test, isValid, modal) {
        var _this = this;
        this.submitted = true;
        console.log(test, isValid);
        modal.hide();
        var formData = new FormData();
        formData.append('primingImageFile', this.primingImageFile);
        formData.append('testImageFile', this.testImageFile);
        formData.append('test', new Blob([JSON.stringify(test)], { type: "application/json" }));
        this.subscriptions = this.testService.uploadSlide(formData)
            .subscribe(function (res) {
            _this.getTest(test.id);
        }, function (err) {
        });
    };
    SlideAdminComponent.prototype.deleteSlide = function (slide, modal) {
        var _this = this;
        console.log('Slide deleted:' + slide.slideName);
        modal.hide();
        this.subscriptions = this.testService.deleteSlide(slide)
            .subscribe(function (res) {
            _this.getTest(_this.test.id);
        }, function (err) {
        });
    };
    SlideAdminComponent.prototype.onChangeJavaFile = function (event) {
        if (event.target.files[0]) {
            this.primingImageFile = event.target.files[0];
        }
    };
    SlideAdminComponent.prototype.getTest = function (id) {
        var _this = this;
        this.testService.getTest(id)
            .subscribe(function (test) { _this.test = test; _this.slides = test.slideList; }, function (error) { return _this.errorMessage = error; });
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