webpackJsonp(["main"],{

/***/ "../../../../../src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppRoutingModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__components_home_home_component__ = __webpack_require__("../../../../../src/app/components/home/home.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__pages_tor_tors_tors_component__ = __webpack_require__("../../../../../src/app/pages/tor/tors/tors.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_dashboard_dashboard_component__ = __webpack_require__("../../../../../src/app/pages/dashboard/dashboard.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_tor_stores_stores_component__ = __webpack_require__("../../../../../src/app/pages/tor/stores/stores.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_tor_maintenance_maintenance_component__ = __webpack_require__("../../../../../src/app/pages/tor/maintenance/maintenance.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_page_not_found_page_not_found_component__ = __webpack_require__("../../../../../src/app/pages/page-not-found/page-not-found.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_associate_associate_endpoints_associate_endpoints_component__ = __webpack_require__("../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_associate_associate_reprocess_associate_reprocess_component__ = __webpack_require__("../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_associate_associate_stores_associate_stores_component__ = __webpack_require__("../../../../../src/app/pages/associate/associate-stores/associate-stores.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__services_api_auth_guard_service__ = __webpack_require__("../../../../../src/app/services/api/auth-guard.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


/// App pages









//providers

var appRoutes = [
    { path: '', redirectTo: 'tor/dashboard/events', pathMatch: 'full' },
    { path: 'healthchecks',
        component: __WEBPACK_IMPORTED_MODULE_2__components_home_home_component__["a" /* HomeComponent */],
        canActivate: [__WEBPACK_IMPORTED_MODULE_11__services_api_auth_guard_service__["a" /* AuthGuardService */]],
        children: [
            { path: '', redirectTo: 'healthchecks/dashboard/elasticHC', pathMatch: 'full' },
            { path: 'dashboard/:view', component: __WEBPACK_IMPORTED_MODULE_4__pages_dashboard_dashboard_component__["a" /* DashboardComponent */] },
        ] },
    { path: 'tor',
        component: __WEBPACK_IMPORTED_MODULE_2__components_home_home_component__["a" /* HomeComponent */],
        canActivate: [__WEBPACK_IMPORTED_MODULE_11__services_api_auth_guard_service__["a" /* AuthGuardService */]],
        children: [
            { path: '', redirectTo: 'tor/dashboard/events', pathMatch: 'full' },
            { path: 'dashboard/:view', component: __WEBPACK_IMPORTED_MODULE_4__pages_dashboard_dashboard_component__["a" /* DashboardComponent */] },
            { path: 'reprocess', component: __WEBPACK_IMPORTED_MODULE_3__pages_tor_tors_tors_component__["a" /* TorsComponent */] },
            { path: 'stores/:market', component: __WEBPACK_IMPORTED_MODULE_5__pages_tor_stores_stores_component__["a" /* StoresComponent */] },
            { path: 'maintenance/:market', component: __WEBPACK_IMPORTED_MODULE_6__pages_tor_maintenance_maintenance_component__["a" /* MaintenanceComponent */] }
        ] },
    { path: 'associate',
        component: __WEBPACK_IMPORTED_MODULE_2__components_home_home_component__["a" /* HomeComponent */],
        canActivate: [__WEBPACK_IMPORTED_MODULE_11__services_api_auth_guard_service__["a" /* AuthGuardService */]],
        children: [
            { path: '', redirectTo: 'associate/swagger', pathMatch: 'full' },
            { path: 'swagger', component: __WEBPACK_IMPORTED_MODULE_8__pages_associate_associate_endpoints_associate_endpoints_component__["a" /* AssociateEndpointsComponent */] },
            { path: 'reprocess', component: __WEBPACK_IMPORTED_MODULE_9__pages_associate_associate_reprocess_associate_reprocess_component__["a" /* AssociateReprocessComponent */] },
            { path: 'stores/:market', component: __WEBPACK_IMPORTED_MODULE_10__pages_associate_associate_stores_associate_stores_component__["a" /* AssociateStoresComponent */] },
            { path: 'maintenance/:market', component: __WEBPACK_IMPORTED_MODULE_6__pages_tor_maintenance_maintenance_component__["a" /* MaintenanceComponent */] }
        ] },
    { path: 'logout', component: __WEBPACK_IMPORTED_MODULE_2__components_home_home_component__["a" /* HomeComponent */] },
    { path: '**', component: __WEBPACK_IMPORTED_MODULE_7__pages_page_not_found_page_not_found_component__["a" /* PageNotFoundComponent */] }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
            imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* RouterModule */].forRoot(appRoutes, { useHash: true })],
            exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* RouterModule */]],
            declarations: [__WEBPACK_IMPORTED_MODULE_7__pages_page_not_found_page_not_found_component__["a" /* PageNotFoundComponent */]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AppComponent = (function () {
    function AppComponent(router) {
        this.router = router;
        this.title = 'Interfaces Suport UI';
    }
    AppComponent.prototype.ngOnInit = function () {
        console.log("inizalization app");
    };
    AppComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-root',
            template: "<router-outlet></router-outlet>"
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* Router */]])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/esm5/forms.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ngx_bootstrap_carousel__ = __webpack_require__("../../../../ngx-bootstrap/carousel/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__components_footer_footer_component__ = __webpack_require__("../../../../../src/app/components/footer/footer.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__components_home_home_component__ = __webpack_require__("../../../../../src/app/components/home/home.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__components_nav_nav_component__ = __webpack_require__("../../../../../src/app/components/nav/nav.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_logout_logout_component__ = __webpack_require__("../../../../../src/app/pages/logout/logout.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_dashboard_dashboard_component__ = __webpack_require__("../../../../../src/app/pages/dashboard/dashboard.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_tor_tors_tors_component__ = __webpack_require__("../../../../../src/app/pages/tor/tors/tors.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_tor_stores_stores_component__ = __webpack_require__("../../../../../src/app/pages/tor/stores/stores.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_tor_stores_load_stores_load_component__ = __webpack_require__("../../../../../src/app/pages/tor/stores-load/stores-load.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__pages_tor_maintenance_maintenance_component__ = __webpack_require__("../../../../../src/app/pages/tor/maintenance/maintenance.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__pages_associate_associate_stores_associate_stores_component__ = __webpack_require__("../../../../../src/app/pages/associate/associate-stores/associate-stores.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__pages_associate_associate_reprocess_associate_reprocess_component__ = __webpack_require__("../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__services_api_auth_guard_service__ = __webpack_require__("../../../../../src/app/services/api/auth-guard.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__services_api_login_service__ = __webpack_require__("../../../../../src/app/services/api/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__services_api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__services_api_user_info_service__ = __webpack_require__("../../../../../src/app/services/api/user-info.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22__services_tor_tor_endpoints__ = __webpack_require__("../../../../../src/app/services/tor/tor-endpoints.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__services_api_menu_service__ = __webpack_require__("../../../../../src/app/services/api/menu.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__services_tor_markets_service__ = __webpack_require__("../../../../../src/app/services/tor/markets.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25__pages_associate_associate_endpoints_associate_endpoints_component__ = __webpack_require__("../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26__services_tor_stores_service__ = __webpack_require__("../../../../../src/app/services/tor/stores.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27__services_tor_tor_service__ = __webpack_require__("../../../../../src/app/services/tor/tor.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__services_tor_maintenance_service__ = __webpack_require__("../../../../../src/app/services/tor/maintenance.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29__services_associate_associate_enpoints__ = __webpack_require__("../../../../../src/app/services/associate/associate-enpoints.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30__services_associate_associate_reprocess_service__ = __webpack_require__("../../../../../src/app/services/associate/associate-reprocess.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31__services_associate_associate_stores_service__ = __webpack_require__("../../../../../src/app/services/associate/associate-stores.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32__services_associate_associate_rp_instances_service__ = __webpack_require__("../../../../../src/app/services/associate/associate-rp-instances.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33__directives_error_message_error_message_component__ = __webpack_require__("../../../../../src/app/directives/error-message/error-message.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_34__directives_wfm_table_directive__ = __webpack_require__("../../../../../src/app/directives/wfm-table.directive.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_35__directives_paginator__ = __webpack_require__("../../../../../src/app/directives/paginator.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_36__directives_data_filter_pipe__ = __webpack_require__("../../../../../src/app/directives/data-filter.pipe.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_37__directives_utc_date_pipe__ = __webpack_require__("../../../../../src/app/directives/utc-date.pipe.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_38__directives_custom_object_pipe_pipe__ = __webpack_require__("../../../../../src/app/directives/custom-object-pipe.pipe.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_39_ngx_bootstrap_datepicker__ = __webpack_require__("../../../../ngx-bootstrap/datepicker/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_40_ngx_bootstrap_timepicker__ = __webpack_require__("../../../../ngx-bootstrap/timepicker/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_41_ngx_bootstrap_dropdown__ = __webpack_require__("../../../../ngx-bootstrap/dropdown/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_42_ngx_papaparse__ = __webpack_require__("../../../../ngx-papaparse/dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_42_ngx_papaparse___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_42_ngx_papaparse__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_43_angular_calendar__ = __webpack_require__("../../../../angular-calendar/dist/esm/src/index.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


















// App services















// components and directives






//third party components





var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_7__components_footer_footer_component__["a" /* FooterComponent */],
                __WEBPACK_IMPORTED_MODULE_8__components_home_home_component__["a" /* HomeComponent */],
                __WEBPACK_IMPORTED_MODULE_9__components_nav_nav_component__["a" /* NavComponent */],
                __WEBPACK_IMPORTED_MODULE_33__directives_error_message_error_message_component__["a" /* ErrorMessageComponent */],
                __WEBPACK_IMPORTED_MODULE_34__directives_wfm_table_directive__["a" /* WfmTableDirective */],
                __WEBPACK_IMPORTED_MODULE_35__directives_paginator__["a" /* WfmPaginator */],
                __WEBPACK_IMPORTED_MODULE_37__directives_utc_date_pipe__["a" /* UtcDatePipe */],
                __WEBPACK_IMPORTED_MODULE_36__directives_data_filter_pipe__["a" /* DataFilterPipe */],
                __WEBPACK_IMPORTED_MODULE_38__directives_custom_object_pipe_pipe__["a" /* CustomObjectPipePipe */],
                __WEBPACK_IMPORTED_MODULE_10__pages_logout_logout_component__["a" /* LogoutComponent */],
                __WEBPACK_IMPORTED_MODULE_11__pages_dashboard_dashboard_component__["a" /* DashboardComponent */],
                __WEBPACK_IMPORTED_MODULE_12__pages_tor_tors_tors_component__["a" /* TorsComponent */],
                __WEBPACK_IMPORTED_MODULE_15__pages_tor_maintenance_maintenance_component__["a" /* MaintenanceComponent */],
                __WEBPACK_IMPORTED_MODULE_14__pages_tor_stores_load_stores_load_component__["a" /* StoresLoadComponent */],
                __WEBPACK_IMPORTED_MODULE_13__pages_tor_stores_stores_component__["a" /* StoresComponent */],
                __WEBPACK_IMPORTED_MODULE_25__pages_associate_associate_endpoints_associate_endpoints_component__["a" /* AssociateEndpointsComponent */],
                __WEBPACK_IMPORTED_MODULE_16__pages_associate_associate_stores_associate_stores_component__["a" /* AssociateStoresComponent */],
                __WEBPACK_IMPORTED_MODULE_17__pages_associate_associate_reprocess_associate_reprocess_component__["a" /* AssociateReprocessComponent */]
                //UserAdminComponent,
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_42_ngx_papaparse__["PapaParseModule"],
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_3__angular_common_http__["b" /* HttpClientModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
                __WEBPACK_IMPORTED_MODULE_39_ngx_bootstrap_datepicker__["a" /* BsDatepickerModule */].forRoot(),
                __WEBPACK_IMPORTED_MODULE_40_ngx_bootstrap_timepicker__["a" /* TimepickerModule */].forRoot(),
                __WEBPACK_IMPORTED_MODULE_41_ngx_bootstrap_dropdown__["a" /* BsDropdownModule */].forRoot(),
                __WEBPACK_IMPORTED_MODULE_43_angular_calendar__["a" /* CalendarModule */].forRoot(),
                __WEBPACK_IMPORTED_MODULE_4_ngx_bootstrap_carousel__["b" /* CarouselModule */].forRoot(),
                __WEBPACK_IMPORTED_MODULE_5__app_routing_module__["a" /* AppRoutingModule */],
            ],
            providers: [
                __WEBPACK_IMPORTED_MODULE_18__services_api_auth_guard_service__["a" /* AuthGuardService */],
                __WEBPACK_IMPORTED_MODULE_19__services_api_login_service__["a" /* LoginService */],
                __WEBPACK_IMPORTED_MODULE_20__services_api_request_service__["a" /* ApiRequestService */],
                __WEBPACK_IMPORTED_MODULE_21__services_api_user_info_service__["a" /* UserInfoService */],
                __WEBPACK_IMPORTED_MODULE_22__services_tor_tor_endpoints__["a" /* TorEndpoints */],
                __WEBPACK_IMPORTED_MODULE_23__services_api_menu_service__["a" /* MenuService */],
                __WEBPACK_IMPORTED_MODULE_27__services_tor_tor_service__["a" /* TorService */],
                __WEBPACK_IMPORTED_MODULE_24__services_tor_markets_service__["a" /* MarketsService */],
                __WEBPACK_IMPORTED_MODULE_26__services_tor_stores_service__["a" /* StoresService */],
                __WEBPACK_IMPORTED_MODULE_28__services_tor_maintenance_service__["a" /* MaintenanceService */],
                __WEBPACK_IMPORTED_MODULE_29__services_associate_associate_enpoints__["a" /* AssociateEnpoints */],
                __WEBPACK_IMPORTED_MODULE_30__services_associate_associate_reprocess_service__["a" /* AssociateReprocessService */],
                __WEBPACK_IMPORTED_MODULE_32__services_associate_associate_rp_instances_service__["a" /* AssociateRpInstancesService */],
                __WEBPACK_IMPORTED_MODULE_31__services_associate_associate_stores_service__["a" /* AssociateStoresService */]
                /* AssociatesService,
                 UserAdminService*/
            ],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* AppComponent */]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "../../../../../src/app/commons/json-2-csv.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Json2Csv; });
var Json2Csv = (function () {
    function Json2Csv(content, filename) {
        this.DEFAULT_FILENAME = 'csvSample.csv';
        this.generateFile(content, filename);
    }
    Json2Csv.prototype.generateFile = function (content, filename) {
        if (content == '' || content == null || content == undefined) {
            console.error("Invalid data");
            return;
        }
        var blob = new Blob([content], { "type": "text/csv;charset=utf8;" });
        if (navigator.msSaveBlob) {
            navigator.msSaveBlob(blob, (filename == undefined || filename == null || filename == '') ? this.DEFAULT_FILENAME : filename.replace(/ /g, "_") + ".csv");
        }
        else {
            var uri = 'data:attachment/csv;charset=utf-8,' + encodeURI(content);
            var link = document.createElement("a");
            link.href = URL.createObjectURL(blob);
            link.setAttribute('visibility', 'hidden');
            link.download = (filename == undefined || filename == null || filename == '') ? this.DEFAULT_FILENAME : filename.replace(/ /g, "_") + ".csv";
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    };
    return Json2Csv;
}());



/***/ }),

/***/ "../../../../../src/app/components/footer/footer.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/footer/footer.component.html":
/***/ (function(module, exports) {

module.exports = "<p>\n  footer works!\n</p>\n"

/***/ }),

/***/ "../../../../../src/app/components/footer/footer.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return FooterComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FooterComponent = (function () {
    function FooterComponent() {
    }
    FooterComponent.prototype.ngOnInit = function () {
    };
    FooterComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-footer',
            template: __webpack_require__("../../../../../src/app/components/footer/footer.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/footer/footer.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], FooterComponent);
    return FooterComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/home/home.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/home/home.component.html":
/***/ (function(module, exports) {

module.exports = "<div id=\"application\">\n  <app-nav style=\"background: #495057;\"></app-nav>\n  <br/>\n  <router-outlet></router-outlet>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/components/home/home.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomeComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HomeComponent = (function () {
    function HomeComponent() {
        this.showAppAlert = false;
    }
    HomeComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-home',
            template: __webpack_require__("../../../../../src/app/components/home/home.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/home/home.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], HomeComponent);
    return HomeComponent;
}());



/***/ }),

/***/ "../../../../../src/app/components/nav/nav.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".menu-active{\n    background-color: #1f2425;\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/nav/nav.component.html":
/***/ (function(module, exports) {

module.exports = "<!-- Navigation -->\n<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\" id=\"mainNav\">\n  <img [src]=\"'/assets/img/walmartspark.png'\" width=\"50\" height=\"50\">\n  \n  <a class=\"navbar-brand\" href=\"#\">GWFM Interfaces Support</a> &nbsp; &nbsp;\n  <div class=\"d-inline-block\" placement=\"bottom-right\">  \n      <a class=\" navbar-toggler navbar-toggler-right btn btn-danger\" (click)=\"logout()\">\n          Logout&nbsp;&nbsp;\n          <i class=\"fa fa-user-times\"></i>\n      </a>\n  </div>\n\n  <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n    <ul class=\"navbar-nav navbar-sidenav\" id=\"exampleAccordion\">\n\n      <li *ngFor=\"let menu of menus\" class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"level1\">\n            <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" (click)=\"refreshCurrentActive('/'+menu.path)\">\n              <i [ngClass]=\"menu.icon\"></i>\n              <span class=\"nav-link-text\">\n                {{menu.name}}</span>\n            </a>\n            <ul class=\"sidenav-second-level\" [ngClass]=\"{'collapse':!menu.selected}\" id=\"collapseFirstLevel\">\n    \n              <li *ngFor=\"let menu2 of menu.children\" class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"level2\">\n                <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" (click)=\"refreshCurrentActive('/'+menu.path+'/'+menu2.path)\"\n                  [ngClass]=\"{'menu-active':menu2.selected}\">\n                  <i [ngClass]=\"menu2.icon\"></i>\n                  <span class=\"nav-link-text\">\n                      {{menu2.name}}</span>\n                </a>\n                <ul class=\"sidenav-third-level\" [ngClass]=\"{'collapse':!menu2.selected}\" id=\"collapseSecondLevel\">\n                    <li *ngFor=\"let menu3 of menu2.children\">\n                      <a class=\"nav-link\" [ngClass]=\"{'menu-active':menu3.selected}\" [routerLink]=\"'/'+menu.path+'/'+menu2.path+'/'+menu3.path\"\n                      (click)=\"refreshCurrentActive('/'+menu.path+'/'+menu2.path+'/'+menu3.path)\">\n                        <i [ngClass]=\"menu3.icon\"></i>\n                        <span class=\"nav-link-text\">\n                            {{menu3.name}}</span>\n                      </a>\n                    </li>\n                </ul>\n              </li>\n            </ul>\n      </li>\n    </ul>\n    <!--ul class=\"navbar-nav sidenav-toggler\">\n      <li class=\"nav-item\">\n        <a class=\"nav-link text-center\" id=\"sidenavToggler\">\n          <i class=\"fa fa-fw fa-angle-left\"></i>\n        </a>\n      </li>\n    </ul-->\n  \n    <ul class=\"navbar-nav ml-auto\">\n      <li class=\"nav-item\" *ngIf=\"serviceError==undefined\">\n        <a class=\"btn btn-warning\" (click)=\"logout()\">\n          {{userId}}&nbsp;&nbsp;\n          <i class=\"fa fa-user-times\"></i>\n        </a>\n      </li>\n      <li class=\"nav-item\" *ngIf=\"serviceError!=undefined\">\n        <a class=\"btn btn-warning\" (click)=\"login()\">\n          Login&nbsp;&nbsp;\n          <i class=\"fa fa-sign-in\"></i>\n        </a>\n      </li>\n    </ul>\n  </div>\n</nav>\n\n<div *ngIf=\"serviceError!=undefined\" class=\"jumbotron\">\n  <h1 class=\"display-4\">Welcome to GWFM Interfaces Support</h1>\n  \n  <hr class=\"my-4\">\n  <div class=\"row justify-content-md-center\">\n    <div class=\"col col-xs-12 col-sm-8 col-md-5\">\n  <carousel>\n    <slide>\n      <img src=\"assets/img/data.png\" alt=\"first slide\" style=\"display: block; width: 100%;\">\n      <div class=\"carousel-caption d-none d-md-block\">\n        <h3>Metrics Dashboards</h3>\n        <p>Check and review the daily metrics and the health of your applications.</p>\n      </div>\n    </slide>\n    <slide>\n      <img src=\"assets/img/engine.png\" alt=\"second slide\" style=\"display: block; width: 100%;\">\n      <div class=\"carousel-caption d-none d-md-block\">\n        <h3>All in one place</h3>\n        <p>Time Off Reques, Associate, IT 1 etc and more, all the GWFM interfaces in just one place.</p>\n      </div>\n    </slide>\n    <slide>\n      <img src=\"assets/img/userinterfaceicon.png\" alt=\"third slide\" style=\"display: block; width: 100%;\">\n      <div class=\"carousel-caption d-none d-md-block\">\n        <h3>User friendly</h3>\n        <p>You don't need to be an expert, all the environment is really easy and friendly.</p>\n      </div>\n    </slide>\n  </carousel>\n  </div>\n  </div>\n  <a class=\"btn-secondary btn-sm\" href=\"#\" role=\"button\" (click)=\"showError=!showError\">\n    <i class=\"fa fa-exclamation-triangle\"></i>\n  </a>\n  <app-error-message *ngIf=\"showError\" [service-error]=\"serviceError\"></app-error-message>\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/components/nav/nav.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NavComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_bootstrap_carousel__ = __webpack_require__("../../../../ngx-bootstrap/carousel/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_api_menu_service__ = __webpack_require__("../../../../../src/app/services/api/menu.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_api_login_service__ = __webpack_require__("../../../../../src/app/services/api/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_api_user_info_service__ = __webpack_require__("../../../../../src/app/services/api/user-info.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var NavComponent = (function () {
    function NavComponent(router, menuService, loginService, userService, sanitizer) {
        this.router = router;
        this.menuService = menuService;
        this.loginService = loginService;
        this.userService = userService;
        this.sanitizer = sanitizer;
    }
    NavComponent.prototype.ngOnInit = function () {
        var _this = this;
        console.info("nav component init");
        this.serviceError = undefined;
        var currentPath = this.router.url;
        this.menuService.getUserMenu().subscribe(function (data) {
            _this.resetMenu = JSON.stringify(data);
            _this.refreshCurrentActive(currentPath);
        }, function (error) { console.error(error); _this.serviceError = error; });
        this.userId = this.userService.getUserId();
    };
    NavComponent.prototype.logout = function () {
        this.loginService.logout();
        var logoutUrl = __WEBPACK_IMPORTED_MODULE_7__environments_environment__["a" /* environment */].ssoLogoutUrl + "?post_logout_redirect_uri=" + __WEBPACK_IMPORTED_MODULE_7__environments_environment__["a" /* environment */].hostUrl;
        window.open(logoutUrl, "_self");
    };
    NavComponent.prototype.login = function () {
        var today = new Date();
        var redirect = "&redirect_uri=" + __WEBPACK_IMPORTED_MODULE_7__environments_environment__["a" /* environment */].hostUrl;
        var nonce = "&nonce=" + today.getFullYear() + today.getMonth() + today.getDate();
        var state = "&state=" + today.getFullYear() + today.getMonth() + today.getDate();
        var loginUrl = __WEBPACK_IMPORTED_MODULE_7__environments_environment__["a" /* environment */].ssoLoginUrl + redirect + nonce + state;
        window.open(loginUrl, "_self");
    };
    NavComponent.prototype.refreshCurrentActive = function (currentPath) {
        //restart the menu
        this.menus = JSON.parse(this.resetMenu);
        var paths = currentPath.split("/");
        var selectedMenu;
        var _loop_1 = function () {
            var level = Number(index);
            switch (level) {
                case 1:
                    this_1.menus.forEach(function (menu) {
                        if (menu.path == paths[level]) {
                            menu.selected = true;
                            selectedMenu = menu;
                        }
                    });
                    break;
                case 2:
                    selectedMenu.children.forEach(function (submenu) {
                        if (submenu.path == paths[level]) {
                            submenu.selected = true;
                            selectedMenu = submenu;
                        }
                    });
                    break;
                case 3:
                    selectedMenu.children.forEach(function (submenu) {
                        if (submenu.path == paths[level]) {
                            submenu.selected = true;
                        }
                    });
                    break;
                default:
                    break;
            }
        };
        var this_1 = this;
        for (var index in paths) {
            _loop_1();
        }
    };
    NavComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-nav',
            template: __webpack_require__("../../../../../src/app/components/nav/nav.component.html"),
            styles: [__webpack_require__("../../../../../src/app/components/nav/nav.component.css")],
            providers: [
                { provide: __WEBPACK_IMPORTED_MODULE_1_ngx_bootstrap_carousel__["a" /* CarouselConfig */], useValue: { interval: 1500, noPause: true, showIndicators: true } }
            ]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */],
            __WEBPACK_IMPORTED_MODULE_4__services_api_menu_service__["a" /* MenuService */],
            __WEBPACK_IMPORTED_MODULE_5__services_api_login_service__["a" /* LoginService */],
            __WEBPACK_IMPORTED_MODULE_6__services_api_user_info_service__["a" /* UserInfoService */],
            __WEBPACK_IMPORTED_MODULE_2__angular_platform_browser__["c" /* DomSanitizer */]])
    ], NavComponent);
    return NavComponent;
}());



/***/ }),

/***/ "../../../../../src/app/directives/custom-object-pipe.pipe.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CustomObjectPipePipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__("../../../../lodash/lodash.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_lodash__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


var CustomObjectPipePipe = (function () {
    function CustomObjectPipePipe() {
    }
    CustomObjectPipePipe.prototype.transform = function (array, query) {
        if (query) {
            return __WEBPACK_IMPORTED_MODULE_1_lodash__["filter"](array, function (event) {
                var rowtext = '';
                for (var prop in event.message.customObject) {
                    rowtext += '-' + ("" + event.message.customObject[prop]);
                }
                rowtext += '-' + event.message.eventShortMsg;
                rowtext += '-' + event.message.eventLongMsg;
                return rowtext.toLocaleUpperCase().indexOf(query.toLocaleUpperCase()) > -1;
            });
        }
        return array;
    };
    CustomObjectPipePipe = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
            name: 'customObjectPipe'
        })
    ], CustomObjectPipePipe);
    return CustomObjectPipePipe;
}());



/***/ }),

/***/ "../../../../../src/app/directives/data-filter.pipe.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataFilterPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__("../../../../lodash/lodash.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_lodash__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


var DataFilterPipe = (function () {
    function DataFilterPipe() {
    }
    DataFilterPipe.prototype.transform = function (array, query) {
        if (query) {
            return __WEBPACK_IMPORTED_MODULE_1_lodash__["filter"](array, function (obj) {
                var rowtext = '';
                for (var prop in obj) {
                    rowtext += '-' + ("" + obj[prop]);
                }
                return rowtext.toLocaleUpperCase().indexOf(query.toLocaleUpperCase()) > -1;
            });
        }
        return array;
    };
    DataFilterPipe = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
            name: 'dataFilter'
        })
    ], DataFilterPipe);
    return DataFilterPipe;
}());



/***/ }),

/***/ "../../../../../src/app/directives/error-message/error-message.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/directives/error-message/error-message.component.html":
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"error!=undefined\" class=\"alert alert-danger\" role=\"alert\" style=\"width:100%;\">\n    <strong>{{error.message}}</strong>\n    <p>{{error.developerMessage}}</p>\n</div>\n<div class=\"sk-wave\" *ngIf=\"loading\">\n        <div class=\"sk-rect sk-rect1\"></div>\n        <div class=\"sk-rect sk-rect2\"></div>\n        <div class=\"sk-rect sk-rect3\"></div>\n        <div class=\"sk-rect sk-rect4\"></div>\n        <div class=\"sk-rect sk-rect5\"></div>\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/directives/error-message/error-message.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ErrorMessageComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_service_error__ = __webpack_require__("../../../../../src/app/models/service-error.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ErrorMessageComponent = (function () {
    function ErrorMessageComponent() {
        this.error = undefined;
        this.loading = false;
    }
    ErrorMessageComponent.prototype.variablesInfo = function () {
        console.info(this.error);
        console.info(this.loading);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])('service-error'),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1__models_service_error__["a" /* ServiceError */])
    ], ErrorMessageComponent.prototype, "error", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])('loading'),
        __metadata("design:type", Boolean)
    ], ErrorMessageComponent.prototype, "loading", void 0);
    ErrorMessageComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-error-message',
            template: __webpack_require__("../../../../../src/app/directives/error-message/error-message.component.html"),
            styles: [__webpack_require__("../../../../../src/app/directives/error-message/error-message.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], ErrorMessageComponent);
    return ErrorMessageComponent;
}());



