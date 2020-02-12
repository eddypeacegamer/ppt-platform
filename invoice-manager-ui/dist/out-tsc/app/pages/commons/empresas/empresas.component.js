import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { CompaniesData } from '../../../@core/data/companies-data';
import { GenericPage } from '../../../models/generic-page';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { Router } from '@angular/router';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { map } from 'rxjs/operators';
let EmpresasComponent = class EmpresasComponent {
    constructor(router, companyService, catalogsService, donwloadService) {
        this.router = router;
        this.companyService = companyService;
        this.catalogsService = catalogsService;
        this.donwloadService = donwloadService;
        this.page = new GenericPage();
        this.pageSize = '10';
        this.filterParams = { razonSocial: '', rfc: '', linea: '' };
    }
    ngOnInit() {
        this.catalogsService.getAllGiros().toPromise()
            .then(cat => this.girosCat = cat)
            .then(() => this.updateDataTable());
    }
    getCompanyInfo(pageValue, sizeValue, filterParams) {
        return this.companyService.getCompanies(pageValue, sizeValue, filterParams).pipe(map((page) => {
            const records = page.content.map(r => {
                const record = {};
                record.rfc = r.informacionFiscal.rfc;
                record.razonSocial = r.informacionFiscal.razonSocial;
                record.direccion = `${r.informacionFiscal.calle} ${r.informacionFiscal.noExterior}, ${r.informacionFiscal.localidad} ${r.informacionFiscal.municipio} ${r.informacionFiscal.estado}, C.P. ${r.informacionFiscal.cp}`;
                record.giro = this.girosCat.find(g => g.id === r.giro).nombre;
                record.tipo = r.tipo;
                record.correo = r.correo;
                record.activo = (r.activo) ? 'SI' : 'NO';
                record.fechaCreacion = r.fechaCreacion;
                record.fechaActualizacion = r.fechaActualizacion;
                return record;
            });
            page.content = records;
            return page;
        }));
    }
    updateDataTable(currentPage, pageSize, filterParams) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.getCompanyInfo(pageValue, sizeValue, filterParams).subscribe(result => this.page = result);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    onCompanySelected(tipo) {
        if (tipo === '*') {
            this.filterParams.linea = '';
        }
        else {
            this.filterParams.linea = tipo;
        }
    }
    newCompany() {
        this.router.navigate([`./pages/operaciones/empresa/*`]);
    }
    downloadHandler() {
        this.getCompanyInfo(0, 10000, this.filterParams).subscribe(result => {
            this.donwloadService.exportCsv(result.content, 'Empresas');
        });
    }
    redirectToEmpresa(rfc) {
        this.router.navigate([`./pages/operaciones/empresa/${rfc}`]);
    }
};
EmpresasComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-empresas',
        templateUrl: './empresas.component.html',
        styleUrls: ['./empresas.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [Router,
        CompaniesData,
        CatalogsData,
        DownloadCsvService])
], EmpresasComponent);
export { EmpresasComponent };
//# sourceMappingURL=empresas.component.js.map