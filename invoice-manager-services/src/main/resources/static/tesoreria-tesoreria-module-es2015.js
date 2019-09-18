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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");



const routes = [];
let TesoreriaRoutingModule = class TesoreriaRoutingModule {
};
TesoreriaRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
    })
], TesoreriaRoutingModule);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let TesoreriaComponent = class TesoreriaComponent {
    constructor() { }
    ngOnInit() {
    }
};
TesoreriaComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'ngx-tesoreria',
        template: `
    <h1>TESORERIA IS WORKING</h1>
    <router-outlet></router-outlet>
  `,
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
], TesoreriaComponent);



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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _tesoreria_routing_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./tesoreria-routing.module */ "./src/app/pages/tesoreria/tesoreria-routing.module.ts");
/* harmony import */ var _tesoreria_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./tesoreria.component */ "./src/app/pages/tesoreria/tesoreria.component.ts");




let TesoreriaModule = class TesoreriaModule {
};
TesoreriaModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        declarations: [_tesoreria_component__WEBPACK_IMPORTED_MODULE_3__["TesoreriaComponent"]],
        imports: [
            _tesoreria_routing_module__WEBPACK_IMPORTED_MODULE_2__["TesoreriaRoutingModule"]
        ]
    })
], TesoreriaModule);



/***/ })

}]);
//# sourceMappingURL=tesoreria-tesoreria-module-es2015.js.map