/***/ }),

/***/ "../../../../../src/app/directives/paginator.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return WfmPaginator; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__wfm_table_directive__ = __webpack_require__("../../../../../src/app/directives/wfm-table.directive.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_lodash__ = __webpack_require__("../../../../lodash/lodash.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_lodash___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_lodash__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};



var WfmPaginator = (function () {
    function WfmPaginator(injectTable) {
        var _this = this;
        this.injectTable = injectTable;
        this.rowsOnPageSet = [];
        this.dataLength = 0;
        this.minRowsOnPage = 0;
        this.onPageChangeSubscriber = function (event) {
            _this.activePage = event.activePage;
            _this.rowsOnPage = Number(event.rowsOnPage);
            _this.dataLength = event.dataLength;
            _this.lastPage = Math.ceil(_this.dataLength / _this.rowsOnPage);
        };
    }
    /* ngOnChanges(changes: any): any {
        
         if(changes.)
     }*/
    WfmPaginator.prototype.ngOnChanges = function (changes) {
        this.wfmTable = this.inputTable || this.injectTable;
        console.info('WfmPaginator : ngOnChanges');
        this.onPageChangeSubscriber(this.wfmTable.getPage());
        this.wfmTable.onPageChange.subscribe(this.onPageChangeSubscriber);
        if (changes.rowsOnPageSet) {
            this.minRowsOnPage = __WEBPACK_IMPORTED_MODULE_2_lodash__["min"](this.rowsOnPageSet);
        }
    };
    WfmPaginator.prototype.setPage = function (pageNumber) {
        console.log('setting page:', pageNumber);
        this.wfmTable.setPage(pageNumber, this.rowsOnPage);
    };
    WfmPaginator.prototype.setRowsOnPage = function (rowsOnPage) {
        this.wfmTable.setPage(this.activePage, rowsOnPage);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("rowsOnPageSet"),
        __metadata("design:type", Object)
    ], WfmPaginator.prototype, "rowsOnPageSet", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("wmfTable"),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1__wfm_table_directive__["a" /* WfmTableDirective */])
    ], WfmPaginator.prototype, "inputTable", void 0);
    WfmPaginator = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: "wfmPaginator",
            template: "\n        <div class=\"clearfix\">\n           \n            <div class=\"float-left \">\n            <strong class=\"small\" for=\"pagenumber\">Page Registers.\t&nbsp;\t&nbsp;</strong>\n            </div>\n            <div class=\"float-left\">\n                <select id=\"pages float-left\" class=\"rounded-0\" (change)=\"setRowsOnPage($event.target.value)\">\n                    <option *ngFor=\"let rows of rowsOnPageSet\">{{rows}}</option>\n                </select>\n                \n            </div>\n            <div class=\"float-left\">\n            <strong class=\"small\"> &nbsp; &nbsp;{{wfmTable.inputData.length}}&nbsp; total records </strong>\n            </div>\n            <div class=\"float-right\" *ngIf=\"dataLength > rowsOnPage\">\n            <a  class=\"text-primary\"  *ngIf=\"activePage > 2\" (click)=\"setPage(1)\" >            <i class=\"fa fa-fast-backward\"></i></a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage > 1\" (click)=\"setPage(activePage - 1)\"><i class=\"fa fa-backward\"></i></a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage > 4 && activePage + 1 > lastPage\" (click)=\"setPage(activePage - 4)\">{{activePage-4}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage > 3 && activePage + 2 > lastPage\" (click)=\"setPage(activePage - 3)\">{{activePage-3}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage > 2\" (click)=\"setPage(activePage - 2)\">{{activePage-2}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage > 1\" (click)=\"setPage(activePage - 1)\">{{activePage-1}}</a>\n            <a  (click)=\"setPage(activePage)\">    {{activePage}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage + 1 < lastPage\" (click)=\"setPage(activePage+1)\">  {{activePage+1}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage + 2 < lastPage\" (click)=\"setPage(activePage+2)\">  {{activePage+2}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage + 3 < lastPage && activePage < 3\" (click)=\"setPage(activePage+3)\">  {{activePage+3}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage + 4 < lastPage && activePage < 2\" (click)=\"setPage(activePage+4)\">  {{activePage+4}}</a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage + 1 < lastPage \" (click)=\"setPage(activePage+1)\">  <i class=\"fa fa-forward\"></i></a>\n            <a  class=\"text-primary\"  *ngIf=\"activePage < lastPage\" (click)=\"setPage(lastPage)\">      <i class=\"fa fa-fast-forward\"></i></a>\n            </div>\n            \n        </div>\n    "
        }),
        __param(0, Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Optional"])()),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__wfm_table_directive__["a" /* WfmTableDirective */]])
    ], WfmPaginator);
    return WfmPaginator;
}());



/***/ }),

/***/ "../../../../../src/app/directives/utc-date.pipe.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UtcDatePipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var UtcDatePipe = (function () {
    function UtcDatePipe() {
    }
    UtcDatePipe.prototype.transform = function (millisecons) {
        if (millisecons == null || millisecons == undefined)
            return null;
        var dateValue = new Date(millisecons);
        var dateWithNoTimezone = new Date(dateValue.getUTCFullYear(), dateValue.getUTCMonth(), dateValue.getUTCDate(), dateValue.getUTCHours(), dateValue.getUTCMinutes(), dateValue.getUTCSeconds());
        return dateWithNoTimezone;
    };
    UtcDatePipe = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
            name: 'utcDate'
        })
    ], UtcDatePipe);
    return UtcDatePipe;
}());



/***/ }),

/***/ "../../../../../src/app/directives/wfm-table.directive.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return WfmTableDirective; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash__ = __webpack_require__("../../../../lodash/lodash.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_lodash___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_lodash__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__ = __webpack_require__("../../../../rxjs/_esm5/Rx.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var WfmTableDirective = (function () {
    function WfmTableDirective(differs) {
        this.differs = differs;
        this.inputData = [];
        this.sortBy = "";
        this.sortOrder = "asc";
        this.sortByChange = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.sortOrderChange = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.rowsOnPage = 10;
        this.activePage = 1;
        this.mustRecalculateData = false;
        this.onSortChange = new __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["a" /* ReplaySubject */](1);
        this.onPageChange = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.diff = differs.find([]).create(null);
    }
    WfmTableDirective.prototype.getSort = function () {
        return { sortBy: this.sortBy, sortOrder: this.sortOrder };
    };
    WfmTableDirective.prototype.setSort = function (sortBy, sortOrder) {
        if (this.sortBy !== sortBy || this.sortOrder !== sortOrder) {
            this.sortBy = sortBy;
            this.sortOrder = __WEBPACK_IMPORTED_MODULE_1_lodash__["includes"](["asc", "desc"], sortOrder) ? sortOrder : "asc";
            this.mustRecalculateData = true;
            this.onSortChange.next({ sortBy: sortBy, sortOrder: sortOrder });
            this.sortByChange.emit(this.sortBy);
            this.sortOrderChange.emit(this.sortOrder);
        }
    };
    WfmTableDirective.prototype.getPage = function () {
        return { activePage: this.activePage, rowsOnPage: this.rowsOnPage, dataLength: this.inputData.length };
    };
    WfmTableDirective.prototype.setPage = function (activePage, rowsOnPage) {
        if (this.rowsOnPage !== rowsOnPage || this.activePage !== activePage) {
            this.activePage = this.activePage !== activePage ? activePage : this.calculateNewActivePage(this.rowsOnPage, rowsOnPage);
            this.rowsOnPage = rowsOnPage;
            this.mustRecalculateData = true;
            this.onPageChange.emit({
                activePage: this.activePage,
                rowsOnPage: this.rowsOnPage,
                dataLength: this.inputData ? this.inputData.length : 0
            });
        }
    };
    WfmTableDirective.prototype.calculateNewActivePage = function (previousRowsOnPage, currentRowsOnPage) {
        var firstRowOnPage = (this.activePage - 1) * previousRowsOnPage + 1;
        var newActivePage = Math.ceil(firstRowOnPage / currentRowsOnPage);
        return newActivePage;
    };
    WfmTableDirective.prototype.recalculatePage = function () {
        var lastPage = Math.ceil(this.inputData.length / this.rowsOnPage);
        this.activePage = lastPage < this.activePage ? lastPage : this.activePage;
        this.activePage = this.activePage || 1;
        this.onPageChange.emit({
            activePage: this.activePage,
            rowsOnPage: this.rowsOnPage,
            dataLength: this.inputData.length
        });
    };
    WfmTableDirective.prototype.ngOnChanges = function (changes) {
        if (changes["rowsOnPage"]) {
            this.rowsOnPage = changes["rowsOnPage"].previousValue;
            this.setPage(this.activePage, changes["rowsOnPage"].currentValue);
            this.mustRecalculateData = true;
        }
        if (changes["sortBy"] || changes["sortOrder"]) {
            if (!__WEBPACK_IMPORTED_MODULE_1_lodash__["includes"](["asc", "desc"], this.sortOrder)) {
                console.warn("angular2-datatable: value for input mfSortOrder must be one of ['asc', 'desc'], but is:", this.sortOrder);
                this.sortOrder = "asc";
            }
            if (this.sortBy) {
                this.onSortChange.next({ sortBy: this.sortBy, sortOrder: this.sortOrder });
            }
            this.mustRecalculateData = true;
        }
        if (changes["inputData"]) {
            this.inputData = changes["inputData"].currentValue || [];
            this.recalculatePage();
            this.mustRecalculateData = true;
        }
    };
    WfmTableDirective.prototype.ngDoCheck = function () {
        var changes = this.diff.diff(this.inputData);
        if (changes) {
            this.recalculatePage();
            this.mustRecalculateData = true;
        }
        if (this.mustRecalculateData) {
            this.fillData();
            this.mustRecalculateData = false;
        }
    };
    WfmTableDirective.prototype.fillData = function () {
        this.activePage = this.activePage;
        this.rowsOnPage = this.rowsOnPage;
        var offset = (this.activePage - 1) * this.rowsOnPage;
        var data = this.inputData;
        var sortBy = this.sortBy;
        if (typeof sortBy === 'string' || sortBy instanceof String) {
            data = __WEBPACK_IMPORTED_MODULE_1_lodash__["orderBy"](data, this.caseInsensitiveIteratee(sortBy), [this.sortOrder]);
        }
        else {
            data = __WEBPACK_IMPORTED_MODULE_1_lodash__["orderBy"](data, sortBy, [this.sortOrder]);
        }
        data = __WEBPACK_IMPORTED_MODULE_1_lodash__["slice"](data, offset, offset + this.rowsOnPage);
        this.data = data;
    };
    WfmTableDirective.prototype.caseInsensitiveIteratee = function (sortBy) {
        return function (row) {
            var value = row;
            for (var _i = 0, _a = sortBy.split('.'); _i < _a.length; _i++) {
                var sortByProperty = _a[_i];
                if (value) {
                    value = value[sortByProperty];
                }
            }
            if (value && typeof value === 'string' || value instanceof String) {
                return value.toLowerCase();
            }
            return value;
        };
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("wfmData"),
        __metadata("design:type", Array)
    ], WfmTableDirective.prototype, "inputData", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("wfmSortBy"),
        __metadata("design:type", Object)
    ], WfmTableDirective.prototype, "sortBy", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("wfmSortOrder"),
        __metadata("design:type", Object)
    ], WfmTableDirective.prototype, "sortOrder", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])("wfmSortByChange"),
        __metadata("design:type", Object)
    ], WfmTableDirective.prototype, "sortByChange", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])("wfmSortOrderChange"),
        __metadata("design:type", Object)
    ], WfmTableDirective.prototype, "sortOrderChange", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("wfmRowsOnPage"),
        __metadata("design:type", Object)
    ], WfmTableDirective.prototype, "rowsOnPage", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])("wfmActivePage"),
        __metadata("design:type", Object)
    ], WfmTableDirective.prototype, "activePage", void 0);
    WfmTableDirective = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Directive"])({
            selector: 'table[wfmData]',
            exportAs: 'wfmDataTable'
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["IterableDiffers"]])
    ], WfmTableDirective);
    return WfmTableDirective;
}());



/***/ }),

/***/ "../../../../../src/app/models/associate-reprocess-builder.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateReprocessBuilder; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__associate_reprocess_dto__ = __webpack_require__("../../../../../src/app/models/associate-reprocess-dto.ts");

var AssociateReprocessBuilder = (function () {
    function AssociateReprocessBuilder() {
        this._reprocessDto = new __WEBPACK_IMPORTED_MODULE_0__associate_reprocess_dto__["a" /* AssociateReprocessDto */]();
    }
    AssociateReprocessBuilder.prototype.setRpInstance = function (rpInstance) {
        this._reprocessDto.rpInstance = rpInstance;
        return this;
    };
    AssociateReprocessBuilder.prototype.setLogId = function (logId) {
        this._reprocessDto.logId = logId;
        return this;
    };
    AssociateReprocessBuilder.prototype.setWin = function (win) {
        this._reprocessDto.win = win;
        return this;
    };
    AssociateReprocessBuilder.prototype.setEvent = function (event) {
        this._reprocessDto.event = event;
        return this;
    };
    AssociateReprocessBuilder.prototype.build = function () {
        return this._reprocessDto;
    };
    return AssociateReprocessBuilder;
}());



/***/ }),

/***/ "../../../../../src/app/models/associate-reprocess-dto.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateReprocessDto; });
var AssociateReprocessDto = (function () {
    function AssociateReprocessDto() {
    }
    return AssociateReprocessDto;
}());



