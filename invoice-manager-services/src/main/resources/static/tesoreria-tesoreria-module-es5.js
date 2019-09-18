(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["tesoreria-tesoreria-module"],{

/***/ "./src/app/pages/tesoreria/tesoreria-routing.module.ts":
/*!*************************************************************!*\
  !*** ./src/app/pages/tesoreria/tesoreria-routing.module.ts ***!
  \*************************************************************/
/*! exports provided: TesoreriaRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TesoreriaRoutingModule", function() { return TesoreriaRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");



var routes = [];
var TesoreriaRoutingModule = /** @class */ (function () {
    function TesoreriaRoutingModule() {
    }
    TesoreriaRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], TesoreriaRoutingModule);
    return TesoreriaRoutingModule;
}());



/***/ }),

/***/ "./src/app/pages/tesoreria/tesoreria.component.ts":
/*!********************************************************!*\
  !*** ./src/app/pages/tesoreria/tesoreria.component.ts ***!
  \********************************************************/
/*! exports provided: TesoreriaComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TesoreriaComponent", function() { return TesoreriaComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var TesoreriaComponent = /** @class */ (function () {
    function TesoreriaComponent() {
    }
    TesoreriaComponent.prototype.ngOnInit = function () {
    };
    TesoreriaComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'ngx-tesoreria',
            template: "\n    <h1>TESORERIA IS WORKING</h1>\n    <router-outlet></router-outlet>\n  ",
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], TesoreriaComponent);
    return TesoreriaComponent;
}());



/***/ }),

/***/ "./src/app/pages/tesoreria/tesoreria.module.ts":
/*!*****************************************************!*\
  !*** ./src/app/pages/tesoreria/tesoreria.module.ts ***!
  \*****************************************************/
/*! exports provided: TesoreriaModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TesoreriaModule", function() { return TesoreriaModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _tesoreria_routing_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./tesoreria-routing.module */ "./src/app/pages/tesoreria/tesoreria-routing.module.ts");
/* harmony import */ var _tesoreria_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./tesoreria.component */ "./src/app/pages/tesoreria/tesoreria.component.ts");




var TesoreriaModule = /** @class */ (function () {
    function TesoreriaModule() {
    }
    TesoreriaModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_tesoreria_component__WEBPACK_IMPORTED_MODULE_3__["TesoreriaComponent"]],
            imports: [
                _tesoreria_routing_module__WEBPACK_IMPORTED_MODULE_2__["TesoreriaRoutingModule"]
            ]
        })
    ], TesoreriaModule);
    return TesoreriaModule;
}());



/***/ })

}]);
//# sourceMappingURL=tesoreria-tesoreria-module-es5.js.map