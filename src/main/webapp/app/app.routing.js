"use strict";
var router_1 = require('@angular/router');
var auth_guard_service_1 = require('./auth-guard.service');
var admin_component_1 = require('./admin.component');
var test_admin_component_1 = require('./test-admin.component');
var about_component_1 = require('./about.component');
var testing_component_1 = require('./testing.component');
var login_component_1 = require('./login.component');
var routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    {
        path: 'tests',
        component: test_admin_component_1.TestAdminComponent,
        canActivate: [auth_guard_service_1.AuthGuard]
    },
    {
        path: 'about',
        component: about_component_1.AboutComponent,
        canActivate: [auth_guard_service_1.AuthGuard]
    },
    {
        path: 'admin',
        component: admin_component_1.AdminComponent,
        canActivate: [auth_guard_service_1.AuthGuard]
    },
    {
        path: 'testing',
        component: testing_component_1.TestingComponent,
        canActivate: [auth_guard_service_1.AuthGuard]
    },
    {
        path: 'login',
        component: login_component_1.LoginComponent
    },
    { path: '**', redirectTo: 'login', pathMatch: 'full' }
];
exports.routing = router_1.RouterModule.forRoot(routes);
//# sourceMappingURL=app.routing.js.map