/***/ }),

/***/ "../../../../../src/app/models/associate-rp-instance-dto.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateRpInstanceDto; });
var AssociateRpInstanceDto = (function () {
    function AssociateRpInstanceDto() {
    }
    return AssociateRpInstanceDto;
}());



/***/ }),

/***/ "../../../../../src/app/models/associate-store-dto.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateStoreDto; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__associate_rp_instance_dto__ = __webpack_require__("../../../../../src/app/models/associate-rp-instance-dto.ts");

var AssociateStoreDto = (function () {
    function AssociateStoreDto() {
        this.rpInstance = new __WEBPACK_IMPORTED_MODULE_0__associate_rp_instance_dto__["a" /* AssociateRpInstanceDto */]();
    }
    return AssociateStoreDto;
}());



/***/ }),

/***/ "../../../../../src/app/models/dashboard-enum.enum.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardEnum; });
var DashboardEnum;
(function (DashboardEnum) {
    DashboardEnum[DashboardEnum["events"] = 1] = "events";
    DashboardEnum[DashboardEnum["topology"] = 2] = "topology";
    DashboardEnum[DashboardEnum["stores"] = 3] = "stores";
    DashboardEnum[DashboardEnum["elastic"] = 4] = "elastic";
})(DashboardEnum || (DashboardEnum = {}));


/***/ }),

/***/ "../../../../../src/app/models/maintenance-builder.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export MaintenanceDay */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MaintenanceDayBuilder; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_date_fns__ = __webpack_require__("../../../../date-fns/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_date_fns___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_date_fns__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__maintenance__ = __webpack_require__("../../../../../src/app/models/maintenance.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__week_days_enum__ = __webpack_require__("../../../../../src/app/models/week-days.enum.ts");

//models


var MaintenanceDay = (function () {
    function MaintenanceDay() {
    }
    return MaintenanceDay;
}());

var MaintenanceDayBuilder = (function () {
    function MaintenanceDayBuilder() {
        this._maintenanceDay = new MaintenanceDay();
    }
    MaintenanceDayBuilder.prototype.setStartDate = function (dayOfWeek, startHr, event) {
        var today = new Date();
        this._maintenanceDay.startDate = Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["addDays"])(today, this.getDiferenceDays(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(today, 'dddd'), dayOfWeek));
        var starttimeTemp = new String(startHr).split(".", 3);
        if (starttimeTemp.length >= 2) {
            if (event == 0) {
                starttimeTemp[1] = this.timeToOskiTLogic(starttimeTemp[1]);
            }
            if (starttimeTemp[1].length == 1) {
                starttimeTemp[1] = starttimeTemp[1] + "0";
            }
        }
        this._maintenanceDay.startDate.setHours(Number(starttimeTemp[0]), (starttimeTemp[1] == null || starttimeTemp[1] == undefined) ? 0 : Number((starttimeTemp[1].length == 1) ? starttimeTemp[1] + "0" : starttimeTemp[1]));
    };
    MaintenanceDayBuilder.prototype.setEndDate = function (dayOfWeek, endHr, event) {
        var today = new Date();
        this._maintenanceDay.endDate = Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["addDays"])(today, this.getDiferenceDays(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(today, 'dddd'), dayOfWeek));
        var endtimeTemp = new String(endHr).split(".", 3);
        if (endtimeTemp.length >= 2) {
            if (event == 0) {
                endtimeTemp[1] = this.timeToOskiTLogic(endtimeTemp[1]);
            }
            if (endtimeTemp[1].length == 1) {
                endtimeTemp[1] = endtimeTemp[1] + "0";
            }
        }
        this._maintenanceDay.endDate.setHours(Number(endtimeTemp[0]), (endtimeTemp[1] == null || endtimeTemp[1] == undefined) ? 0 : Number((endtimeTemp[1].length == 1) ? endtimeTemp[1] + "0" : endtimeTemp[1]));
    };
    MaintenanceDayBuilder.prototype.setDayOfWeek = function (dayOfWeek) {
        this._maintenanceDay.dayOfWeek = dayOfWeek;
    };
    MaintenanceDayBuilder.prototype.setMarket = function (market) {
        this._maintenanceDay.market = market;
    };
    MaintenanceDayBuilder.prototype.setEvent = function (a) {
        var end = this.logicDateTodoubleDate(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(this._maintenanceDay.endDate, 'HH.mm'));
        var start = this.logicDateTodoubleDate(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(this._maintenanceDay.startDate, 'HH.mm'));
        this._maintenanceDay.event = {
            dayOfWeek: this._maintenanceDay.dayOfWeek,
            endHr: end,
            startHr: start,
            market: this._maintenanceDay.market,
            title: "[" + this.doubleToStringDate(start) + "-" + this.doubleToStringDate(end) + "]",
            start: this._maintenanceDay.startDate,
            end: this._maintenanceDay.endDate,
            color: MaintenanceDayBuilder.colors.red,
            draggable: true,
            actions: a,
            resizable: {
                beforeStart: true,
                afterEnd: true
            }
        };
    };
    MaintenanceDayBuilder.prototype.setMaintenance = function () {
        var initDate = this.doubleDateToLogicDate(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(this._maintenanceDay.startDate, 'HH.mm'));
        var finalDate = this.doubleDateToLogicDate(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(this._maintenanceDay.endDate, 'HH.mm'));
        this._maintenanceDay.maintenance = new __WEBPACK_IMPORTED_MODULE_1__maintenance__["a" /* Maintenance */](initDate, (finalDate == 0) ? 24 : finalDate, this._maintenanceDay.dayOfWeek, this._maintenanceDay.market);
    };
    MaintenanceDayBuilder.prototype.initDateUtcToLocal = function () {
        var startDateLocal = this.convertUTCDateToLocalDate(this._maintenanceDay.startDate);
        if (Number(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(startDateLocal, 'd')) == 6 && Number(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(this._maintenanceDay.startDate, 'd')) == 0) {
            startDateLocal = Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["addDays"])(startDateLocal, 7);
        }
        this._maintenanceDay.startDate = startDateLocal;
    };
    MaintenanceDayBuilder.prototype.endDateUtcToLocal = function () {
        var endDateLocal = this.convertUTCDateToLocalDate(this._maintenanceDay.endDate);
        if (Number(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(endDateLocal, 'd')) == 6 && Number(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(this._maintenanceDay.endDate, 'd')) == 0) {
            endDateLocal = Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["addDays"])(endDateLocal, 7);
        }
        this._maintenanceDay.endDate = endDateLocal;
    };
    MaintenanceDayBuilder.prototype.initDateLocalToUtc = function () {
        this._maintenanceDay.startDate = this.convertLocalDateToUTCDate(this._maintenanceDay.startDate);
    };
    MaintenanceDayBuilder.prototype.endDateLocalToUtc = function () {
        this._maintenanceDay.endDate = this.convertLocalDateToUTCDate(this._maintenanceDay.endDate);
    };
    MaintenanceDayBuilder.prototype.getResult = function () {
        return this._maintenanceDay;
    };
    MaintenanceDayBuilder.prototype.doubleDateToLogicDate = function (date) {
        var stringDate = date.split(".", 3);
        var hour;
        var minutes = 0;
        if (stringDate.length > 0) {
            hour = Number(stringDate[0]);
            if (stringDate.length > 1) {
                minutes = Math.round(Number(stringDate[1]) * 100 / 60);
            }
        }
        var minuteString = (minutes < 10) ? "0" + minutes : new String(minutes);
        return Number(hour + "." + minuteString);
    };
    MaintenanceDayBuilder.prototype.doubleToStringDate = function (date) {
        var starttimeTemp = new String(date).split(".", 3);
        var stringDate = starttimeTemp[0] + ":" + ((starttimeTemp[1] == null || starttimeTemp[1] == undefined) ? "00" : starttimeTemp[1]);
        return stringDate;
    };
    MaintenanceDayBuilder.prototype.timeToOskiTLogic = function (numb) {
        var result = Math.round(Number(numb) * 60 / 100);
        return result + "";
    };
    MaintenanceDayBuilder.prototype.logicDateTodoubleDate = function (date) {
        var stringDate = date.split(".", 3);
        var hour;
        var minutes = 0;
        if (stringDate.length > 0) {
            hour = Number(stringDate[0]);
            if (stringDate.length > 1) {
                //minutes = Math.round(Number(stringDate[1]) * 60 / 100);
                minutes = Number(stringDate[1]);
            }
        }
        var minuteString = (minutes < 10) ? "0" + minutes : new String(minutes);
        return Number(hour + "." + minuteString);
    };
    MaintenanceDayBuilder.prototype.getDiferenceDays = function (today, day) {
        return __WEBPACK_IMPORTED_MODULE_2__week_days_enum__["a" /* WeekDays */][day] - __WEBPACK_IMPORTED_MODULE_2__week_days_enum__["a" /* WeekDays */][today];
    };
    //utc date to local date
    MaintenanceDayBuilder.prototype.convertUTCDateToLocalDate = function (date) {
        var newDate = new Date(date.getTime() - date.getTimezoneOffset() * 60 * 1000);
        return newDate;
    };
    MaintenanceDayBuilder.prototype.modifyStartTime = function (start) {
        this._maintenanceDay.maintenance.startHr = start;
    };
    MaintenanceDayBuilder.prototype.modifyEndTime = function (end) {
        this._maintenanceDay.maintenance.endHr = end;
    };
    MaintenanceDayBuilder.prototype.duplicateBuilder = function (builder) {
        var tempMaintenanceDayBuilder = new MaintenanceDayBuilder();
        tempMaintenanceDayBuilder._maintenanceDay.maintenance = new __WEBPACK_IMPORTED_MODULE_1__maintenance__["a" /* Maintenance */](builder._maintenanceDay.maintenance.startHr, builder._maintenanceDay.maintenance.endHr, builder._maintenanceDay.maintenance.dayOfWeek, builder._maintenanceDay.maintenance.market);
        return tempMaintenanceDayBuilder;
    };
    MaintenanceDayBuilder.prototype.addWeekDay = function () {
        var dayWeek = __WEBPACK_IMPORTED_MODULE_2__week_days_enum__["a" /* WeekDays */][this._maintenanceDay.maintenance.dayOfWeek];
        this._maintenanceDay.maintenance.dayOfWeek = __WEBPACK_IMPORTED_MODULE_2__week_days_enum__["a" /* WeekDays */][(dayWeek == 7) ? 1 : dayWeek + 1];
    };
    MaintenanceDayBuilder.prototype.componentToDate = function (dayWeek, date) {
        return this.numberToDate(dayWeek, Number(date.hour + "." + date.minute));
    };
    MaintenanceDayBuilder.prototype.numberToDate = function (dayOfWeek, startHr) {
        var today = new Date();
        var tempDate = Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["addDays"])(today, this.getDiferenceDays(Object(__WEBPACK_IMPORTED_MODULE_0_date_fns__["format"])(today, 'dddd'), dayOfWeek));
        var starttimeTemp = new String(startHr).split(".", 3);
        tempDate.setHours(Number(starttimeTemp[0]), (starttimeTemp[1] == null || starttimeTemp[1] == undefined) ? 0 : Number(starttimeTemp[1]));
        return tempDate;
    };
    //local date to utc date
    MaintenanceDayBuilder.prototype.convertLocalDateToUTCDate = function (date) {
        var dateWithNoTimezone = new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
        return dateWithNoTimezone;
    };
    //maintenance to  MaintenanceDay
    MaintenanceDayBuilder.prototype.buildMaintenanceDayView = function (maintenance, actions) {
        this.setMarket(maintenance.market);
        this.setStartDate(maintenance.dayOfWeek, maintenance.startHr, 0);
        this.setEndDate(maintenance.dayOfWeek, maintenance.endHr, 0);
        this.setDayOfWeek(maintenance.dayOfWeek);
        this.initDateUtcToLocal();
        this.endDateUtcToLocal();
        this.setEvent(actions);
        return this.getResult();
    };
    MaintenanceDayBuilder.prototype.buildMaintenanceDayDelete = function (maintenance) {
        this.setMarket(maintenance.market);
        this.setStartDate(maintenance.dayOfWeek, maintenance.startHr, 1);
        this.setEndDate(maintenance.dayOfWeek, maintenance.endHr, 1);
        this.setDayOfWeek(maintenance.dayOfWeek);
        this.initDateLocalToUtc();
        this.endDateLocalToUtc();
        this.setMaintenance();
        return this.getResult();
    };
    MaintenanceDayBuilder.prototype.buildMaintenanceDayNew = function (initDate, endDate, market, weekDay) {
        var maintenanceDay = [];
        var maintenanceBuilder = new MaintenanceDayBuilder();
        maintenanceBuilder.setMarket(market);
        maintenanceBuilder.setStartDate(weekDay, Number(initDate.getHours() + "." + initDate.getMinutes()), 1);
        maintenanceBuilder.setEndDate(weekDay, Number(endDate.getHours() + "." + endDate.getMinutes()), 1);
        maintenanceBuilder._maintenanceDay.startDateInit = maintenanceBuilder._maintenanceDay.startDate.getHours();
        maintenanceBuilder._maintenanceDay.endDateInit = maintenanceBuilder._maintenanceDay.endDate.getHours();
        if (maintenanceBuilder._maintenanceDay.startDate.getHours() <= maintenanceBuilder._maintenanceDay.endDate.getHours()) {
            if (maintenanceBuilder._maintenanceDay.startDate.getHours() == maintenanceBuilder._maintenanceDay.endDate.getHours() && maintenanceBuilder._maintenanceDay.startDate.getMinutes() >= maintenanceBuilder._maintenanceDay.endDate.getMinutes()) {
            }
            else {
                console.log("first validation");
                maintenanceBuilder.setDayOfWeek(weekDay);
                maintenanceBuilder.initDateLocalToUtc();
                maintenanceBuilder.endDateLocalToUtc();
                maintenanceBuilder.setMaintenance();
                if (maintenanceBuilder._maintenanceDay.startDate.getHours() > maintenanceBuilder._maintenanceDay.endDate.getHours()) {
                    //split save 
                    console.log("split save");
                    var temporalMaintenance = this.duplicateBuilder(maintenanceBuilder);
                    maintenanceBuilder.modifyEndTime(24.0);
                    temporalMaintenance.modifyStartTime(0.0);
                    temporalMaintenance.addWeekDay();
                    maintenanceDay.push(maintenanceBuilder._maintenanceDay.maintenance);
                    maintenanceDay.push(temporalMaintenance._maintenanceDay.maintenance);
                }
                else {
                    if (this.componentToDate(weekDay, initDate).getHours() >= maintenanceBuilder._maintenanceDay.startDate.getHours()) {
                        //regular save , nex day
                        console.log("regular save next day");
                        maintenanceBuilder.addWeekDay();
                        maintenanceDay.push(maintenanceBuilder._maintenanceDay.maintenance);
                    }
                    else {
                        //regular save 
                        if (maintenanceBuilder._maintenanceDay.startDate.getHours() < maintenanceBuilder._maintenanceDay.startDateInit) {
                            maintenanceBuilder.addWeekDay();
                        }
                        maintenanceDay.push(maintenanceBuilder._maintenanceDay.maintenance);
                    }
                }
            }
        }
        return maintenanceDay;
    };
    MaintenanceDayBuilder.dateFormatElastic = 'YYYY-MM-DDTHH:mm:ss';
    MaintenanceDayBuilder.colors = {
        red: {
            primary: '#ad2121',
            secondary: '#FAE3E3'
        },
        blue: {
            primary: '#1e90ff',
            secondary: '#D1E8FF'
        },
        yellow: {
            primary: '#e3bc08',
            secondary: '#FDF1BA'
        }
    };
    return MaintenanceDayBuilder;
}());



/***/ }),

/***/ "../../../../../src/app/models/maintenance.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Maintenance; });
var Maintenance = (function () {
    function Maintenance(startHr, endHr, dayOfWeek, market) {
        this.startHr = startHr;
        this.endHr = endHr;
        this.dayOfWeek = dayOfWeek;
        this.market = market;
    }
    return Maintenance;
}());



/***/ }),

/***/ "../../../../../src/app/models/service-error.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ServiceError; });
var ServiceError = (function () {
    function ServiceError(message, devMessage) {
        this.message = message;
        this.developerMessage = devMessage;
    }
    return ServiceError;
}());



/***/ }),

/***/ "../../../../../src/app/models/tor-store.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TorStore; });
var TorStore = (function () {
    function TorStore() {
    }
    return TorStore;
}());



/***/ }),

/***/ "../../../../../src/app/models/week-days.enum.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return WeekDays; });
var WeekDays;
(function (WeekDays) {
    WeekDays[WeekDays["Sunday"] = 1] = "Sunday";
    WeekDays[WeekDays["Monday"] = 2] = "Monday";
    WeekDays[WeekDays["Tuesday"] = 3] = "Tuesday";
    WeekDays[WeekDays["Wednesday"] = 4] = "Wednesday";
    WeekDays[WeekDays["Thursday"] = 5] = "Thursday";
    WeekDays[WeekDays["Friday"] = 6] = "Friday";
    WeekDays[WeekDays["Saturday"] = 7] = "Saturday";
})(WeekDays || (WeekDays = {}));


