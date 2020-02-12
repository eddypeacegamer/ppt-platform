import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Client } from '../../../models/client';
import { ClientsData } from '../../../@core/data/clients-data';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';
let ClienteComponent = class ClienteComponent {
    constructor(clientService, userService, catalogsService, route, router) {
        this.clientService = clientService;
        this.userService = userService;
        this.catalogsService = catalogsService;
        this.route = route;
        this.router = router;
        this.isPromotor = false;
        this.formInfo = { rfc: '', message: '', coloniaId: '*', success: '' };
        this.coloniaId = 0;
        this.colonias = [];
        this.paises = ['México'];
    }
    ngOnInit() {
        this.isPromotor = (this.router.url.indexOf('/promotor') > 0) ? true : false;
        this.clientInfo = new Client();
        /** recovering folio info**/
        this.route.paramMap.subscribe(route => {
            const rfc = route.get('rfc');
            if (rfc !== '*') {
                this.clientService.getClientByRFC(rfc)
                    .subscribe((client) => { this.clientInfo = client, this.formInfo.rfc = rfc; }, (error) => {
                    this.formInfo.message = error.error.message ||
                        `${error.statusText} : ${error.message}`;
                    this.formInfo.status = error.status;
                });
            }
        });
    }
    updateClient() {
        this.formInfo.success = '';
        this.formInfo.message = '';
        this.validatePercentages();
        this.clientService.updateClient(this.clientInfo).subscribe(client => { this.formInfo.success = 'Cliente actualizado exitosamente'; this.clientInfo = client; }, (error) => { this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status; });
    }
    insertClient() {
        this.formInfo.success = '';
        this.formInfo.message = '';
        this.validatePercentages();
        if (this.clientInfo.porcentajeCliente < 0 || this.clientInfo.porcentajeContacto < 0
            || this.clientInfo.porcentajeDespacho < 0 || this.clientInfo.porcentajePromotor < 0) {
            this.formInfo.message = 'Alguno o algunos porcentages son invalidos';
        }
        else {
            this.userService.getUserInfo().toPromise().then(user => this.clientInfo.correoPromotor = user.email)
                .then(() => {
                this.clientService.insertNewClient(this.clientInfo).subscribe(client => { this.formInfo.success = 'Cliente guardado exitosamente'; this.clientInfo = client; }, (error) => { this.formInfo.message = error.error.message || `${error.statusText} : ${error.message}`; this.formInfo.status = error.status; });
            });
        }
    }
    zipCodeInfo(zc) {
        if (zc.length > 4 && zc.length < 6) {
            this.colonias = [];
            this.catalogsService.getZipCodeInfo(zc).subscribe((data) => {
                this.clientInfo.informacionFiscal.estado = data.estado;
                this.clientInfo.informacionFiscal.municipio = data.municipio;
                this.colonias = data.colonias;
                this.clientInfo.informacionFiscal.localidad = data.colonias[0];
                if (data.colonias.length < 1) {
                    alert(`No se ha encontrado información pata el codigo postal ${zc}`);
                }
            }, (error) => alert(error.error.message || error.statusText));
        }
    }
    onLocation(index) {
        this.clientInfo.informacionFiscal.localidad = this.colonias[index];
    }
    validatePercentages() {
        if (this.clientInfo.correoContacto === undefined || this.clientInfo.correoContacto.length < 1) {
            this.clientInfo.correoContacto = 'Sin asignar';
            this.clientInfo.porcentajeContacto = 0;
            this.clientInfo.porcentajeDespacho = 16 - this.clientInfo.porcentajeCliente - this.clientInfo.porcentajePromotor;
        }
    }
    toggleOn() {
        this.clientInfo.activo = true;
        this.updateClient();
    }
    toggleOff() {
        this.clientInfo.activo = false;
        this.updateClient();
    }
};
ClienteComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-cliente',
        templateUrl: './cliente.component.html',
        styleUrls: ['./cliente.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [ClientsData,
        UsersData,
        CatalogsData,
        ActivatedRoute,
        Router])
], ClienteComponent);
export { ClienteComponent };
//# sourceMappingURL=cliente.component.js.map