import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { ClientsData } from '../../../@core/data/clients-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
import { UsersData } from '../../../@core/data/users-data';
let ClientesComponent = class ClientesComponent {
    constructor(userService, clientService, donwloadService, router) {
        this.userService = userService;
        this.clientService = clientService;
        this.donwloadService = donwloadService;
        this.router = router;
        this.paths = [];
        this.page = new GenericPage();
        this.pageSize = '10';
        this.filterParams = { razonSocial: '', rfc: '', status: '*', promotor: '' };
    }
    ngOnInit() {
        this.paths = this.router.url.split('/');
        this.userService.getUserInfo().toPromise()
            .then((user) => {
            this.user = user;
            if (this.paths[2] === 'promotor') {
                this.filterParams.promotor = user.email;
            }
        }).then(() => this.updateDataTable(0, 10, this.filterParams));
    }
    updateDataTable(currentPage, pageSize, filterParams) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.clientService.getClients(pageValue, sizeValue, filterParams)
            .subscribe(result => this.page = result);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.clientService.getClients(0, 10000, this.filterParams).subscribe((result) => {
            this.donwloadService.exportCsv(result.content.map(r => r.informacionFiscal), 'Clientes');
        });
    }
    redirectToCliente(rfc) {
        this.router.navigate([`./pages/${this.paths[2]}/cliente/${rfc}`]);
    }
};
ClientesComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-clientes',
        templateUrl: './clientes.component.html',
        styleUrls: ['./clientes.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [UsersData,
        ClientsData,
        DownloadCsvService,
        Router])
], ClientesComponent);
export { ClientesComponent };
//# sourceMappingURL=clientes.component.js.map