/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"content-wrapper\">\n  <div class=\"container-fluid\">\n    <br/>\n    \n    <div class=\"swagger-container\">\n    </div>\n\n  </div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateEndpointsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__polyfills__ = __webpack_require__("../../../../../src/polyfills.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_associate_associate_enpoints__ = __webpack_require__("../../../../../src/app/services/associate/associate-enpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AssociateEndpointsComponent = (function () {
    function AssociateEndpointsComponent(el, endpoints) {
        this.el = el;
        this.endpoints = endpoints;
    }
    AssociateEndpointsComponent.prototype.ngOnInit = function () {
    };
    AssociateEndpointsComponent.prototype.ngAfterViewInit = function () {
        var ui = Object(__WEBPACK_IMPORTED_MODULE_1__polyfills__["swaggerUIBundle"])({
            urls: [{
                    validatorUrl: null,
                    url: this.endpoints.getSwaggerJsonEndpoint(),
                    name: 'associate-interface'
                }],
            domNode: this.el.nativeElement.querySelector('.swagger-container'),
            deepLinking: true,
            presets: [
                __WEBPACK_IMPORTED_MODULE_1__polyfills__["swaggerUIBundle"].presets.apis,
                __WEBPACK_IMPORTED_MODULE_1__polyfills__["swaggerUIStandalonePreset"]
            ],
            plugins: [
                __WEBPACK_IMPORTED_MODULE_1__polyfills__["swaggerUIBundle"].plugins.DownloadUrl
            ],
            layout: 'StandaloneLayout'
        });
    };
    AssociateEndpointsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-associate-endpoints',
            template: __webpack_require__("../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/associate/associate-endpoints/associate-endpoints.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"],
            __WEBPACK_IMPORTED_MODULE_2__services_associate_associate_enpoints__["a" /* AssociateEnpoints */]])
    ], AssociateEndpointsComponent);
    return AssociateEndpointsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"content-wrapper\">\n  <div class=\"container-fluid\">\n    <br/>\n    <br/>\n    <h3> Reprocess Associates</h3>\n    <br/>\n    <br/>\n    <div class=\"row justify-content-center\">\n      <div class=\"col-md-12\">\n        <div class=\"card mb-3\">\n          <div class=\"card-header text-center\">\n            <i class=\"fa fa-search-plus\"></i>\n            <strong>Associate reprocess search</strong>\n          </div>\n          <div class=\"card-body\">\n            <form #torForm=\"ngForm\">\n              <div class=\"form-row\">\n                <div class=\"form-group col-md-4 col-sm-12\">\n                  <label for=\"gtaid\" class=\"col-form-label\">Elastic ID</label>\n                  <input type=\"string\" class=\"form-control\" [(ngModel)]=\"id\" name=\"elasticId\"\n                    id=\"elasticId\">\n                </div>\n                <div class=\"form-group col-md-4 col-sm-6\">\n                  <label for=\"start\" class=\"col-form-label\">Start Date</label>\n                  <div class=\"input-group\">\n                    <input type=\"text\" class=\"form-control\" #dp1=\"bsDatepicker\" bsDatepicker [(bsValue)]=\"startDate\" [disabled]=\"id.length>0\">\n                    <button class=\"input-group-addon\" [disabled]=\"id.length>0\" (click)=\"dp1.show()\" type=\"button\">\n                      <i class=\"fa fa-calendar\"></i>\n                    </button>\n                  </div>\n                </div>\n                <div class=\"form-group col-md-4 col-sm-6\">\n                  <label for=\"end\" class=\"col-form-label\">End Date</label>\n                  <div class=\"input-group\">\n                      <input type=\"text\" class=\"form-control\" #dp2=\"bsDatepicker\" bsDatepicker [minDate]=\"startDate\"  [(bsValue)]=\"endDate\" [disabled]=\"id.length>0\">\n                      <button class=\"input-group-addon\" [disabled]=\"id.length>0\" (click)=\"dp2.show()\" type=\"button\">\n                        <i class=\"fa fa-calendar\"></i>\n                      </button>\n                  </div>\n                </div>\n              </div>\n\n              <div class=\"row justify-content-center\">\n                <button type=\"submit\" class=\"btn btn-success\" [disabled]=\"(startDate==null||endDate==null)&&id.length==0\" (click)=\"searchEvents()\">Search Error's</button>\n              </div>\n            </form>\n          </div>\n        </div>\n\n      </div>\n    </div>\n    <br/>\n    <br/>\n\n    <app-error-message [service-error]=\"serviceError\" [loading]=\"loadingRequest\"></app-error-message>\n\n    <div class=\"row\" *ngIf=\"this.events.length>0 && !loadingRequest\">\n      <div class=\"col-md-12\">\n        <div class=\"card mb-3\">\n          <div class=\"card-header\">\n            <i class=\"fa fa-bell\"></i>\n            <strong>Associate errors</strong>\n          </div>\n          <div class=\"card-body\">\n            <div class=\"row\">\n              <div class=\"clearfix col-sm-12\">\n                <div class=\"input-group float-right\" *ngIf=\"events.length>0\" style=\"width: 250px; height: 31px;\">\n                  <input type=\"text\" class=\"form-control\" placeholder=\"Search events...\" aria-label=\"Events search\" aria-describedby=\"basic-addon2\"\n                    [(ngModel)]=\"filterQuery\">\n                  <span class=\"input-group-addon\" id=\"basic-addon2\">\n                    <i class=\"fa fa-search\"></i>\n                  </span>\n                </div>\n\n              </div>\n            </div>\n            <div class=\"row\" *ngIf=\"!loadingRequest\">\n              <div class=\"col-sm-12 table-responsive\">\n\n                <table width=\"100%\" class=\"table table-striped table-hover\" id=\"dataTables-example\" [wfmData]=\"events | customObjectPipe : filterQuery\"\n                  #wfm=\"wfmDataTable\" [wfmRowsOnPage]=\"rowsOnPage\" [(wfmSortBy)]=\"sortBy\" [(wfmSortOrder)]=\"sortOrder\">\n                  <thead class=\"thead-dark\">\n                    <tr>\n                      \n                      <th style=\"width:50px;\">\n                        <a (click)=\"selectAllEvents()\" *ngIf=\"events.length>1\">\n                            <i *ngIf=\"check==true\" class=\"fa fa-check-square-o \" aria-hidden=\"true\"></i>\n                            <i *ngIf=\"check==false\" class=\"fa fa-square-o \" aria-hidden=\"true\"></i>\n                        </a>\n                      </th>\n                      <th class=\"small\" style=\"width:50px;\"></th>\n                      <th class=\"small\">RP_INSTANCE</th>\n                      <th class=\"small\">WIN</th>\n                      <th class=\"small\">LOG ID</th>\n                      <th class=\"small\">STATUS</th>\n                      <th class=\"small\">TYPE CODE</th>\n                      <th class=\"small\">PAY TYPE</th>\n                      <th class=\"small\">GEO CODE</th>\n                      <th class=\"small\">STORE</th>\n                      <th class=\"small\">DIV</th>\n                      <th class=\"small\">DEPT</th>\n                      <th class=\"small\">JOB</th>\n                      <th class=\"small\">ERROR</th>\n                    </tr>\n                  </thead>\n                  <tbody>\n                    <tr *ngFor=\"let item of wfm.data; let i = index\">\n                      <td>\n                        <input type=\"checkbox\" [(ngModel)]=\"item.check\" *ngIf=\"events.length>1\">\n                      </td>\n                      <td>\n                        <button type=\"button\" class=\"btn btn-primary btn-sm\" (click)=\"reprocessEvent(item)\" [disabled]=\"loadingRequest==true\">\n                          <i class=\"fa fa-reply-all\" aria-hidden=\"true\"></i>\n                        </button>\n                      </td>\n                      <td class=\"small\">{{item.message.customObject.rpInstance}}</td>\n                      <td class=\"small\">{{item.message.customObject.walmartIdentificationNumber}}</td>\n                      <td class=\"small\">{{item.message.customObject.associateLogId}}</td>\n                      <td class=\"small\">{{item.message.customObject.statusCode}}</td>\n                      <td class=\"small\">{{item.message.customObject.associateTypeCode}}</td>\n                      <td class=\"small\">{{item.message.customObject.payTypeCode}}</td>\n                      <td class=\"small\">{{item.message.customObject.associateGeographicCategoryCode}}</td>\n                      <td class=\"small\">{{item.message.customObject.storeNumber}}</td>\n                      <td class=\"small\">{{item.message.customObject.divNumber}}</td>\n                      <td class=\"small\">{{item.message.customObject.departmentNumber}}</td>\n                      <td class=\"small\">{{item.message.customObject.jobCode}}</td>\n                      <td class=\"small\">{{item.message.eventShortMsg}}</td>\n                    </tr>\n                  </tbody>\n                  <tfoot>\n                    <tr>\n                      <td colspan=\"14\">\n                        <wfmPaginator [rowsOnPageSet]=\"[5,10,15]\"></wfmPaginator>\n                      </td>\n                    </tr>\n                  </tfoot>\n                </table>\n                <div class=\"row justify-content-center\" *ngIf=\"events.length>1\">\n                    <button type=\"button  btn-danger\" class=\"btn btn-primary btn-danger\" (click)=\"reprocessEvents()\" [disabled]=\"loadingRequest==true\">\n                      Reprocess Selected\n                    </button>\n                  </div>\n              </div>\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateReprocessComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_associate_associate_reprocess_service__ = __webpack_require__("../../../../../src/app/services/associate/associate-reprocess.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_service_error__ = __webpack_require__("../../../../../src/app/models/service-error.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_associate_reprocess_builder__ = __webpack_require__("../../../../../src/app/models/associate-reprocess-builder.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__directives_custom_object_pipe_pipe__ = __webpack_require__("../../../../../src/app/directives/custom-object-pipe.pipe.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var AssociateReprocessComponent = (function () {
    function AssociateReprocessComponent(reprocessService) {
        this.reprocessService = reprocessService;
        this.id = "";
        this.filterQuery = "";
        this.rowsOnPage = 5;
        this.sortBy = "item.message.customObject.walmartIdentificationNumber";
        this.sortOrder = "asc";
        this.customObjectPipePipe = new __WEBPACK_IMPORTED_MODULE_4__directives_custom_object_pipe_pipe__["a" /* CustomObjectPipePipe */]();
    }
    AssociateReprocessComponent.prototype.ngOnInit = function () {
        this.events = [];
        this.serviceError = undefined;
        this.check = false;
        this.startDate = this.getToday();
        this.endDate = this.getToday();
    };
    AssociateReprocessComponent.prototype.getToday = function () {
        var date = new Date();
        return new Date(date.getFullYear(), date.getMonth(), date.getDate());
    };
    AssociateReprocessComponent.prototype.searchEvents = function () {
        var _this = this;
        this.events = [];
        this.serviceError = undefined;
        this.loadingRequest = true;
        this.reprocessService.getErrorEvents(this.id, this.startDate, this.endDate).subscribe(function (data) {
            var events = data.filter(function (event) { return event.message.customObject != null; });
            if (events.length > 0) {
                _this.events = events;
            }
            else {
                _this.events = [];
                _this.serviceError = new __WEBPACK_IMPORTED_MODULE_2__models_service_error__["a" /* ServiceError */]("Events not found", "Try with a different period of time");
            }
            _this.loadingRequest = false;
        }, function (err) {
            console.debug(err);
            _this.serviceError = err;
            _this.loadingRequest = false;
        });
    };
    AssociateReprocessComponent.prototype.reprocessEvents = function () {
        var _this = this;
        this.serviceError = undefined;
        this.loadingRequest = true;
        var dtos = [];
        this.events.forEach(function (event) {
            if (event.check) {
                var dto = new __WEBPACK_IMPORTED_MODULE_3__models_associate_reprocess_builder__["a" /* AssociateReprocessBuilder */]().setEvent(event.id)
                    .setRpInstance(event.message.customObject.rpInstance)
                    .setWin(event.message.customObject.walmartIdentificationNumber)
                    .setLogId(event.message.customObject.associateLogId).build();
                dtos.push(dto);
            }
        });
        this.reprocessService.reprocessEvents(dtos).subscribe(function (success) {
            _this.searchEvents();
        }, function (error) {
            console.debug(error);
            _this.serviceError = error;
            _this.loadingRequest = false;
        });
    };
    AssociateReprocessComponent.prototype.reprocessEvent = function (event) {
        var _this = this;
        this.serviceError = undefined;
        this.loadingRequest = true;
        var dto = new __WEBPACK_IMPORTED_MODULE_3__models_associate_reprocess_builder__["a" /* AssociateReprocessBuilder */]().setEvent(event.id)
            .setRpInstance(event.message.customObject.rpInstance)
            .setWin(event.message.customObject.walmartIdentificationNumber)
            .setLogId(event.message.customObject.associateLogId).build();
        this.reprocessService.reprocessEvent(dto).subscribe(function (success) {
            _this.searchEvents();
        }, function (error) {
            console.debug(error);
            _this.serviceError = error;
            _this.loadingRequest = false;
        });
    };
    AssociateReprocessComponent.prototype.selectAllEvents = function () {
        this.check = !this.check;
        for (var _i = 0, _a = this.events; _i < _a.length; _i++) {
            var event_1 = _a[_i];
            event_1.check = this.check;
        }
    };
    AssociateReprocessComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-associate-reprocess',
            template: __webpack_require__("../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/associate/associate-reprocess/associate-reprocess.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_associate_associate_reprocess_service__["a" /* AssociateReprocessService */]])
    ], AssociateReprocessComponent);
    return AssociateReprocessComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-stores/associate-stores.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-stores/associate-stores.component.html":
/***/ (function(module, exports) {

module.exports = "<div #scrollMe class=\"content-wrapper\">\n  <div class=\"container-fluid\">\n    <br/>\n    <br/>\n    <div class=\"text-justifyed\">\n      <h3>Stores Management</h3>\n    </div>\n    \n\n    <div class=\"col-sm-12\">\n      <div class=\"card\">\n        <div class=\"card-header\">\n          <i class=\"fa fa-building\"></i>\n          <strong>Stores in scope</strong>\n        </div>\n        <div class=\"card-body\">\n          \n          \n          <div class=\"row\">\n              <div class=\"clearfix col-sm-12\">\n                 \n                  <button class=\"btn btn-success btn-sm float-right ml-1 mr-1\" (click)=\"downloadStoresInScope()\" *ngIf=\"stores.length>0\"><i class=\"fa fa-file-excel-o\"></i></button>\n                  <div class=\"input-group float-right\" *ngIf=\"stores.length>0\" style=\"width: 250px; height: 31px;\">\n                      <input type=\"text\" class=\"form-control\" placeholder=\"Search stores...\" aria-label=\"Stores search\" aria-describedby=\"basic-addon2\" [(ngModel)]=\"filterQuery\" >\n                      <span class=\"input-group-addon\" id=\"basic-addon2\"><i class=\"fa fa-search\"></i></span>\n                  </div>\n                  \n              </div>\n          </div>\n          <app-error-message [service-error]=\"storesError\" [loading]=\"loadingStores\"></app-error-message>\n\n          <div class=\"row\" *ngIf=\"!loadingStores\">\n            <div class=\"col-sm-12 table-responsive\">\n\n              <table width=\"100%\" class=\"table table-striped table-hover\" id=\"dataTables-example\" [wfmData]=\"stores | dataFilter : filterQuery\"\n                #wfm=\"wfmDataTable\" [wfmRowsOnPage]=\"rowsOnPage\" [(wfmSortBy)]=\"sortBy\" [(wfmSortOrder)]=\"sortOrder\">\n                <thead class=\"thead-dark\">\n                  <tr>\n                    <th class=\"text-justify small\">\n                      <strong>Edit</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Rp Instance</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Interface</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Store Number</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Store Name</strong>\n                    </th>\n                  </tr>\n                </thead>\n                <tbody>\n                  <tr *ngFor=\"let item of wfm.data; let i = index\">\n                    <td class=\"text-justify\">\n                      <button type=\"button\" class=\"btn btn-sm btn-primary\" (click)=\"openEdit('update',item)\" [disabled]=\"showEdit\">\n                            <i class=\"fa fa-pencil-square-o\"></i>\n                          </button>\n                    </td>\n                    <td class=\"text-justify small\">{{item.rpInstance.rpInstance}}</td>\n                    <td class=\"text-justify small\">{{item.interfaceName}}</td>\n                    <td class=\"text-justify small\">{{item.storeNumber}}</td>\n                    <td class=\"text-justify small\">{{item.storeName}}</td>\n                  </tr>\n                </tbody>\n                <tfoot>\n                  <tr>\n                    <td colspan=\"5\">\n                      <wfmPaginator [rowsOnPageSet]=\"[5,10,15]\"></wfmPaginator>\n                    </td>\n                  </tr>\n                </tfoot>\n              </table>\n            </div>\n          </div>\n\n\n          <div class=\"row justify-content-center\">\n              <div class=\"col-4 text-center\">\n                  <button class=\"btn btn-primary\" (click)=\"openEdit('add', undefined)\" [disabled]=\"showEdit\"><i class=\"fa fa-plus\"></i>&nbsp;Create store</button>\n              </div>\n            </div>\n         \n        </div>\n        <div class=\"card-footer small text-muted\">\n\n        </div>\n      </div>\n    </div>\n    <br/>\n\n    <div class=\"col-sm-12\" *ngIf=\"showEdit\">\n      <div class=\"card\">\n        <div class=\"card-header\">\n          <i class=\"fa fa-cog\"></i>\n          <strong *ngIf=\"viewId==1\">Add Store</strong>\n          <strong *ngIf=\"viewId==2\">Update Store</strong>\n          <strong *ngIf=\"viewId==3\">Load stores</strong>\n\n          <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"closeEdit()\">\n              <span aria-hidden=\"true\">&times;</span>\n          </button>\n        </div>\n        <div class=\"card-body\">\n\n          <form #storeForm=\"ngForm\" class=\"container\" id=\"needs-validation\" novalidate *ngIf=\"currentStore!=undefined\">\n            <div class=\"form-row\">\n              \n              <div class=\"form-group col-sm-6 col-md-6 col-lg-3\">\n                <label for=\"snumber\" class=\"col-form-label\">Store Number</label>\n                <input type=\"number\" class=\"form-control\" id=\"snumber\" placeholder=\"Store Number\" [(ngModel)]=\"currentStore.storeNumber\" [disabled]=\"viewId==2\"\n                  name=\"storeNumber\" #storeNumber=\"ngModel\"  required pattern=\"[0-9]{1,5}\" [ngClass]=\"{'is-valid':storeNumber.valid&& storeNumber.dirty, 'is-invalid':storeNumber.invalid && storeNumber.dirty}\">\n              </div>\n              <div class=\"form-group col-sm-6 col-md-6 col-lg-3\" >\n                <label for=\"gtanumber\" class=\"col-form-label\">Interface Name</label>\n                <input type=\"text\" class=\"form-control\" id=\"interfaceName\" placeholder=\"Interface Name\" [(ngModel)]=\"currentStore.interfaceName\"\n                  name=\"interfaceName\" #interfaceName=\"ngModel\" required  pattern=\"[A-Z]{1,9}\" [ngClass]=\"{'is-valid':interfaceName.valid&& interfaceName.dirty, 'is-invalid':interfaceName.invalid && interfaceName.dirty}\">\n              </div>\n              <div class=\"form-group col-sm-10 col-md-10 col-lg-5\">\n                  <label for=\"bunit\" class=\"col-form-label\">Store Name</label>\n                  <input type=\"text\" class=\"form-control\" id=\"bunit\" placeholder=\"Store Name\" [(ngModel)]=\"currentStore.storeName\"\n                    name=\"storeName\" #storeName=\"ngModel\"  required pattern=\"[A-Za-z0-9\\s_-]{0,50}\" [ngClass]=\"{'is-valid':storeName.valid&& storeName.dirty, 'is-invalid':storeName.invalid && storeName.dirty}\">\n              </div>\n              <div class=\"form-group col-sm-2 col-md-2 col-lg-1\">\n                  <label for=\"button\" class=\"col-form-label\">&nbsp;&nbsp;&nbsp;</label>\n                <button *ngIf=\"viewId==1\" class=\"btn btn-primary\" type=\"submit\" [disabled]=\"storeForm.invalid\" (click)=\"addStore()\">\n                  <i class=\"fa fa-plus-circle\"></i> <span>&nbsp;Add</span>\n                </button>\n                <button *ngIf=\"viewId==2\" class=\"btn btn-primary\" type=\"submit\" [disabled]=\"storeForm.invalid\" (click)=\"updateStore()\">\n                  <i class=\"fa fa-refresh\" ></i> <span>&nbsp;Update</span>\n                </button>\n              </div>\n            </div>\n            \n          </form>\n          \n        </div>\n        <div class=\"card-footer small text-muted\">\n\n        </div>\n      </div>\n    </div>\n\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/associate/associate-stores/associate-stores.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateStoresComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__commons_json_2_csv__ = __webpack_require__("../../../../../src/app/commons/json-2-csv.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_papaparse__ = __webpack_require__("../../../../ngx-papaparse/dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_papaparse___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_papaparse__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_associate_store_dto__ = __webpack_require__("../../../../../src/app/models/associate-store-dto.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_associate_associate_stores_service__ = __webpack_require__("../../../../../src/app/services/associate/associate-stores.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_associate_associate_rp_instances_service__ = __webpack_require__("../../../../../src/app/services/associate/associate-rp-instances.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ViewEnum;
(function (ViewEnum) {
    ViewEnum[ViewEnum["add"] = 1] = "add";
    ViewEnum[ViewEnum["update"] = 2] = "update";
    ViewEnum[ViewEnum["load"] = 3] = "load";
})(ViewEnum || (ViewEnum = {}));
var AssociateStoresComponent = (function () {
    function AssociateStoresComponent(route, storesService, instancesService, papa) {
        this.route = route;
        this.storesService = storesService;
        this.instancesService = instancesService;
        this.papa = papa;
        this.filterQuery = "";
        this.rowsOnPage = 5;
        this.sortBy = "storeNumber";
        this.sortOrder = "asc";
    }
    AssociateStoresComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            _this.rpInstance = params['market'];
            _this.viewId = 0;
            _this.stores = [];
            _this.showEdit = false;
            _this.loadStores(_this.rpInstance);
            _this.filterQuery = "";
            _this.instancesService.getRpInstances().subscribe(function (data) { _this.rpInstances = data; _this.loadingStores = false; }, function (error) { _this.storesError = error; _this.loadingStores = false; });
        });
    };
    AssociateStoresComponent.prototype.loadStores = function (rpInstance) {
        var _this = this;
        this.storesError = undefined;
        this.loadingStores = true;
        this.storesService.getStoresByRpInstance(rpInstance).subscribe(function (stores) { _this.stores = stores; _this.loadingStores = false; }, function (error) { _this.storesError = error; _this.loadingStores = false; });
    };
    AssociateStoresComponent.prototype.openEdit = function (action, store) {
        var _this = this;
        this.storesError = undefined;
        this.showEdit = true;
        this.viewId = ViewEnum[action];
        if (store == undefined) {
            this.currentStore = new __WEBPACK_IMPORTED_MODULE_4__models_associate_store_dto__["a" /* AssociateStoreDto */]();
            this.currentStore.rpInstance = this.rpInstances.filter(function (rpInstance) { return rpInstance.rpInstance == _this.rpInstance; }).pop();
        }
        else {
            this.currentStore = store;
        }
    };
    AssociateStoresComponent.prototype.closeEdit = function () {
        this.currentStore = undefined;
        this.showEdit = false;
        this.storesError = undefined;
    };
    AssociateStoresComponent.prototype.updateStore = function () {
        var _this = this;
        this.loadingStores = true;
        this.storesError = undefined;
        this.storesService.upadteStore(this.rpInstance, this.currentStore).subscribe(function (success) { console.info('succesfull Update'); _this.loadingStores = false; }, function (error) { _this.storesError = error; _this.loadingStores = false; });
    };
    AssociateStoresComponent.prototype.addStore = function () {
        var _this = this;
        this.loadingStores = true;
        this.storesError = undefined;
        this.storesService.addNewStore(this.rpInstance, this.currentStore).subscribe(function (success) {
            console.info('succesfull Update');
            _this.loadingStores = false;
            _this.loadStores(_this.rpInstance);
        }, function (error) { _this.storesError = error; _this.loadingStores = false; });
    };
    AssociateStoresComponent.prototype.downloadStoresInScope = function () {
        var date = new Date();
        new __WEBPACK_IMPORTED_MODULE_2__commons_json_2_csv__["a" /* Json2Csv */](this.papa.unparse(this.stores), 'AssociateStores-' + date.getFullYear() + (date.getMonth() + 1) + date.getDate());
    };
    AssociateStoresComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    AssociateStoresComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-associate-stores',
            template: __webpack_require__("../../../../../src/app/pages/associate/associate-stores/associate-stores.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/associate/associate-stores/associate-stores.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */],
            __WEBPACK_IMPORTED_MODULE_5__services_associate_associate_stores_service__["a" /* AssociateStoresService */],
            __WEBPACK_IMPORTED_MODULE_6__services_associate_associate_rp_instances_service__["a" /* AssociateRpInstancesService */],
            __WEBPACK_IMPORTED_MODULE_3_ngx_papaparse__["PapaParseService"]])
    ], AssociateStoresComponent);
    return AssociateStoresComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/dashboard/dashboard.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/dashboard/dashboard.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"content-wrapper\" style=\"background:#ecf0f1;\">      \n  <br/>\n<div class=\"row  justify-content-end\" >\n  <div class=\"col-md-6 text-right mr-3\">\n      <a class=\"float-right small\" href=\"http://kibana.prod2.gwfm-alerting.bopwm.dal3.prod.walmart.com:5601/app/kibana#/dashboard/TOR-topology?_g=(refreshInterval:(display:Off,pause:!f,value:0),time:(from:now-7d,mode:quick,to:now))&_a=(filters:!(),options:(darkTheme:!f),panels:!((col:4,id:TORs-processed-by-market,panelIndex:1,row:12,size_x:4,size_y:4,type:visualization),(col:1,id:TORs-processed-daily,panelIndex:3,row:3,size_x:6,size_y:3,type:visualization),(col:1,id:TORs-processed,panelIndex:4,row:1,size_x:12,size_y:2,type:visualization),(col:8,id:TORs-processed-market-slash-status,panelIndex:9,row:8,size_x:5,size_y:4,type:visualization),(col:1,id:TOR-daily-errors,panelIndex:12,row:16,size_x:8,size_y:4,type:visualization),(col:8,id:TOR-total-time-median,panelIndex:14,row:12,size_x:5,size_y:4,type:visualization),(col:1,id:TOR-Process-Type,panelIndex:15,row:8,size_x:3,size_y:2,type:visualization),(col:4,id:TORs-process-time-by-range,panelIndex:17,row:8,size_x:4,size_y:4,type:visualization),(col:1,id:TORs-processed-USA,panelIndex:21,row:10,size_x:3,size_y:2,type:visualization),(col:1,id:TORs-processed-for-Canada,panelIndex:22,row:12,size_x:3,size_y:2,type:visualization),(col:4,id:TOR-process-time-calculations-overview,panelIndex:27,row:6,size_x:9,size_y:2,type:visualization),(col:1,id:TOR-event-priority-chart,panelIndex:28,row:14,size_x:3,size_y:2,type:visualization),(col:7,id:TOR-tors-processed-hourly,panelIndex:29,row:3,size_x:6,size_y:3,type:visualization),(col:1,id:TOR-eventtype-piechart,panelIndex:30,row:6,size_x:3,size_y:2,type:visualization),(col:9,id:TOR-errors-by-originIP,panelIndex:31,row:16,size_x:4,size_y:4,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'TOR%20topology',uiState:(P-1:(vis:(colors:(APPROVED:%23508642,CA:%230A437C,CANCELLED_AFTER_APPROVED:%23EA6460,US:%2382B5D8))),P-12:(vis:(legendOpen:!t)),P-14:(spy:(mode:(fill:!f,name:!n)),vis:(colors:('50th%20percentile%20of%20customObject.processTotalTime':%23E5AC0E,'9,096.757':%237EB26D,'Maximum%20total%20time':%23BF1B00,'Maximum%20total%20time%20%5Bseconds%5D':%23BF1B00,'Minimum%20total%20time%20%5Bseconds%5D':%23508642),legendOpen:!t)),P-15:(vis:(colors:(NORMAL:%23447EBC,'New%20Store':%23447EBC,Normal:%237EB26D,Reprocess:%23EAB839))),P-17:(vis:(colors:('Number%20of%20TORs':%23806EB7),legendOpen:!f)),P-26:(vis:(colors:(error:%23890F02,'info%20processed':%23508642))),P-28:(vis:(colors:(Important:%23F2C96D,Major:%23E0752D,Minor:%239AC48A))),P-3:(vis:(colors:('TORs%20Count':%23614D93),legendOpen:!t)),P-31:(spy:(mode:(fill:!f,name:!n)),vis:(colors:('Nr%20of%20errors':%23E24D42),legendOpen:!t))))\"\n       style=\"color:#4d515b;\" target=\"_blank\">For more filters and information press here.</a>\n  </div>\n   \n</div>\n<div class=\"embed-responsive embed-responsive embed-responsive-1by1\">\n  <iframe class=\"embed-responsive-item\" [src]=\"kibanaUrl\"  width=\"100%\"></iframe>\n</div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/dashboard/dashboard.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_dashboard_enum_enum__ = __webpack_require__("../../../../../src/app/models/dashboard-enum.enum.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var DashboardComponent = (function () {
    function DashboardComponent(route, sanitizer) {
        this.route = route;
        this.sanitizer = sanitizer;
    }
    DashboardComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            var key = Number(__WEBPACK_IMPORTED_MODULE_3__models_dashboard_enum_enum__["a" /* DashboardEnum */][params['view']]);
            switch (key) {
                case __WEBPACK_IMPORTED_MODULE_3__models_dashboard_enum_enum__["a" /* DashboardEnum */].events:
                    _this.kibanaUrl = _this.sanitizer.bypassSecurityTrustResourceUrl(__WEBPACK_IMPORTED_MODULE_4__environments_environment__["a" /* environment */].torsEventsDashboard);
                    break;
                case __WEBPACK_IMPORTED_MODULE_3__models_dashboard_enum_enum__["a" /* DashboardEnum */].topology:
                    _this.kibanaUrl = _this.sanitizer.bypassSecurityTrustResourceUrl(__WEBPACK_IMPORTED_MODULE_4__environments_environment__["a" /* environment */].torsTopologyDashboard);
                    break;
                case __WEBPACK_IMPORTED_MODULE_3__models_dashboard_enum_enum__["a" /* DashboardEnum */].stores:
                    _this.kibanaUrl = _this.sanitizer.bypassSecurityTrustResourceUrl(__WEBPACK_IMPORTED_MODULE_4__environments_environment__["a" /* environment */].torsStoresDashboard);
                    break;
                case __WEBPACK_IMPORTED_MODULE_3__models_dashboard_enum_enum__["a" /* DashboardEnum */].elastic:
                    _this.kibanaUrl = _this.sanitizer.bypassSecurityTrustResourceUrl(__WEBPACK_IMPORTED_MODULE_4__environments_environment__["a" /* environment */].healthcheckElasticDashboard);
                    break;
                default:
                    _this.kibanaUrl = _this.sanitizer.bypassSecurityTrustResourceUrl(__WEBPACK_IMPORTED_MODULE_4__environments_environment__["a" /* environment */].torsTopologyDashboard);
                    break;
            }
        });
    };
    DashboardComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    DashboardComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-dashboard',
            template: __webpack_require__("../../../../../src/app/pages/dashboard/dashboard.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/dashboard/dashboard.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* ActivatedRoute */],
            __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser__["c" /* DomSanitizer */]])
    ], DashboardComponent);
    return DashboardComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/logout/logout.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/logout/logout.component.html":
