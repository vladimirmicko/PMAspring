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
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var primeng_1 = require('primeng/primeng');
var ng2_bootstrap_1 = require('ng2-bootstrap');
var common_1 = require('@angular/common');
var app_routing_1 = require('./app.routing');
var app_component_1 = require('./app.component');
var test_admin_component_1 = require('./test-admin.component');
var about_component_1 = require('./about.component');
var login_component_1 = require('./login.component');
var admin_component_1 = require('./admin.component');
var testing_component_1 = require('./testing.component');
var test_service_1 = require('./test.service');
var auth_guard_service_1 = require('./auth-guard.service');
var authentication_service_1 = require('./authentication.service');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                primeng_1.DataTableModule,
                primeng_1.SharedModule,
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                forms_1.ReactiveFormsModule,
                http_1.HttpModule,
                app_routing_1.routing,
                ng2_bootstrap_1.ModalModule.forRoot()
            ],
            declarations: [
                app_component_1.AppComponent,
                login_component_1.LoginComponent,
                test_admin_component_1.TestAdminComponent,
                about_component_1.AboutComponent,
                admin_component_1.AdminComponent,
                testing_component_1.TestingComponent
            ],
            providers: [
                test_service_1.TestService,
                authentication_service_1.AuthenticationService,
                auth_guard_service_1.AuthGuard,
                {
                    provide: common_1.LocationStrategy,
                    useClass: common_1.HashLocationStrategy
                }
            ],
            bootstrap: [app_component_1.AppComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map