(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["contabilidad-contabilidad-module"],{

/***/ "./src/app/pages/contabilidad/contabilidad-routing.module.ts":
/*!*******************************************************************!*\
  !*** ./src/app/pages/contabilidad/contabilidad-routing.module.ts ***!
  \*******************************************************************/
/*! exports provided: ContabilidadRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ContabilidadRoutingModule", function() { return ContabilidadRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");



const routes = [];
let ContabilidadRoutingModule = class ContabilidadRoutingModule {
};
ContabilidadRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
    })
], ContabilidadRoutingModule);



/***/ }),

/***/ "./src/app/pages/contabilidad/contabilidad.component.ts":
/*!**************************************************************!*\
  !*** ./src/app/pages/contabilidad/contabilidad.component.ts ***!
  \**************************************************************/
/*! exports provided: ContabilidadComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ContabilidadComponent", function() { return ContabilidadComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let ContabilidadComponent = class ContabilidadComponent {
    constructor() { }
    ngOnInit() {
    }
};
ContabilidadComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'ngx-contabilidad',
        template: `
    <router-outlet></router-outlet>
  `,
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
], ContabilidadComponent);



/***/ }),

/***/ "./src/app/pages/contabilidad/contabilidad.module.ts":
/*!***********************************************************!*\
  !*** ./src/app/pages/contabilidad/contabilidad.module.ts ***!
  \***********************************************************/
/*! exports provided: ContabilidadModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ContabilidadModule", function() { return ContabilidadModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _contabilidad_routing_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./contabilidad-routing.module */ "./src/app/pages/contabilidad/contabilidad-routing.module.ts");
/* harmony import */ var _contabilidad_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./contabilidad.component */ "./src/app/pages/contabilidad/contabilidad.component.ts");




let ContabilidadModule = class ContabilidadModule {
};
ContabilidadModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        declarations: [_contabilidad_component__WEBPACK_IMPORTED_MODULE_3__["ContabilidadComponent"]],
        imports: [
            _contabilidad_routing_module__WEBPACK_IMPORTED_MODULE_2__["ContabilidadRoutingModule"]
        ]
    })
], ContabilidadModule);



/***/ })

}]);
//# sourceMappingURL=contabilidad-contabilidad-module-es2015.js.map