/***/ (function(module, exports) {

module.exports = "\n<div class=\"container\">\n    \n      <div class=\"row justify-content-center\">\n          <img [src]=\"'/assets/img/wm-spark.jpeg'\" width=\"200\" height=\"100\">\n      </div>\n      <div class=\"row justify-content-center\">\n          <h1>Thanks for your visit ...</h1>\n      </div>\n\n      <div class=\"row justify-content-center\">\n          <a class=\"nav-link\" routerLink=\"/login\">\n            <i class=\"fa fa-reply\"></i>\n            <span class=\"nav-link-text\">\n            Return to login</span>\n          </a>\n      </div>\n\n      <carousel>\n        <slide>\n          <img src=\"assets/img/data.png\" alt=\"first slide\" style=\"display: block; width: 100%;\">\n          <div class=\"carousel-caption d-none d-md-block\">\n            <h3>First slide label</h3>\n            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>\n          </div>\n        </slide>\n        <slide>\n          <img src=\"assets/img/engine.png\" alt=\"second slide\" style=\"display: block; width: 100%;\">\n          <div class=\"carousel-caption d-none d-md-block\">\n            <h3>Second slide label</h3>\n            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>\n          </div>\n        </slide>\n        <slide>\n          <img src=\"assets/img/userinterfaceicon.png\" alt=\"third slide\" style=\"display: block; width: 100%;\">\n          <div class=\"carousel-caption d-none d-md-block\">\n            <h3>Third slide label</h3>\n            <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>\n          </div>\n        </slide>\n    </carousel>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/pages/logout/logout.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LogoutComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var LogoutComponent = (function () {
    function LogoutComponent() {
    }
    LogoutComponent.prototype.ngOnInit = function () {
    };
    LogoutComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-logout',
            template: __webpack_require__("../../../../../src/app/pages/logout/logout.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/logout/logout.component.css")],
        }),
        __metadata("design:paramtypes", [])
    ], LogoutComponent);
    return LogoutComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/page-not-found/page-not-found.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/page-not-found/page-not-found.component.html":
/***/ (function(module, exports) {

module.exports = "<p>\n  Page Not Found\n</p>\n"

/***/ }),

/***/ "../../../../../src/app/pages/page-not-found/page-not-found.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PageNotFoundComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var PageNotFoundComponent = (function () {
    function PageNotFoundComponent() {
    }
    PageNotFoundComponent.prototype.ngOnInit = function () {
    };
    PageNotFoundComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-page-not-found',
            template: __webpack_require__("../../../../../src/app/pages/page-not-found/page-not-found.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/page-not-found/page-not-found.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], PageNotFoundComponent);
    return PageNotFoundComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/tor/maintenance/maintenance.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/tor/maintenance/maintenance.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"content-wrapper\">\n  <div class=\"container-fluid\">\n    <br/>\n    <br/>\n    <div class=\"text-centered\">\n      <h3>Maintenance Period</h3>\n    </div>\n    <br/>\n    <div class=\"col-sm-12\">\n      <div class=\"card\">\n        <div class=\"card-header\">\n          <i class=\"fa fa-building\"></i>\n          <strong>Market Availability</strong>\n        </div>\n        <div class=\"card-body\">\n          \n          <div class=\"col-sm-12\">\n            <div class=\"row\">\n              <div class=\"col-sm-12 col-md-3\">\n                <div class=\"text-center\">\n                  <strong>Add new maintenance period</strong>\n                </div>\n                <br/>\n                <div class=\"row \">\n                  <div class=\"col-lg-6\">\n                      <label>Week day</label>\n                  </div>\n                  <div class=\"col-lg-6\">\n                      <select class=\"float-left form-control\" id=\"weekDays\" (change)=\"changeDayOfWeek($event.target.value)\">\n                          <option *ngFor=\"let weekDay of weekDays\">{{weekDay}}</option>\n                      </select>\n                  </div>\n                </div>\n                \n                <div class=\"row \">\n                    <div class=\"col-lg-6\">\n                        <label for=\"start\" class=\"col-form-label\">Start time </label>\n                    </div>\n                    <div class=\"col-lg-6\">\n                        <timepicker [(ngModel)]=\"startTime\" [showMeridian]=\"false\"  [mousewheel]=\"false\" ></timepicker>\n                    </div>\n                </div>\n                <div class=\"row \">\n                        <div class=\"col-lg-6\">\n                            <label for=\"end\" class=\"col-form-label\">End time</label>\n                        </div>\n                        <div class=\"col-lg-6\">\n                            <timepicker [(ngModel)]=\"endTime\" [min]=\"startTime\" [showMeridian]=\"false\"  [mousewheel]=\"false\" ></timepicker>\n                        </div>\n                </div>\n                <br/>\n                <div class=\"text-center\">\n                  <button class=\"btn btn-primary\" (click)=\"addNewMaintenance()\" [disabled]=\"loadingMaintenance\">\n                    <i class=\"fa fa-plus\"></i>&nbsp;&nbsp;Add\n                  </button>\n                </div>\n              </div>\n              <div class=\"col-md-9 col-xs-12\">\n                  <app-error-message [service-error]=\"maintenanceErrorr\"  (eventClicked)=\"handleEvent('Clicked', $event.event)\"\n                  [loading]=\"loadingMaintenance\"></app-error-message>\n                  <mwl-calendar-week-view *ngIf=\"!loadingMaintenance\" [viewDate]=\"startTime\" [events]=\"events\" [refresh]=\"refresh\" ></mwl-calendar-week-view>\n                </div>\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/tor/maintenance/maintenance.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MaintenanceComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_week_days_enum__ = __webpack_require__("../../../../../src/app/models/week-days.enum.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_tor_maintenance_service__ = __webpack_require__("../../../../../src/app/services/tor/maintenance.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_maintenance_builder__ = __webpack_require__("../../../../../src/app/models/maintenance-builder.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_maintenance__ = __webpack_require__("../../../../../src/app/models/maintenance.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var MaintenanceComponent = (function () {
    function MaintenanceComponent(route, maintenanceService) {
        var _this = this;
        this.route = route;
        this.maintenanceService = maintenanceService;
        this.events = [];
        //Delete  calendar event
        this.actions = [
            {
                label: '<i class="fa fa-fw fa-times"></i>',
                onClick: function (_a) {
                    var event = _a.event;
                    _this.events = _this.events.filter(function (iEvent) { return iEvent !== event; });
                    _this.handleEvent('Deleted', event);
                }
            }
        ];
    }
    //Before job
    MaintenanceComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.market = 'US';
        this.sub = this.route.params.subscribe(function (params) {
            _this.market = params['market'];
            _this.mainntenances = [];
            _this.weekDays = _this.maintenanceService.getWeekDays();
            _this.weekDay = __WEBPACK_IMPORTED_MODULE_2__models_week_days_enum__["a" /* WeekDays */][1];
            _this.startTime = new Date();
            _this.endTime = new Date();
            _this.getMantenanceByMarket(_this.market);
        });
    };
    //After job
    MaintenanceComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    //Charge  calendar by market
    MaintenanceComponent.prototype.getMantenanceByMarket = function (market) {
        var _this = this;
        this.mainntenances = [];
        this.market = market;
        this.events = [];
        this.maintenanceService.getMaintenanceByMarket(market)
            .subscribe(function (mainntenances) {
            _this.mainntenances = mainntenances;
            _this.maintenanceErrorr = null;
            _this.events = _this.maintenanceService.assignMaintenance(_this.mainntenances, _this.actions);
        }, function (error) {
            _this.maintenanceErrorr = error;
            _this.loadingMaintenance = false;
        });
    };
    //Change day of week combo
    MaintenanceComponent.prototype.changeDayOfWeek = function (weekDay) {
        this.weekDay = weekDay;
    };
    //Create new maintenance
    MaintenanceComponent.prototype.addNewMaintenance = function () {
        var _this = this;
        this.loadingMaintenance = true;
        var maintenances = new __WEBPACK_IMPORTED_MODULE_4__models_maintenance_builder__["a" /* MaintenanceDayBuilder */]().buildMaintenanceDayNew(this.startTime, this.endTime, this.market, this.weekDay);
        if (maintenances.length > 0) {
            this.maintenanceService.upsertMaintenance(maintenances[0]).subscribe(function (succes) {
                if (maintenances.length > 1) {
                    _this.maintenanceService.upsertMaintenance(maintenances[1]).subscribe(function (succes) {
                        _this.getMantenanceByMarket(_this.market);
                        _this.maintenanceErrorr = null;
                        _this.loadingMaintenance = false;
                    }, function (error) {
                        _this.maintenanceErrorr = error;
                        _this.loadingMaintenance = false;
                    });
                }
                else {
                    _this.getMantenanceByMarket(_this.market);
                    _this.maintenanceErrorr = null;
                    _this.loadingMaintenance = false;
                }
            }, function (error) {
                _this.maintenanceErrorr = error;
                _this.loadingMaintenance = false;
            });
        }
        else {
            this.maintenanceErrorr = this.maintenanceService.createError("Error creating maintenance", "Data error");
            this.loadingMaintenance = false;
        }
    };
    //Event for delete  maintenance
    MaintenanceComponent.prototype.handleEvent = function (action, event) {
        var _this = this;
        this.maintenanceService.deletetMaintenance(new __WEBPACK_IMPORTED_MODULE_5__models_maintenance__["a" /* Maintenance */](event.startHr, event.endHr, event.dayOfWeek, event.market)).subscribe(function (correct) { _this.maintenanceErrorr = null; _this.loadingMaintenance = false; }, function (error) { _this.maintenanceErrorr = error; _this.loadingMaintenance = false; });
    };
    MaintenanceComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-maintenance',
            template: __webpack_require__("../../../../../src/app/pages/tor/maintenance/maintenance.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/tor/maintenance/maintenance.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */],
            __WEBPACK_IMPORTED_MODULE_3__services_tor_maintenance_service__["a" /* MaintenanceService */]])
    ], MaintenanceComponent);
    return MaintenanceComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/tor/stores-load/stores-load.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".success-validation{\n    background-color: rgba(0, 255, 0, 0.26);\n}\n\n.fail-validation{\n    background-color: rgba(255, 0, 0, 0.26);\n}\n\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/tor/stores-load/stores-load.component.html":
