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
var hero_service_1 = require('./hero.service');
var ng2_bootstrap_1 = require('ng2-bootstrap');
var AdminComponent = (function () {
    function AdminComponent(heroService) {
        this.heroService = heroService;
        this.heroes = [];
    }
    AdminComponent.prototype.ngOnInit = function () {
        this.getHeroes();
    };
    AdminComponent.prototype.showChildModal = function () {
        this.childModal.show();
    };
    AdminComponent.prototype.hideChildModal = function () {
        this.childModal.hide();
    };
    AdminComponent.prototype.getHeroes = function () {
        var _this = this;
        this.heroService.getHeroes()
            .subscribe(function (heroes) { return _this.heroes = heroes; }, function (error) { return _this.errorMessage = error; });
    };
    __decorate([
        core_1.ViewChild('childModal'), 
        __metadata('design:type', ng2_bootstrap_1.ModalDirective)
    ], AdminComponent.prototype, "childModal", void 0);
    AdminComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'my-app',
            templateUrl: './app.component.html',
            styleUrls: ['./app.component.css']
        }), 
        __metadata('design:paramtypes', [hero_service_1.HeroService])
    ], AdminComponent);
    return AdminComponent;
}());
exports.AdminComponent = AdminComponent;
//# sourceMappingURL=admin.component.js.map