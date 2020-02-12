import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { forkJoin, of } from 'rxjs';
import { Catalogo } from '../../models/catalogos/catalogo';
let CatalogsService = class CatalogsService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getAllClavesProductoServicio(page, size) {
        const pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        return this.httpClient.get('../api/catalogs/producto-servicios', { params: pageParams });
    }
    getZipCodeInfo(zipCode) {
        return this.httpClient.get(`/api/catalogs/codigo-postal/${zipCode}`);
    }
    getProductoServiciosByDescription(description) {
        const params = new HttpParams().append('descripcion', description);
        return this.httpClient.get('/api/catalogs/producto-servicios', { params: params });
    }
    getProductoServiciosByClave(clave) {
        const params = new HttpParams().append('clave', clave);
        return this.httpClient.get('/api/catalogs/producto-servicios', { params: params });
    }
    getClaveUnidadByName(name) {
        const params = new HttpParams().append('nombre', name);
        return this.httpClient.get('/api/catalogs/clave-unidad', { params: params });
    }
    getAllUsoCfdis() {
        return this.httpClient.get('/api/catalogs/uso-cdfi');
    }
    getAllRegimenFiscal() {
        return this.httpClient.get('/api/catalogs/regimen-fiscal');
    }
    getAllGiros() {
        return this.httpClient.get('/api/catalogs/giros');
    }
    getStatusPago() {
        return this.httpClient.get('/api/catalogs/status-pago');
    }
    getStatusValidacion() {
        return this.httpClient.get('/api/catalogs/status-evento');
    }
    getStatusDevolucion() {
        return this.httpClient.get('/api/catalogs/status-devolucion');
    }
    getFormasPago(metodo) {
        if (metodo === 'PUE') {
            return of([new Catalogo('01', 'Efectivo'),
                new Catalogo('02', 'Cheque nominativo'),
                new Catalogo('03', 'Transferencia electrónica de fondos')]);
        }
        if (metodo === 'PPD') {
            return of([new Catalogo('99', 'Por definir')]);
        }
        return of([new Catalogo('01', 'Efectivo'),
            new Catalogo('02', 'Cheque nominativo'),
            new Catalogo('03', 'Transferencia electrónica de fondos'),
            new Catalogo('99', 'Por definir')]);
    }
    getBancos() {
        return this.httpClient.get('/api/catalogs/bancos');
    }
    getTiposReferencia(formapago) {
        if (formapago === 'EFECTIVO') {
            return of([new Catalogo('DIRECCION', 'Direcccion de entrega')]);
        }
        if (formapago === 'CHEQUE') {
            return of([new Catalogo('DIRECCION', 'Direcccion de entrega')]);
        }
        if (formapago === 'TRANSFERENCIA') {
            return of([new Catalogo('CLABE', 'CLABE bancaria'),
                new Catalogo('NO_CUENTA', 'No. cuenta'),
                new Catalogo('TC', 'Tarjeta de credito'),
                new Catalogo('TD', 'Tarjeta de debito'),
            ]);
        }
        if (formapago === 'FACTURA') {
            return of([new Catalogo('FOLIO', 'Folio factura a pagar')]);
        }
        return of([]);
    }
    getInvoiceCatalogs() {
        const giros = this.getAllGiros();
        const claveUnidad = this.getClaveUnidadByName('');
        const usoCfdi = this.getAllUsoCfdis();
        const statusPago = this.getStatusPago();
        const statusDevolucion = this.getStatusDevolucion();
        const statusEvento = this.getStatusValidacion();
        const formasPago = this.getFormasPago();
        return forkJoin([giros, claveUnidad, usoCfdi, statusPago, statusDevolucion, statusEvento, formasPago]);
    }
};
CatalogsService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], CatalogsService);
export { CatalogsService };
//# sourceMappingURL=catalogs.service.js.map