/***/ (function(module, exports) {

module.exports = "\n<div class=\"row\">\n  <div class=\"col-md-4 col-lg-4\">\n      <p class=\"small text-center\">1.- Download the sample file.</p>\n  </div>\n  <div class=\"col-md-4 col-lg-4\">\n      <p class=\"small text-center\">2.- Edit and upload the file with the stores to load.</p>\n  </div>\n  <div class=\"col-md-4 col-lg-4\">\n      <p class=\"small text-center\">3.- Upload again the file and press Send stores</p>\n  </div>\n</div>\n<div class=\"row\">\n    <div class=\"col-md-4 col-lg-4 text-center\">\n        <button class=\"btn btn-success\" (click)=\"downloadFile()\">\n            <i class=\"fa fa-download\"></i> &nbsp;Download\n        </button>\n    </div>\n    <div class=\"col-md-4 col-lg-4\">\n        <input type=\"file\" id=\"filecsv\" accept=\".csv\" (change)=\"fileUploadListener($event)\" class=\"custom-file-input\">\n        <span class=\"custom-file-control\">{{filename}}</span>\n    </div>\n    <div class=\"col-md-4 col-lg-4 text-center\">\n        <button class=\"btn btn-primary \" [disabled]=\"storesToLoad.length==0 || loadingStores==true || loadError!=undefined\" (click)=\"sendStores()\">\n            <i class=\"fa fa-send\"></i> &nbsp;Send stores\n        </button>\n    </div>\n</div>\n<br/>\n<app-error-message [service-error]=\"loadError\" [loading]=\"loadingStores\"></app-error-message>\n<div class=\"alert alert-success\" *ngIf=\"sucessMessage!=undefined\">\n  {{sucessMessage}}\n</div>\n<div class=\"row\" *ngIf=\"storesToLoad.length>0 && !loadingStores\">\n  <div class=\"col-sm-12 table-responsive\">\n\n    <table width=\"100%\" class=\"table table-striped table-hover\" id=\"storesToLoad\" [wfmData]=\"storesToLoad | dataFilter : filterQuery\"\n      #wfm=\"wfmDataTable\" [wfmRowsOnPage]=\"rowsOnPage\" [(wfmSortBy)]=\"sortBy\" [(wfmSortOrder)]=\"sortOrder\">\n      <thead class=\"thead-inverse\">\n        <tr>\n          <th class=\"text-justify small\">\n            <strong>Validation</strong>\n          </th>\n          <th class=\"text-justify small\">\n            <strong>Market</strong>\n          </th>\n          <th class=\"text-justify small\">\n            <strong>Business Unit</strong>\n          </th>\n          <th class=\"text-justify small\">\n            <strong>Store Number</strong>\n          </th>\n          <th class=\"text-justify small\">\n            <strong>Store Number GTA</strong>\n          </th>\n          <th class=\"text-justify small\">\n            <strong>Store Name</strong>\n          </th>\n\n        </tr>\n      </thead>\n      <tbody>\n        <tr *ngFor=\"let item of wfm.data\">\n          <td class=\"text-center\">\n            <i class=\"fa fa-exclamation-circle text-warning\" *ngIf=\"false==checkRecord(item.storeNumber,item.storeNumberGta)\"></i>\n            <i class=\"fa fa-check-circle text-success\" *ngIf=\"checkRecord(item.storeNumber,item.storeNumberGta)\"></i>\n          </td>\n          <td class=\"text-justify small\">{{item.market}}</td>\n          <td class=\"text-justify small\">{{item.businessUnitId}}</td>\n          <td class=\"text-justify small\">{{item.storeNumber}}</td>\n          <td class=\"text-justify small\">{{item.storeNumberGta}}</td>\n          <td class=\"text-justify small\">{{item.storeName}}</td>\n\n        </tr>\n      </tbody>\n      <tfoot>\n        <tr>\n          <td colspan=\"8\">\n            <wfmPaginator [rowsOnPageSet]=\"[5,10,15]\"></wfmPaginator>\n          </td>\n        </tr>\n      </tfoot>\n    </table>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/tor/stores-load/stores-load.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StoresLoadComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_papaparse__ = __webpack_require__("../../../../ngx-papaparse/dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_papaparse___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ngx_papaparse__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__commons_json_2_csv__ = __webpack_require__("../../../../../src/app/commons/json-2-csv.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_tor_stores_service__ = __webpack_require__("../../../../../src/app/services/tor/stores.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_service_error__ = __webpack_require__("../../../../../src/app/models/service-error.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var StoresLoadComponent = (function () {
    function StoresLoadComponent(papa, storesServices) {
        this.papa = papa;
        this.storesServices = storesServices;
        this.filterQuery = "";
        this.rowsOnPage = 5;
        this.sortBy = "storeNumber";
        this.sortOrder = "asc";
        this.storesToLoad = [];
        this.file = undefined;
        this.regexGTA = new RegExp('^[A-Z0-9_-]{5,9}$');
        this.regexStrNumber = new RegExp('^[0-9]{3,5}$');
        this.sucessMessage = undefined;
        this.filename = '';
    }
    StoresLoadComponent.prototype.ngOnInit = function () {
    };
    StoresLoadComponent.prototype.fileUploadListener = function ($event) {
        var _this = this;
        this.storesToLoad = [];
        this.loadError = undefined;
        this.file = $event.target.files[0];
        this.filename = this.file.name;
        this.papa.parse(this.file, {
            header: true,
            dynamicTyping: false,
            complete: function (results, file) {
                _this.storesToLoad = results.data;
                if (results.data.length == 0) {
                    _this.loadError = new __WEBPACK_IMPORTED_MODULE_4__models_service_error__["a" /* ServiceError */]('Empty file!', 'The file no contains any store to load.');
                }
                for (var index in results.data) {
                    if (!_this.checkRecord(results.data[index].storeNumber, results.data[index].storeNumberGta)) {
                        _this.loadError = new __WEBPACK_IMPORTED_MODULE_4__models_service_error__["a" /* ServiceError */]('There are invalid records!', 'Review  if all the storeNumbers contains only numbers and the storeNumberGta has at least 5 digits.');
                        break;
                    }
                }
            }
        });
    };
    StoresLoadComponent.prototype.downloadFile = function () {
        var sample = [{
                market: "US",
                storeNumber: 1,
                storeNumberGta: "00001",
                businessUnitId: "000000001",
                storeName: "businesUnit - format- location",
            }];
        new __WEBPACK_IMPORTED_MODULE_2__commons_json_2_csv__["a" /* Json2Csv */](this.papa.unparse(sample, { header: false }), 'SampleStoresLoad');
    };
    StoresLoadComponent.prototype.checkRecord = function (storeNumber, storeNumberGta) {
        return this.regexGTA.test(storeNumberGta) && this.regexStrNumber.test(storeNumber);
    };
    StoresLoadComponent.prototype.sendStores = function () {
        var _this = this;
        this.sucessMessage = undefined;
        this.loadingStores = true;
        this.storesServices.loadStores(this.file).subscribe(function (result) { _this.sucessMessage = result; _this.storesToLoad = []; _this.loadingStores = false; }, function (error) { _this.loadError = error; _this.loadingStores = false; });
    };
    StoresLoadComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-stores-load',
            template: __webpack_require__("../../../../../src/app/pages/tor/stores-load/stores-load.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/tor/stores-load/stores-load.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ngx_papaparse__["PapaParseService"],
            __WEBPACK_IMPORTED_MODULE_3__services_tor_stores_service__["a" /* StoresService */]])
    ], StoresLoadComponent);
    return StoresLoadComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/tor/stores/stores.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "\n\n\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/tor/stores/stores.component.html":
/***/ (function(module, exports) {

module.exports = "<div #scrollMe class=\"content-wrapper\">\n  <div class=\"container-fluid\">\n    <br/>\n    <br/>\n    <div class=\"text-justifyed\">\n      <h3>Stores Management</h3>\n    </div>\n    \n\n    <div class=\"col-sm-12\">\n      <div class=\"card\">\n        <div class=\"card-header\">\n          <i class=\"fa fa-building\"></i>\n          <strong>Stores in scope</strong>\n        </div>\n        <div class=\"card-body\">\n          \n          \n          <div class=\"row\">\n              <div class=\"clearfix col-sm-12\">\n                 \n                  <button class=\"btn btn-success btn-sm float-right ml-1 mr-1\" (click)=\"downloadStoresInScope()\" *ngIf=\"stores.length>0\"><i class=\"fa fa-file-excel-o\"></i></button>\n                  <div class=\"input-group float-right\" *ngIf=\"stores.length>0\" style=\"width: 250px; height: 31px;\">\n                      <input type=\"text\" class=\"form-control\" placeholder=\"Search stores...\" aria-label=\"Stores search\" aria-describedby=\"basic-addon2\" [(ngModel)]=\"filterQuery\" >\n                      <span class=\"input-group-addon\" id=\"basic-addon2\"><i class=\"fa fa-search\"></i></span>\n                  </div>\n                  \n              </div>\n          </div>\n          <app-error-message [service-error]=\"storesError\" [loading]=\"loadingStores\"></app-error-message>\n\n          <div class=\"row\" *ngIf=\"!loadingStores\">\n            <div class=\"col-sm-12 table-responsive\">\n\n              <table width=\"100%\" class=\"table table-striped table-hover\" id=\"dataTables-example\" [wfmData]=\"stores | dataFilter : filterQuery\"\n                #wfm=\"wfmDataTable\" [wfmRowsOnPage]=\"rowsOnPage\" [(wfmSortBy)]=\"sortBy\" [(wfmSortOrder)]=\"sortOrder\">\n                <thead class=\"thead-dark\">\n                  <tr>\n                    <th class=\"text-justify small\">\n                      <strong>Edit</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Market</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Business Unit</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Store Number</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Store Number GTA</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Store Name</strong>\n                    </th>\n                    <th class=\"text-justify small\">\n                      <strong>Store Group</strong>\n                    </th>\n                  </tr>\n                </thead>\n                <tbody>\n                  <tr *ngFor=\"let item of wfm.data; let i = index\">\n                    <td class=\"text-justify\">\n                      <button type=\"button\" class=\"btn btn-sm btn-primary\" (click)=\"openEdit('update',item)\" [disabled]=\"showEdit\">\n                            <i class=\"fa fa-pencil-square-o\"></i>\n                          </button>\n                    </td>\n                    <td class=\"text-justify small\">{{item.market}}</td>\n                    <td class=\"text-justify small\">{{item.businessUnitId}}</td>\n                    <td class=\"text-justify small\">{{item.storeNumber}}</td>\n                    <td class=\"text-justify small\">{{item.storeNumberGta}}</td>\n                    <td class=\"text-justify small\">{{item.storeName}}</td>\n                    <td class=\"text-justify small\">{{item.groupName}}</td>\n                  </tr>\n                </tbody>\n                <tfoot>\n                  <tr>\n                    <td colspan=\"8\">\n                      <wfmPaginator [rowsOnPageSet]=\"[5,10,15]\"></wfmPaginator>\n                    </td>\n                  </tr>\n                </tfoot>\n              </table>\n            </div>\n          </div>\n\n\n          <div class=\"row justify-content-center\">\n              <div class=\"col-4 text-center\">\n                <button class=\"btn btn-primary\" (click)=\"openEdit('load',undefined)\" [disabled]=\"showEdit\"><i class=\"fa fa-file-o\"></i>&nbsp;Create stores</button>  \n              </div>\n              <div class=\"col-4 text-center\">\n                  <button class=\"btn btn-primary\" (click)=\"openEdit('add', undefined)\" [disabled]=\"showEdit\"><i class=\"fa fa-plus\"></i>&nbsp;Create store</button>\n              </div>\n            </div>\n         \n        </div>\n        <div class=\"card-footer small text-muted\">\n\n        </div>\n      </div>\n    </div>\n    <br/>\n\n    <div class=\"col-sm-12\" *ngIf=\"showEdit\">\n      <div class=\"card\">\n        <div class=\"card-header\">\n          <i class=\"fa fa-cog\"></i>\n          <strong *ngIf=\"viewId==1\">Add Store</strong>\n          <strong *ngIf=\"viewId==2\">Update Store</strong>\n          <strong *ngIf=\"viewId==3\">Load stores</strong>\n\n          <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"closeEdit()\">\n              <span aria-hidden=\"true\">&times;</span>\n          </button>\n        </div>\n        <div class=\"card-body\" *ngIf=\"viewId==2 || viewId==1\">\n\n          <form #storeForm=\"ngForm\" class=\"container\" id=\"needs-validation\" novalidate *ngIf=\"currentStore!=undefined\">\n            <div class=\"form-row\">\n              \n              <div class=\"form-group col-sm-6 col-md-6 col-lg-3\">\n                <label for=\"snumber\" class=\"col-form-label\">Store Number</label>\n                <input type=\"number\" class=\"form-control\" id=\"snumber\" placeholder=\"Store Number\" [(ngModel)]=\"currentStore.storeNumber\"\n                  name=\"storeNumber\" #storeNumber=\"ngModel\"  required pattern=\"[0-9]{3,5}\" [ngClass]=\"{'is-valid':storeNumber.valid&& storeNumber.dirty, 'is-invalid':storeNumber.invalid && storeNumber.dirty}\">\n              </div>\n              <div class=\"form-group col-sm-6 col-md-6 col-lg-3\" >\n                <label for=\"gtanumber\" class=\"col-form-label\">GTA Number</label>\n                <input type=\"text\" class=\"form-control\" id=\"gtanumber\" placeholder=\"Store Number GTA\" [(ngModel)]=\"currentStore.storeNumberGta\"\n                  name=\"storeNumberGta\" #storeNumberGta=\"ngModel\" required  pattern=\"[A-Z0-9_-]{5,9}\" [ngClass]=\"{'is-valid':storeNumberGta.valid&& storeNumberGta.dirty, 'is-invalid':storeNumberGta.invalid && storeNumberGta.dirty}\">\n              </div>\n              <div class=\"form-group col-sm-12 col-md-6 col-lg-3\">\n                  <label for=\"bunit\" class=\"col-form-label\">Business Unit</label>\n                  <input type=\"text\" class=\"form-control\" id=\"bunit\" placeholder=\"Business Unit Id\" [(ngModel)]=\"currentStore.businessUnitId\"\n                    name=\"businessUnitId\" #businessUnitId=\"ngModel\"  required pattern=\"[0-9]{9}\" [ngClass]=\"{'is-valid':businessUnitId.valid&& businessUnitId.dirty, 'is-invalid':businessUnitId.invalid && businessUnitId.dirty}\">\n                </div>\n                <div class=\"form-group col-sm-12 col-md-6 col-lg-3\">\n                  <label for=\"storename\" class=\"col-form-label\">Store Name</label>\n                  <input type=\"text\" class=\"form-control\" id=\"storename\" placeholder=\"00000xxxx- Format - Location\" [(ngModel)]=\"currentStore.storeName\"\n                    name=\"storeName\" #storeName=\"ngModel\"  required pattern=\"[A-Za-z0-9\\s_-]{0,50}\" [ngClass]=\"{'is-valid':storeName.valid&& storeName.dirty, 'is-invalid':storeName.invalid && storeName.dirty}\">\n                </div>\n            </div>\n            \n            <button class=\"btn btn-primary\" type=\"submit\" [disabled]=\"storeForm.invalid\" (click)=\"upsertStore()\">\n              <i class=\"fa fa-plus-circle\" *ngIf=\"viewId==1\"></i> <span *ngIf=\"viewId==1\">&nbsp;Add</span>\n              <i class=\"fa fa-refresh\" *ngIf=\"viewId==2\"></i> <span *ngIf=\"viewId==2\">&nbsp;Update</span>\n            </button>\n          </form>\n          \n        </div>\n        <div class=\"card-body\" *ngIf=\"viewId==3\">\n          <app-stores-load></app-stores-load>\n\n        </div>\n        <div class=\"card-footer small text-muted\">\n\n        </div>\n      </div>\n    </div>\n\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/tor/stores/stores.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StoresComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_tor_stores_service__ = __webpack_require__("../../../../../src/app/services/tor/stores.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_tor_markets_service__ = __webpack_require__("../../../../../src/app/services/tor/markets.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_tor_store__ = __webpack_require__("../../../../../src/app/models/tor-store.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__commons_json_2_csv__ = __webpack_require__("../../../../../src/app/commons/json-2-csv.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_papaparse__ = __webpack_require__("../../../../ngx-papaparse/dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_papaparse___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_ngx_papaparse__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ViewEnum;
(function (ViewEnum) {
    ViewEnum[ViewEnum["add"] = 1] = "add";
    ViewEnum[ViewEnum["update"] = 2] = "update";
    ViewEnum[ViewEnum["load"] = 3] = "load";
})(ViewEnum || (ViewEnum = {}));
var StoresComponent = (function () {
    function StoresComponent(route, storesServices, marketsService, papa) {
        this.route = route;
        this.storesServices = storesServices;
        this.marketsService = marketsService;
        this.papa = papa;
        this.filterQuery = "";
        this.rowsOnPage = 5;
        this.sortBy = "storeNumber";
        this.sortOrder = "asc";
    }
    StoresComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            _this.market = params['market'];
            _this.viewId = 0;
            _this.stores = [];
            _this.markets = [];
            _this.showEdit = false;
            _this.loadStores(_this.market);
            _this.filterQuery = "";
            _this.marketsService.getMarkets().subscribe(function (markets) { _this.markets = markets; _this.loadingStores = false; }, function (error) { _this.storesError = error; _this.loadingStores = false; });
        });
    };
    StoresComponent.prototype.loadStores = function (market) {
        var _this = this;
        this.storesError = undefined;
        this.loadingStores = true;
        this.storesServices.getStoresByMarket(market).subscribe(function (stores) { _this.stores = stores; _this.loadingStores = false; }, function (error) { _this.storesError = error; _this.loadingStores = false; });
    };
    StoresComponent.prototype.downloadStoresInScope = function () {
        var date = new Date();
        new __WEBPACK_IMPORTED_MODULE_5__commons_json_2_csv__["a" /* Json2Csv */](this.papa.unparse(this.stores), 'storesInScope-' + date.getFullYear() + (date.getMonth() + 1) + date.getDate());
    };
    StoresComponent.prototype.openEdit = function (action, store) {
        this.storesError = undefined;
        this.showEdit = true;
        this.viewId = ViewEnum[action];
        if (store == undefined) {
            this.currentStore = new __WEBPACK_IMPORTED_MODULE_4__models_tor_store__["a" /* TorStore */]();
            this.currentStore.market = this.market;
        }
        else {
            this.currentStore = store;
        }
    };
    StoresComponent.prototype.closeEdit = function () {
        this.currentStore = undefined;
        this.showEdit = false;
        this.storesError = undefined;
    };
    StoresComponent.prototype.upsertStore = function () {
        var _this = this;
        this.loadingStores = true;
        this.storesError = undefined;
        this.storesServices.upsertStore(this.currentStore).subscribe(function (success) {
            _this.loadStores(_this.currentStore.market);
            _this.closeEdit();
        }, function (error) {
            {
                _this.storesError = error;
                _this.loadingStores = false;
            }
        });
    };
    StoresComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    StoresComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-stores',
            template: __webpack_require__("../../../../../src/app/pages/tor/stores/stores.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/tor/stores/stores.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */],
            __WEBPACK_IMPORTED_MODULE_2__services_tor_stores_service__["a" /* StoresService */],
            __WEBPACK_IMPORTED_MODULE_3__services_tor_markets_service__["a" /* MarketsService */],
            __WEBPACK_IMPORTED_MODULE_6_ngx_papaparse__["PapaParseService"]])
    ], StoresComponent);
    return StoresComponent;
}());



/***/ }),

