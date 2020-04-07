import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { of } from 'rxjs';
import { Catalogo } from '../../models/catalogos/catalogo';
let PaymentsService = class PaymentsService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getPaymentsByFolio(folio) {
        return this.httpClient.get(`../api/facturas/${folio}/pagos`);
    }
    getPaymentById(id) {
        return this.httpClient.get(`../api/pagos/${id}`);
    }
    insertNewPayment(folio, payment) {
        return this.httpClient.post(`../api/facturas/${folio}/pagos`, payment);
    }
    deletePayment(folio, paymentId) {
        return this.httpClient.delete(`../api/facturas/${folio}/pagos/${paymentId}`);
    }
    updatePaymentWithValidation(folio, paymentId, payment) {
        return this.httpClient.put(`../api/facturas/${folio}/pagos/${paymentId}`, payment);
    }
    getFormasPago(roles) {
        const payTypeCat = [new Catalogo('EFECTIVO', 'Efectivo'),
            new Catalogo('CHEQUE', 'Cheque nominativo'),
            new Catalogo('TRANSFERENCIA', 'Transferencia electrónica de fondos'),
            new Catalogo('DEPOSITO', 'Deposito bancario')];
        if (roles !== undefined && roles.length > 0 && roles.find(r => r === 'OPERADOR') !== undefined) {
            payTypeCat.push(new Catalogo('CREDITO', 'Credito despacho'));
        }
        return of(payTypeCat);
    }
    getAllPayments(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            if (filterParams[key] !== undefined && filterParams[key].length > 0) {
                if (filterParams[key] instanceof Date) {
                    const date = filterParams[key];
                    pageParams = pageParams.append(key, `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`);
                }
                else {
                    pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : filterParams[key]);
                }
            }
        }
        return this.httpClient.get('../api/pagos', { params: pageParams });
    }
    getIncomes(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            let value;
            if (filterParams[key] instanceof Date) {
                let date = filterParams[key];
                value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            }
            else {
                value = filterParams[key];
            }
            if (value.length > 0) {
                pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : value);
            }
        }
        return this.httpClient.get('../api/pagos/ingresos', { params: pageParams });
    }
    getIncomesSum(filterParams) {
        let pageParams = new HttpParams();
        for (const key in filterParams) {
            let value;
            if (filterParams[key] instanceof Date) {
                let date = filterParams[key];
                value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            }
            else {
                value = filterParams[key];
            }
            if (value.length > 0) {
                pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : value);
            }
        }
        return this.httpClient.get('../api/pagos/ingresos/total', { params: pageParams });
    }
    getExpenses(page, size, filterParams) {
        let pageParams = new HttpParams().append('page', page.toString()).append('size', size.toString());
        for (const key in filterParams) {
            let value;
            if (filterParams[key] instanceof Date) {
                let date = filterParams[key];
                value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            }
            else {
                value = filterParams[key];
            }
            if (value.length > 0) {
                pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : value);
            }
        }
        return this.httpClient.get('../api/pagos/egresos', { params: pageParams });
    }
    getExpensesSum(filterParams) {
        let pageParams = new HttpParams();
        for (const key in filterParams) {
            let value;
            if (filterParams[key] instanceof Date) {
                let date = filterParams[key];
                value = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            }
            else {
                value = filterParams[key];
            }
            if (value.length > 0) {
                pageParams = pageParams.append(key, (filterParams[key] === '*') ? '' : value);
            }
        }
        return this.httpClient.get('../api/pagos/egresos/total', { params: pageParams });
    }
    updatePayment(payment) {
        return this.httpClient.put(`../api/pagos/${payment.id}`, payment);
    }
};
PaymentsService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], PaymentsService);
export { PaymentsService };
//# sourceMappingURL=payments.service.js.map