/***/ "../../../../../src/app/pages/tor/tors/tors.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/tor/tors/tors.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"content-wrapper\">\n  <div class=\"container-fluid\">\n    <br/>\n    <br/>\n    <h3> Reprocess Time Off Request</h3>\n    <br/>\n    <br/>\n    <div class=\"row justify-content-center\">\n      <div class=\"col-md-12\">\n        <div class=\"card mb-3\">\n          <div class=\"card-header text-center\">\n            <i class=\"fa fa-search-plus\"></i>\n            <strong>TOR's reprocess search</strong>\n          </div>\n          <div class=\"card-body\">\n            <form #torForm=\"ngForm\">\n              <div class=\"form-row\">\n                <div class=\"form-group col-md-4 col-sm-12\">\n                  <label for=\"gtaid\" class=\"col-form-label\">Elastic ID</label>\n                  <input type=\"string\" class=\"form-control\" [(ngModel)]=\"id\" name=\"elasticId\"\n                    id=\"elasticId\">\n                </div>\n                <div class=\"form-group col-md-4 col-sm-6\">\n                  <label for=\"start\" class=\"col-form-label\">Start Date</label>\n                  <div class=\"input-group\">\n                    <input type=\"text\" class=\"form-control\" #dp1=\"bsDatepicker\" bsDatepicker [(bsValue)]=\"startDate\" [disabled]=\"id.length>0\">\n                    <button class=\"input-group-addon\" [disabled]=\"id.length>0\" (click)=\"dp1.show()\" type=\"button\">\n                      <i class=\"fa fa-calendar\"></i>\n                    </button>\n                  </div>\n                </div>\n                <div class=\"form-group col-md-4 col-sm-6\">\n                  <label for=\"end\" class=\"col-form-label\">End Date</label>\n                  <div class=\"input-group\">\n                      <input type=\"text\" class=\"form-control\" #dp2=\"bsDatepicker\" bsDatepicker [minDate]=\"startDate\"  [(bsValue)]=\"endDate\" [disabled]=\"id.length>0\">\n                      <button class=\"input-group-addon\" [disabled]=\"id.length>0\" (click)=\"dp2.show()\" type=\"button\">\n                        <i class=\"fa fa-calendar\"></i>\n                      </button>\n                  </div>\n                </div>\n              </div>\n\n              <div class=\"row justify-content-center\">\n                <button type=\"submit\" class=\"btn btn-success\" [disabled]=\"(startDate==null||endDate==null)&&id.length==0\" (click)=\"searchEvents()\">Search TOR's</button>\n              </div>\n            </form>\n          </div>\n        </div>\n\n      </div>\n    </div>\n    <br/>\n    <br/>\n\n    <app-error-message [service-error]=\"torsError\" [loading]=\"loadingTors\"></app-error-message>\n\n    <div class=\"row\" *ngIf=\"this.events.length>0 && !loadingTors\">\n      <div class=\"col-md-12\">\n        <div class=\"card mb-3\">\n          <div class=\"card-header\">\n            <i class=\"fa fa-bell\"></i>\n            <strong>Tors with discrepances</strong>\n          </div>\n          <div class=\"card-body\">\n            <div class=\"row\">\n              <div class=\"clearfix col-sm-12\">\n                <div class=\"input-group float-right\" *ngIf=\"events.length>0\" style=\"width: 250px; height: 31px;\">\n                  <input type=\"text\" class=\"form-control\" placeholder=\"Search events...\" aria-label=\"Events search\" aria-describedby=\"basic-addon2\"\n                    [(ngModel)]=\"filterQuery\">\n                  <span class=\"input-group-addon\" id=\"basic-addon2\">\n                    <i class=\"fa fa-search\"></i>\n                  </span>\n                </div>\n\n              </div>\n            </div>\n            <div class=\"row\" *ngIf=\"!loadingTors\">\n              <div class=\"col-sm-12 table-responsive\">\n\n                <table width=\"100%\" class=\"table table-striped table-hover\" id=\"dataTables-example\" [wfmData]=\"events | customObjectPipe : filterQuery\"\n                  #wfm=\"wfmDataTable\" [wfmRowsOnPage]=\"rowsOnPage\" [(wfmSortBy)]=\"sortBy\" [(wfmSortOrder)]=\"sortOrder\">\n                  <thead class=\"thead-dark\">\n                    <tr>\n                      \n                      <th style=\"width:50px;\">\n                        <a (click)=\"selectAllEvents()\" *ngIf=\"events.length>1\">\n                            <i *ngIf=\"check==true\" class=\"fa fa-check-square-o \" aria-hidden=\"true\"></i>\n                            <i *ngIf=\"check==false\" class=\"fa fa-square-o \" aria-hidden=\"true\"></i>\n                        </a>\n                      </th>\n                      <th class=\"small\" style=\"width:50px;\"></th>\n                      <th class=\"small\">GTA ID</th>\n                      <th class=\"small\">WIN</th>\n                      <th class=\"small\">START DATE</th>\n                      <th class=\"small\">END DATE</th>\n                      <th class=\"small\">REASON</th>\n                      <th class=\"small\">STATUS</th>\n                      <th class=\"small\">STORE</th>\n                      <th class=\"small\">PROCESS</th>\n                      <th class=\"small\">LAST UPDATE</th>\n                      <th class=\"small\">ERROR</th>\n                    </tr>\n                  </thead>\n                  <tbody>\n                    <tr *ngFor=\"let item of wfm.data; let i = index\">\n                      <td>\n                        <input type=\"checkbox\" [(ngModel)]=\"item.check\" *ngIf=\"events.length>1\">\n                      </td>\n                      <td>\n                        <button type=\"button\" class=\"btn btn-primary btn-sm\" (click)=\"reprocessTor(item)\">\n                          <i class=\"fa fa-reply-all\" aria-hidden=\"true\"></i>\n                        </button>\n                      </td>\n                      <td class=\"small\">{{item.message.customObject.requestIdGta}}</td>\n                      <td class=\"small\">{{item.message.customObject.winNumber}}</td>\n                      <td class=\"small\">{{item.message.customObject.timeOffStart|utcDate| date:'dd/MM/yyyy HH:mm'}}</td>\n                      <td class=\"small\">{{item.message.customObject.timeOffEnd|utcDate| date:'dd/MM/yyyy HH:mm'}}</td>\n                      <td class=\"small\">{{item.message.customObject.timeOffReason}}</td>\n                      <td class=\"small\">{{item.message.customObject.status}}</td>\n                      <td class=\"small\">{{item.message.customObject.bussinessUnit}}</td>\n                      <td class=\"small\">{{item.message.customObject.processType}}</td>\n                      <td class=\"small\">{{item.message.customObject.lastUpdateTime |utcDate| date:'dd/MM/yyyy HH:mm'}}</td>\n                      <td class=\"small\">{{item.message.eventShortMsg}}</td>\n                    </tr>\n                  </tbody>\n                  <tfoot>\n                    <tr>\n                      <td colspan=\"12\">\n                        <wfmPaginator [rowsOnPageSet]=\"[5,10,15]\"></wfmPaginator>\n                      </td>\n                    </tr>\n                  </tfoot>\n                </table>\n                <div class=\"row justify-content-center\" *ngIf=\"events.length>1\">\n                    <button type=\"button  btn-danger\" class=\"btn btn-primary btn-danger\" (click)=\"reprocessTors()\">\n                      Reprocess Selected Tors\n                    </button>\n                  </div>\n              </div>\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/tor/tors/tors.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TorsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_tor_tor_service__ = __webpack_require__("../../../../../src/app/services/tor/tor.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_service_error__ = __webpack_require__("../../../../../src/app/models/service-error.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__directives_custom_object_pipe_pipe__ = __webpack_require__("../../../../../src/app/directives/custom-object-pipe.pipe.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var TorsComponent = (function () {
    function TorsComponent(torService) {
        this.torService = torService;
        this.id = "";
        this.filterQuery = "";
        this.rowsOnPage = 5;
        this.sortBy = "item.message.customObject.winNumber";
        this.sortOrder = "asc";
        this.customObjectPipePipe = new __WEBPACK_IMPORTED_MODULE_3__directives_custom_object_pipe_pipe__["a" /* CustomObjectPipePipe */]();
    }
    TorsComponent.prototype.ngOnInit = function () {
        this.events = [];
        this.torsError = undefined;
        this.check = false;
        this.startDate = this.getToday();
        this.endDate = this.getToday();
    };
    TorsComponent.prototype.getToday = function () {
        var date = new Date();
        return new Date(date.getFullYear(), date.getMonth(), date.getDate());
    };
    TorsComponent.prototype.searchEvents = function () {
        var _this = this;
        this.events = [];
        this.loadingTors = true;
        this.torsError = undefined;
        this.torService.getEventsByIdAndDate(this.id, this.startDate, this.endDate).subscribe(function (data) {
            var events = data.filter(function (event) { return event.message.customObject != null; });
            if (events.length > 0) {
                _this.events = events;
            }
            else {
                _this.events = [];
                _this.torsError = new __WEBPACK_IMPORTED_MODULE_2__models_service_error__["a" /* ServiceError */]("Events not found", "Try with a different period of time");
            }
            _this.loadingTors = false;
        }, function (err) {
            console.debug(err);
            _this.torsError = err;
            _this.loadingTors = false;
        });
    };
    TorsComponent.prototype.reprocessTor = function (event) {
        var _this = this;
        this.loadingTors = true;
        this.torService.reprocesTor(event).subscribe(function (correct) {
            _this.torService.deleteEvent(event).subscribe(function (correct) { _this.filterQuery = ""; setTimeout(function () { return _this.searchEvents(); }, 500); }, function (error) { _this.torsError = error; _this.loadingTors = false; });
        }, function (error) { _this.torsError = error; _this.loadingTors = false; });
    };
    TorsComponent.prototype.reprocessTors = function () {
        var _this = this;
        this.loadingTors = true;
        var events = this.customObjectPipePipe.transform(this.events, this.filterQuery);
        var eventList = [];
        for (var _i = 0, events_1 = events; _i < events_1.length; _i++) {
            var event_1 = events_1[_i];
            if (event_1.check) {
                eventList.push(event_1.id);
            }
        }
        this.torService.reprocesTors(eventList).subscribe(function (correct) { _this.check = false; _this.filterQuery = ""; setTimeout(function () { return _this.searchEvents(); }, 900); }, function (error) { _this.torsError = error; _this.loadingTors = false; });
    };
    TorsComponent.prototype.selectAllEvents = function () {
        this.check = !this.check;
        for (var _i = 0, _a = this.events; _i < _a.length; _i++) {
            var event_2 = _a[_i];
            event_2.check = this.check;
        }
    };
    TorsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'app-tors',
            template: __webpack_require__("../../../../../src/app/pages/tor/tors/tors.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/tor/tors/tors.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_tor_tor_service__["a" /* TorService */]])
    ], TorsComponent);
    return TorsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/services/api-request.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ApiRequestService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs__ = __webpack_require__("../../../../rxjs/Rx.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__api_user_info_service__ = __webpack_require__("../../../../../src/app/services/api/user-info.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_service_error__ = __webpack_require__("../../../../../src/app/models/service-error.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var ApiRequestService = (function () {
    function ApiRequestService(http, router, userInfoService) {
        this.http = http;
        this.router = router;
        this.userInfoService = userInfoService;
        this.BASE = "..";
    }
    /**
     * This is a Global place to add all the request headers for every REST calls
     */
    ApiRequestService.prototype.appendAuthHeader = function () {
        var token = this.userInfoService.getStoredToken();
        if (token !== null) {
            var headers = new __WEBPACK_IMPORTED_MODULE_5__angular_common_http__["c" /* HttpHeaders */]()
                .append('Content-Type', 'application/json')
                .append('Authorization', token);
            return headers;
        }
    };
    /**
     * This is a Global place to define all the Request Headers that must be sent for every ajax call
     */
    ApiRequestService.prototype.getRequestOptions = function (requestMethod, url, urlParam, body) {
        var options = {
            headers: this.appendAuthHeader(),
            params: urlParam,
            body: body,
            responseType: 'json'
        };
        console.log('Requesting:', options);
        var request = new __WEBPACK_IMPORTED_MODULE_5__angular_common_http__["e" /* HttpRequest */](requestMethod, url, options);
        return request;
    };
    ApiRequestService.prototype.get = function (url, urlParams) {
        var me = this;
        var opts = {
            headers: this.appendAuthHeader(),
            params: urlParams
        };
        return this.http.get(this.BASE + url, opts)
            .map(function (resp) { return resp; })
            .catch(function (error) {
            if (error.status === 403 || error.status == 401) {
                me.router.navigate(['/logout']);
            }
            return me.errorHandler(error);
        });
    };
    ApiRequestService.prototype.post = function (url, body) {
        var me = this;
        var opts = { headers: this.appendAuthHeader() };
        return this.http.post(this.BASE + url, body, opts)
            .map(function (resp) { return resp; })
            .catch(function (error) {
            if (error.status === 403 || error.status == 401) {
                me.router.navigate(['/logout']);
            }
            return me.errorHandler(error);
        });
    };
    ApiRequestService.prototype.put = function (url, body) {
        var me = this;
        var opts = { headers: this.appendAuthHeader() };
        return this.http.put(this.BASE + url, body, opts)
            .map(function (resp) { return resp; })
            .catch(function (error) {
            if (error.status === 403 || error.status == 401) {
                me.router.navigate(['/logout']);
            }
            return me.errorHandler(error);
        });
    };
    ApiRequestService.prototype.delete = function (url, urlParams) {
        var me = this;
        var opts = { headers: this.appendAuthHeader() };
        return this.http.delete(this.BASE + url, opts)
            .map(function (resp) { return resp; })
            .catch(function (error) {
            if (error.status === 403 || error.status == 401) {
                me.router.navigate(['/logout']);
            }
            return me.errorHandler(error);
        });
    };
    ApiRequestService.prototype.errorHandler = function (error) {
        if (error.error.message != undefined) {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            return __WEBPACK_IMPORTED_MODULE_2_rxjs__["Observable"].throw(new __WEBPACK_IMPORTED_MODULE_4__models_service_error__["a" /* ServiceError */](error.error.message, error.error.developerMessage));
        }
        else {
            // A client-side or network error occurred. Handle it accordingly.
            return __WEBPACK_IMPORTED_MODULE_2_rxjs__["Observable"].throw(new __WEBPACK_IMPORTED_MODULE_4__models_service_error__["a" /* ServiceError */](error.statusText, error.message));
        }
    };
    ApiRequestService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_5__angular_common_http__["a" /* HttpClient */],
            __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* Router */],
            __WEBPACK_IMPORTED_MODULE_3__api_user_info_service__["a" /* UserInfoService */]])
    ], ApiRequestService);
    return ApiRequestService;
}());



/***/ }),

/***/ "../../../../../src/app/services/api/auth-guard.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthGuardService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__user_info_service__ = __webpack_require__("../../../../../src/app/services/api/user-info.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__login_service__ = __webpack_require__("../../../../../src/app/services/api/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AuthGuardService = (function () {
    function AuthGuardService(router, loginService, userInfoService) {
        this.router = router;
        this.loginService = loginService;
        this.userInfoService = userInfoService;
    }
    AuthGuardService.prototype.canActivate = function (route, state) {
        var url = window.document.URL;
        return this.checkLogin(url);
    };
    AuthGuardService.prototype.canActivateChild = function (route, state) {
        return this.canActivate(route, state);
    };
    AuthGuardService.prototype.checkLogin = function (url) {
        var _this = this;
        console.log("validating  URL: " + url);
        if (this.userInfoService.isLoggedIn()) {
            return true;
        }
        else {
            this.loginService.getToken(url).subscribe(function (data) {
                _this.router.navigate(['tor/dashboard/events']);
                return true;
            }, function (error) {
                _this.userInfoService.removeUserInfo();
                _this.router.navigate(['logout']);
                return false;
            });
        }
    };
    AuthGuardService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */],
            __WEBPACK_IMPORTED_MODULE_2__login_service__["a" /* LoginService */],
            __WEBPACK_IMPORTED_MODULE_1__user_info_service__["a" /* UserInfoService */]])
    ], AuthGuardService);
    return AuthGuardService;
}());



/***/ }),

/***/ "../../../../../src/app/services/api/login.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs__ = __webpack_require__("../../../../rxjs/Rx.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__user_info_service__ = __webpack_require__("../../../../../src/app/services/api/user-info.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var LoginService = (function () {
    function LoginService(router, http, userInfoService, apiRequest) {
        this.router = router;
        this.http = http;
        this.userInfoService = userInfoService;
        this.apiRequest = apiRequest;
        this.landingPage = "/tor/dashboard/events";
    }
    LoginService.prototype.parseAuthUrl = function (url) {
        var values = url.split("?");
        if (values.length > 1) {
            var queryParams = values[1].split("&");
            var params = {};
            for (var param in queryParams) {
                var qp = queryParams[param].split("=");
                params[qp[0]] = qp[1];
            }
            return params;
        }
        else {
            return null;
        }
    };
    LoginService.prototype.getToken = function (url) {
        var _this = this;
        var me = this;
        var params = this.parseAuthUrl(url);
        var loginDataSubject = new __WEBPACK_IMPORTED_MODULE_2_rxjs__["Subject"](); // Will use this subject to emit data that we want after ajax login attempt
        var loginInfoReturn; // Object that we want to send back to Login Page
        if (params != null) {
            this.apiRequest.post('/token', params)
                .subscribe(function (jsonResp) {
                if (jsonResp !== undefined && jsonResp !== null && jsonResp.operationStatus === "SUCCESS") {
                    //Create a success object that we want to send back to login page
                    loginInfoReturn = {
                        "success": true,
                        "message": jsonResp.operationMessage,
                        "landingPage": _this.landingPage,
                        "user": {
                            "userId": jsonResp.item.userId,
                            "email": jsonResp.item.emailAddress,
                            "displayName": jsonResp.item.fullName,
                            "token": jsonResp.item.token,
                        }
                    };
                    // store username and jwt token in session storage to keep user logged in between page refreshes
                    _this.userInfoService.storeUserInfo(JSON.stringify(loginInfoReturn.user));
                }
                else {
                    //Create a faliure object that we want to send back to login page
                    loginInfoReturn = {
                        "success": false,
                        "message": jsonResp.msgDesc,
                        "landingPage": "/logout"
                    };
                    console.error(loginInfoReturn);
                }
                loginDataSubject.next(loginInfoReturn);
            }, function (error) {
                loginDataSubject.error(error);
            });
        }
        else {
            loginDataSubject.error("Code is not set.");
            this.logout();
        }
        return loginDataSubject;
    };
    LoginService.prototype.logout = function (navigatetoLogout) {
        if (navigatetoLogout === void 0) { navigatetoLogout = true; }
        // clear token remove user from local storage to log user out
        this.userInfoService.removeUserInfo();
        if (navigatetoLogout) {
            this.router.navigate(["logout"]);
        }
    };
    LoginService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* Router */],
            __WEBPACK_IMPORTED_MODULE_7__angular_common_http__["a" /* HttpClient */],
            __WEBPACK_IMPORTED_MODULE_5__user_info_service__["a" /* UserInfoService */],
            __WEBPACK_IMPORTED_MODULE_6__api_request_service__["a" /* ApiRequestService */]])
    ], LoginService);
    return LoginService;
}());



/***/ }),

/***/ "../../../../../src/app/services/api/menu.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MenuService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var MenuService = (function () {
    function MenuService(apiRequest) {
        this.apiRequest = apiRequest;
    }
    ;
    MenuService.prototype.getUserMenu = function () {
        return this.apiRequest.get("/menu");
    };
    MenuService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__api_request_service__["a" /* ApiRequestService */]])
    ], MenuService);
    return MenuService;
}());



/***/ }),

/***/ "../../../../../src/app/services/api/user-info.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserInfoService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var UserInfoService = (function () {
    function UserInfoService() {
        this.currentUserKey = "currentUser";
        this.storage = sessionStorage; // <--- you may switch between sessionStorage or LocalStrage (only one place to change)
    }
    //Store userinfo from session storage
    UserInfoService.prototype.storeUserInfo = function (userInfoString) {
        this.storage.setItem(this.currentUserKey, userInfoString);
    };
    //Remove userinfo from session storage
    UserInfoService.prototype.removeUserInfo = function () {
        this.storage.removeItem(this.currentUserKey);
    };
    //Get userinfo from session storage
    UserInfoService.prototype.getUserInfo = function () {
        try {
            var userInfoString = this.storage.getItem(this.currentUserKey);
            if (userInfoString) {
                var userObj = JSON.parse(this.storage.getItem(this.currentUserKey));
                return userObj;
            }
            else {
                return null;
            }
        }
        catch (e) {
            return null;
        }
    };
    UserInfoService.prototype.isLoggedIn = function () {
        return this.storage.getItem(this.currentUserKey) ? true : false;
    };
    UserInfoService.prototype.getUserId = function () {
        var userObj = this.getUserInfo();
        if (userObj !== null) {
            var userId = userObj.userId.split("\\")[1];
            return userId;
        }
        return "Anonymus";
    };
    //Get User's Display name from session storage
    UserInfoService.prototype.getUserName = function () {
        var userObj = this.getUserInfo();
        if (userObj !== null) {
            return userObj.displayName;
        }
        return "no-user";
    };
    UserInfoService.prototype.getStoredToken = function () {
        var userObj = this.getUserInfo();
        if (userObj !== null) {
            return userObj.token;
        }
        return null;
    };
    UserInfoService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [])
    ], UserInfoService);
    return UserInfoService;
}());



/***/ }),

/***/ "../../../../../src/app/services/associate/associate-enpoints.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateEnpoints; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");

var BASE = "/api/v1";
var SWAGGER = "/swagger.json";
var RP_INSTANCES = "/rpInstances";
var RP_INSTANCE = "/rpInstance";
var EVENTS = "/events";
var EVENT = "/event";
var EVENT_TYPE = "/eventType";
var APP_NAME = "/appName";
var REPROCESS = "/reprocess";
var STORES = "/stores";
var STORE = "/store";
var PILOT_STORES = "/pilotTypes";
var AssociateEnpoints = (function () {
    function AssociateEnpoints() {
    }
    AssociateEnpoints.prototype.getSwaggerJsonEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + SWAGGER;
    };
    AssociateEnpoints.prototype.getRpInstancesEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES;
    };
    AssociateEnpoints.prototype.getEventsEndpoint = function (type, appName) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + EVENTS + EVENT_TYPE + '/' + type + APP_NAME + '/' + appName;
    };
    AssociateEnpoints.prototype.getReprocessEventsEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + REPROCESS + EVENTS;
    };
    AssociateEnpoints.prototype.getReprocessEventEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + REPROCESS + EVENT;
    };
    AssociateEnpoints.prototype.getAssociateStoresEndpoint = function (rpInstance) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + '/' + STORES;
    };
    AssociateEnpoints.prototype.getStoresByRpInstanceEndpoint = function (rpInstance) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + STORES;
    };
    AssociateEnpoints.prototype.getStoreInfoEndpoint = function (rpInstance, storeNumber) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + STORES + STORE + '/' + storeNumber;
    };
    AssociateEnpoints.prototype.getPilotTypesByStoreEndpoint = function (rpInstance, storeNumber) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + STORES + '/' + storeNumber + PILOT_STORES;
    };
    AssociateEnpoints.prototype.createStoreEndpoint = function (rpInstance) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + STORES;
    };
    AssociateEnpoints.prototype.updateStoreEndpoint = function (rpInstance, storeNumber) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + STORES + '/' + storeNumber;
    };
    AssociateEnpoints.prototype.updateStorePilotType = function (rpInstance) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].associateContext + BASE + RP_INSTANCES + '/' + rpInstance + STORES;
    };
    return AssociateEnpoints;
}());



/***/ }),

/***/ "../../../../../src/app/services/associate/associate-reprocess.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateReprocessService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__associate_enpoints__ = __webpack_require__("../../../../../src/app/services/associate/associate-enpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var EVENT_TYPE = "error";
var APP_NAME = "associate";
var AssociateReprocessService = (function () {
    function AssociateReprocessService(apiRequest, endpoints) {
        this.apiRequest = apiRequest;
        this.endpoints = endpoints;
    }
    ;
    AssociateReprocessService.prototype.getErrorEvents = function (id, startDate, endDate) {
        var DAY_CONSTANT = 3600 * 24 * 1000;
        var elasticId = (id == null || id == undefined) ? "" : id;
        var start = startDate.getTime();
        var end = endDate.getTime() + DAY_CONSTANT;
        var endpoint = this.endpoints.getEventsEndpoint(EVENT_TYPE, APP_NAME);
        var params = new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["d" /* HttpParams */]().set("id", id).set("startDate", start + "").set("endDate", end + "");
        return this.apiRequest.get(endpoint, params);
    };
    AssociateReprocessService.prototype.reprocessEvents = function (events) {
        var endpoint = this.endpoints.getReprocessEventsEndpoint();
        return this.apiRequest.post(endpoint, events);
    };
    AssociateReprocessService.prototype.reprocessEvent = function (event) {
        var endpoint = this.endpoints.getReprocessEventEndpoint();
        return this.apiRequest.post(endpoint, event);
    };
    AssociateReprocessService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__api_request_service__["a" /* ApiRequestService */], __WEBPACK_IMPORTED_MODULE_3__associate_enpoints__["a" /* AssociateEnpoints */]])
    ], AssociateReprocessService);
    return AssociateReprocessService;
}());



/***/ }),

/***/ "../../../../../src/app/services/associate/associate-rp-instances.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateRpInstancesService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__associate_enpoints__ = __webpack_require__("../../../../../src/app/services/associate/associate-enpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AssociateRpInstancesService = (function () {
    function AssociateRpInstancesService(apiRequest, endpoints) {
        this.apiRequest = apiRequest;
        this.endpoints = endpoints;
    }
    AssociateRpInstancesService.prototype.getRpInstances = function () {
        return this.apiRequest.get(this.endpoints.getRpInstancesEndpoint());
    };
    AssociateRpInstancesService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__api_request_service__["a" /* ApiRequestService */], __WEBPACK_IMPORTED_MODULE_2__associate_enpoints__["a" /* AssociateEnpoints */]])
    ], AssociateRpInstancesService);
    return AssociateRpInstancesService;
}());



/***/ }),

/***/ "../../../../../src/app/services/associate/associate-stores.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AssociateStoresService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__associate_enpoints__ = __webpack_require__("../../../../../src/app/services/associate/associate-enpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AssociateStoresService = (function () {
    function AssociateStoresService(apiRequest, endpoints) {
        this.apiRequest = apiRequest;
        this.endpoints = endpoints;
    }
    AssociateStoresService.prototype.getStoresByRpInstance = function (rpInstance) {
        return this.apiRequest.get(this.endpoints.getStoresByRpInstanceEndpoint(rpInstance));
    };
    AssociateStoresService.prototype.getStoreByRpInstanceAndStoreNumber = function (rpInstance, storeNumber) {
        return this.apiRequest.get(this.endpoints.getStoreInfoEndpoint(rpInstance, storeNumber));
    };
    AssociateStoresService.prototype.addNewStore = function (rpInstance, store) {
        var endpoint = this.endpoints.createStoreEndpoint(rpInstance);
        return this.apiRequest.post(endpoint, store);
    };
    AssociateStoresService.prototype.upadteStore = function (rpInstance, store) {
        var endpoint = this.endpoints.updateStoreEndpoint(rpInstance, store.storeNumber);
        return this.apiRequest.put(endpoint, store);
    };
    AssociateStoresService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__api_request_service__["a" /* ApiRequestService */], __WEBPACK_IMPORTED_MODULE_2__associate_enpoints__["a" /* AssociateEnpoints */]])
    ], AssociateStoresService);
    return AssociateStoresService;
}());



/***/ }),

/***/ "../../../../../src/app/services/tor/maintenance.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MaintenanceService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__tor_endpoints__ = __webpack_require__("../../../../../src/app/services/tor/tor-endpoints.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_service_error__ = __webpack_require__("../../../../../src/app/models/service-error.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_week_days_enum__ = __webpack_require__("../../../../../src/app/models/week-days.enum.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_maintenance_builder__ = __webpack_require__("../../../../../src/app/models/maintenance-builder.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var MaintenanceService = (function () {
    function MaintenanceService(apiRequest, endpoints) {
        this.apiRequest = apiRequest;
        this.endpoints = endpoints;
    }
    //trasnform maintenance to weekday bootstrap component
    MaintenanceService.prototype.assignMaintenance = function (maintenances, actions) {
        var events = [];
        for (var _i = 0, maintenances_1 = maintenances; _i < maintenances_1.length; _i++) {
            var maintenance = maintenances_1[_i];
            events.push(new __WEBPACK_IMPORTED_MODULE_5__models_maintenance_builder__["a" /* MaintenanceDayBuilder */]().buildMaintenanceDayView(maintenance, actions).event);
        }
        return events;
    };
    //obtain all days of week
    MaintenanceService.prototype.getWeekDays = function () {
        var dias;
        dias = [];
        var numbers = [1, 2, 3, 4, 5, 6, 7];
        for (var _i = 0, numbers_1 = numbers; _i < numbers_1.length; _i++) {
            var num = numbers_1[_i];
            dias.push(__WEBPACK_IMPORTED_MODULE_4__models_week_days_enum__["a" /* WeekDays */][num]);
        }
        return dias;
    };
    //create errors for the view
    MaintenanceService.prototype.createError = function (message, body) {
        return new __WEBPACK_IMPORTED_MODULE_3__models_service_error__["a" /* ServiceError */](message, JSON.stringify(body));
    };
    //service to obtain maintenances by  market
    MaintenanceService.prototype.getMaintenanceByMarket = function (market) {
        return this.apiRequest.get(this.endpoints.getMaintenanceByMarket(market));
    };
    //update or create maintenance
    MaintenanceService.prototype.upsertMaintenance = function (maintenance) {
        return this.apiRequest.post(this.endpoints.upsertMaintenance(), maintenance);
    };
    //delete maintenance
    MaintenanceService.prototype.deletetMaintenance = function (maintenance) {
        return this.apiRequest.post(this.endpoints.deleteMaintenance(), new __WEBPACK_IMPORTED_MODULE_5__models_maintenance_builder__["a" /* MaintenanceDayBuilder */]().buildMaintenanceDayDelete(maintenance).maintenance);
    };
    MaintenanceService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__api_request_service__["a" /* ApiRequestService */], __WEBPACK_IMPORTED_MODULE_2__tor_endpoints__["a" /* TorEndpoints */]])
    ], MaintenanceService);
    return MaintenanceService;
}());



/***/ }),

/***/ "../../../../../src/app/services/tor/markets.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MarketsService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__tor_endpoints__ = __webpack_require__("../../../../../src/app/services/tor/tor-endpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var MarketsService = (function () {
    function MarketsService(apiRequest, endpoints) {
        this.apiRequest = apiRequest;
        this.endpoints = endpoints;
    }
    MarketsService.prototype.getMarkets = function () {
        return this.apiRequest.get(this.endpoints.getMarketsEndpoint());
    };
    MarketsService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__api_request_service__["a" /* ApiRequestService */], __WEBPACK_IMPORTED_MODULE_2__tor_endpoints__["a" /* TorEndpoints */]])
    ], MarketsService);
    return MarketsService;
}());



/***/ }),

/***/ "../../../../../src/app/services/tor/stores.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StoresService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__tor_endpoints__ = __webpack_require__("../../../../../src/app/services/tor/tor-endpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var StoresService = (function () {
    function StoresService(apiRequest, endpoints) {
        this.apiRequest = apiRequest;
        this.endpoints = endpoints;
    }
    StoresService.prototype.getStores = function () {
        return this.apiRequest.get(this.endpoints.getStoresEndpoint());
    };
    StoresService.prototype.getStoresByMarket = function (market) {
        return this.apiRequest.get(this.endpoints.getStoresByMarketEndpoint(market));
    };
    StoresService.prototype.loadStores = function (file) {
        var formData = new FormData();
        formData.append('file', file);
        return this.apiRequest.post(this.endpoints.loadStoresEndpoint(), formData);
    };
    StoresService.prototype.upsertStore = function (store) {
        return this.apiRequest.put(this.endpoints.upsertStoreEndpoint(), store);
    };
    StoresService.prototype.validateStore = function (store) {
        return this.apiRequest.get(this.endpoints.getStoreValidationByMarket(store.market, store.storeNumberGta));
    };
    StoresService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__api_request_service__["a" /* ApiRequestService */], __WEBPACK_IMPORTED_MODULE_2__tor_endpoints__["a" /* TorEndpoints */]])
    ], StoresService);
    return StoresService;
}());



/***/ }),

/***/ "../../../../../src/app/services/tor/tor-endpoints.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TorEndpoints; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");

var TorEndpoints = (function () {
    function TorEndpoints() {
        this.STORES = '/stores';
        this.STORE = '/store';
        this.MARKETS = '/markets';
        this.MARKET = '/market';
        this.TIME_OFF_REQUESTS = '/timeOffRequests';
        this.TIME_OFF_REQUEST = '/timeOffRequest';
        this.VALIDATION = '/validation';
        this.MAINTENANCE = '/serverAvailability';
        this.UPLOAD = '/upload';
        this.DELETE = '/delete';
        this.REPROCESS = '/reprocess';
        this.EVENTS = '/events';
        this.LIST = '/list';
    }
    TorEndpoints.prototype.getMarketsEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.MARKETS;
    };
    TorEndpoints.prototype.loadStoresEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.UPLOAD + this.STORES;
    };
    TorEndpoints.prototype.upsertStoreEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.STORES + this.STORE;
    };
    TorEndpoints.prototype.getStoresEndpoint = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.STORES;
    };
    TorEndpoints.prototype.getStoresByMarketEndpoint = function (market) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.STORES + this.MARKET + '/' + market;
    };
    TorEndpoints.prototype.getStoreValidationByMarket = function (market, storeNumberGta) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.STORES + this.STORE + '/' + storeNumberGta + this.MARKET + '/' + market + this.VALIDATION;
    };
    TorEndpoints.prototype.getMaintenanceByMarket = function (market) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.MAINTENANCE + this.MARKET + '/' + market;
    };
    TorEndpoints.prototype.upsertMaintenance = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.MAINTENANCE;
    };
    TorEndpoints.prototype.deleteMaintenance = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.MAINTENANCE + this.DELETE;
    };
    TorEndpoints.prototype.getEventsByIdOrDate = function (id, start, end) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.EVENTS;
    };
    TorEndpoints.prototype.reprocessTor = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.TIME_OFF_REQUESTS + this.TIME_OFF_REQUEST + this.REPROCESS;
    };
    TorEndpoints.prototype.reprocessTors = function () {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.TIME_OFF_REQUESTS + this.TIME_OFF_REQUEST + this.REPROCESS + this.LIST;
    };
    TorEndpoints.prototype.deleteEvent = function (elasticId, elasticIndex) {
        return __WEBPACK_IMPORTED_MODULE_0__environments_environment__["a" /* environment */].torContext + this.EVENTS + '/' + elasticIndex + '/' + elasticId;
    };
    return TorEndpoints;
}());



/***/ }),

/***/ "../../../../../src/app/services/tor/tor.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TorService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__api_request_service__ = __webpack_require__("../../../../../src/app/services/api-request.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__tor_endpoints__ = __webpack_require__("../../../../../src/app/services/tor/tor-endpoints.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var TorService = (function () {
    function TorService(endpoints, apiRequest) {
        this.endpoints = endpoints;
        this.apiRequest = apiRequest;
    }
    TorService_1 = TorService;
    TorService.prototype.getEventsByIdAndDate = function (id, startDate, endDate) {
        var DAY_CONSTANT = 3600 * 24 * 1000;
        var elasticId = (id == null || id == undefined) ? "" : id;
        var start = startDate.getTime();
        var end = endDate.getTime() + DAY_CONSTANT;
        var endpoint = this.endpoints.getEventsByIdOrDate(elasticId, start, end);
        var params = new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["d" /* HttpParams */]().set("id", id).set("initDate", start + "").set("endDate", end + "");
        return this.apiRequest.get(this.endpoints.getEventsByIdOrDate(elasticId, start, end), params);
    };
    TorService.prototype.reprocesTor = function (event) {
        var idElastic = event.message.customObject.market + event.message.customObject.requestIdGta + event.message.customObject.status;
        console.info('Reprocessing : ' + idElastic);
        return this.apiRequest.post(this.endpoints.reprocessTor(), idElastic);
    };
    TorService.prototype.reprocesTors = function (events) {
        return this.apiRequest.post(this.endpoints.reprocessTors(), events);
    };
    TorService.prototype.deleteEvent = function (event) {
        return this.apiRequest.delete(this.endpoints.deleteEvent(event.id, TorService_1.elasticIndex));
    };
    TorService.dateFormatElastic = 'YYYY-MM-DDTHH:mm:ss';
    TorService.elasticIndex = 'events';
    TorService = TorService_1 = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__tor_endpoints__["a" /* TorEndpoints */],
            __WEBPACK_IMPORTED_MODULE_2__api_request_service__["a" /* ApiRequestService */]])
    ], TorService);
    return TorService;
    var TorService_1;
}());



/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
var environment = {
    production: true,
    torContext: "/tor-services",
    associateContext: "/associate-services",
    torsEventsDashboard: "http://kibana.prod2.gwfm-alerting.bopwm.dal3.prod.walmart.com:5601/app/kibana#/dashboard/TOR-topology?embed=true&_g=(refreshInterval:(display:Off,pause:!f,value:0),time:(from:now-7d,mode:quick,to:now))&_a=(filters:!(),options:(darkTheme:!f),panels:!((col:4,id:TORs-processed-by-market,panelIndex:1,row:12,size_x:4,size_y:4,type:visualization),(col:1,id:TORs-processed-daily,panelIndex:3,row:3,size_x:6,size_y:3,type:visualization),(col:1,id:TORs-processed,panelIndex:4,row:1,size_x:12,size_y:2,type:visualization),(col:8,id:TORs-processed-market-slash-status,panelIndex:9,row:8,size_x:5,size_y:4,type:visualization),(col:1,id:TOR-daily-errors,panelIndex:12,row:16,size_x:8,size_y:4,type:visualization),(col:8,id:TOR-total-time-median,panelIndex:14,row:12,size_x:5,size_y:4,type:visualization),(col:1,id:TOR-Process-Type,panelIndex:15,row:8,size_x:3,size_y:2,type:visualization),(col:4,id:TORs-process-time-by-range,panelIndex:17,row:8,size_x:4,size_y:4,type:visualization),(col:1,id:TORs-processed-USA,panelIndex:21,row:10,size_x:3,size_y:2,type:visualization),(col:1,id:TORs-processed-for-Canada,panelIndex:22,row:12,size_x:3,size_y:2,type:visualization),(col:4,id:TOR-process-time-calculations-overview,panelIndex:27,row:6,size_x:9,size_y:2,type:visualization),(col:1,id:TOR-event-priority-chart,panelIndex:28,row:14,size_x:3,size_y:2,type:visualization),(col:7,id:TOR-tors-processed-hourly,panelIndex:29,row:3,size_x:6,size_y:3,type:visualization),(col:1,id:TOR-eventtype-piechart,panelIndex:30,row:6,size_x:3,size_y:2,type:visualization),(col:9,id:TOR-errors-by-originIP,panelIndex:31,row:16,size_x:4,size_y:4,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'TOR%20topology',uiState:(P-1:(vis:(colors:(APPROVED:%23508642,CA:%230A437C,CANCELLED_AFTER_APPROVED:%23EA6460,US:%2382B5D8))),P-12:(vis:(legendOpen:!t)),P-14:(spy:(mode:(fill:!f,name:!n)),vis:(colors:('50th%20percentile%20of%20customObject.processTotalTime':%23E5AC0E,'9,096.757':%237EB26D,'Maximum%20total%20time':%23BF1B00,'Maximum%20total%20time%20%5Bseconds%5D':%23BF1B00,'Minimum%20total%20time%20%5Bseconds%5D':%23508642),legendOpen:!t)),P-15:(vis:(colors:(NORMAL:%23447EBC,'New%20Store':%23447EBC,Normal:%237EB26D,Reprocess:%23EAB839))),P-17:(vis:(colors:('Number%20of%20TORs':%23806EB7),legendOpen:!f)),P-26:(vis:(colors:(error:%23890F02,'info%20processed':%23508642))),P-28:(vis:(colors:(Important:%23F2C96D,Major:%23E0752D,Minor:%239AC48A))),P-3:(vis:(colors:('TORs%20Count':%23614D93),legendOpen:!t)),P-31:(spy:(mode:(fill:!f,name:!n)),vis:(colors:('Nr%20of%20errors':%23E24D42),legendOpen:!t))))",
    torsTopologyDashboard: "http://kibana.prod2.gwfm-alerting.bopwm.dal3.prod.walmart.com:5601/app/kibana#/dashboard/TORDATA-dashboard?embed=true&_g=(refreshInterval:(display:Off,pause:!f,value:0),time:(from:now-7d,mode:quick,to:now))&_a=(filters:!(),options:(darkTheme:!f),panels:!((col:1,id:TORDATA-main-metric,panelIndex:1,row:1,size_x:8,size_y:2,type:visualization),(col:4,id:TORDATA-piechart-country,panelIndex:2,row:3,size_x:3,size_y:2,type:visualization),(col:1,id:TORDATA-piechart-process-type,panelIndex:3,row:5,size_x:3,size_y:2,type:visualization),(col:4,id:TORDATA-process-time-calculations-metric,panelIndex:4,row:5,size_x:9,size_y:2,type:visualization),(col:7,id:TORDATA-processed-and-process-type-table,panelIndex:5,row:7,size_x:6,size_y:9,type:visualization),(col:9,id:TORDATA-processed-metric,panelIndex:6,row:1,size_x:4,size_y:2,type:visualization),(col:1,id:TORDATA-reason,panelIndex:7,row:3,size_x:3,size_y:2,type:visualization),(col:1,id:TORDATA-status-and-reason-table,panelIndex:8,row:7,size_x:6,size_y:9,type:visualization),(col:10,id:TORDATA-US-metric,panelIndex:9,row:3,size_x:3,size_y:2,type:visualization),(col:7,id:TORDATA-canada-metric,panelIndex:11,row:3,size_x:3,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'TORDATA%20dashboard',uiState:())",
    torsStoresDashboard: "http://kibana.prod2.gwfm-alerting.bopwm.dal3.prod.walmart.com:5601/app/kibana#/dashboard/TOR-Stores?embed=true&_g=(refreshInterval:(display:Off,pause:!f,value:0),time:(from:now-1y%2Fy,mode:quick,to:now-1y%2Fy))&_a=(filters:!(),options:(darkTheme:!f),panels:!((col:10,id:TOR-stores-by-market-piechart,panelIndex:8,row:7,size_x:3,size_y:3,type:visualization),(col:1,id:Stores-created-successfully,panelIndex:11,row:1,size_x:12,size_y:3,type:visualization),(col:1,id:TOR-STORE-eventtype-piechart,panelIndex:13,row:4,size_x:3,size_y:3,type:visualization),(col:1,id:TOR-STORE-processTime-metric,panelIndex:14,row:10,size_x:5,size_y:3,type:visualization),(col:1,id:TOR-STORE-tors-for-CA-metric,panelIndex:15,row:13,size_x:6,size_y:2,type:visualization),(col:4,id:TOR-STORES-timeline-for-successfully-created-stores,panelIndex:16,row:4,size_x:9,size_y:3,type:visualization),(col:1,id:TOR-STORES-timeline-for-the-sum-of-tors,panelIndex:17,row:7,size_x:9,size_y:3,type:visualization),(col:6,id:TOR-STORES-timeline-of-processTime,panelIndex:18,row:10,size_x:7,size_y:3,type:visualization),(col:1,id:TOR-STORE-datatable-for-each-store,panelIndex:20,row:15,size_x:8,size_y:5,type:visualization),(col:7,id:TOR-STORE-tors-for-US-metric,panelIndex:21,row:13,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'TOR%20Stores',uiState:(P-16:(vis:(colors:('Successfully%20created%20stores':%23508642)))))",
    healthcheckElasticDashboard: "http://elasticsearch.prod2.gwfm-alerting.bopwm.glb.prod.walmart.com:9200/_plugin/kopf/#!/cluster",
    hostUrl: "https://interfaces-support.support-ui-prod.interfaces-support-ui.bopwm.qa.walmart.com/",
    ssoLoginUrl: "https://idp.prod.sso.platform.prod.walmart.com/platform-sso-server/authorize?response_type=code&scope=openid&client_id=173f9732-8cc4-4159-991b-ef1afb544368",
    ssoLogoutUrl: "https://idp.prod.sso.platform.prod.walmart.com/platform-sso-server/ppidp/logout"
};


/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/esm5/platform-browser-dynamic.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["enableProdMode"])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ "../../../../../src/polyfills.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "swaggerUIBundle", function() { return swaggerUIBundle; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "swaggerUIStandalonePreset", function() { return swaggerUIStandalonePreset; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_core_js_es7_reflect__ = __webpack_require__("../../../../core-js/es7/reflect.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_core_js_es7_reflect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_core_js_es7_reflect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_swagger_ui_dist__ = __webpack_require__("../../../../swagger-ui-dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_swagger_ui_dist___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_swagger_ui_dist__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_zone_js_dist_zone__ = __webpack_require__("../../../../zone.js/dist/zone.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_zone_js_dist_zone___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_zone_js_dist_zone__);
/**
 * This file includes polyfills needed by Angular and is loaded before the app.
 * You can add your own extra polyfills to this file.
 *
 * This file is divided into 2 sections:
 *   1. Browser polyfills. These are applied before loading ZoneJS and are sorted by browsers.
 *   2. Application imports. Files imported after ZoneJS that should be loaded before your main
 *      file.
 *
 * The current setup is for so-called "evergreen" browsers; the last versions of browsers that
 * automatically update themselves. This includes Safari >= 10, Chrome >= 55 (including Opera),
 * Edge >= 13 on the desktop, and iOS 10 and Chrome on mobile.
 *
 * Learn more in https://angular.io/docs/ts/latest/guide/browser-support.html
 */
/***************************************************************************************************
 * BROWSER POLYFILLS
 */
/** IE9, IE10 and IE11 requires all of the following polyfills. **/
// import 'core-js/es6/symbol';
// import 'core-js/es6/object';
// import 'core-js/es6/function';
// import 'core-js/es6/parse-int';
// import 'core-js/es6/parse-float';
// import 'core-js/es6/number';
// import 'core-js/es6/math';
// import 'core-js/es6/string';
// import 'core-js/es6/date';
// import 'core-js/es6/array';
// import 'core-js/es6/regexp';
// import 'core-js/es6/map';
// import 'core-js/es6/weak-map';
// import 'core-js/es6/set';
/** IE10 and IE11 requires the following for NgClass support on SVG elements */
// import 'classlist.js';  // Run `npm install --save classlist.js`.
/** IE10 and IE11 requires the following for the Reflect API. */
// import 'core-js/es6/reflect';
/** Evergreen browsers require these. **/
// Used for reflect-metadata in JIT. If you use AOT (and only Angular decorators), you can remove.

/**
 * Required to support Web Animations `@angular/platform-browser/animations`.
 * Needed for: All but Chrome, Firefox and Opera. http://caniuse.com/#feat=web-animation
 **/
// import 'web-animations-js';  // Run `npm install --save web-animations-js`.

var swaggerUIBundle = __WEBPACK_IMPORTED_MODULE_1_swagger_ui_dist__["SwaggerUIBundle"];
var swaggerUIStandalonePreset = __WEBPACK_IMPORTED_MODULE_1_swagger_ui_dist__["SwaggerUIStandalonePreset"];
/***************************************************************************************************
 * Zone JS is required by default for Angular itself.
 */
 // Included with Angular CLI.
/***************************************************************************************************
 * APPLICATION IMPORTS
 */


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map