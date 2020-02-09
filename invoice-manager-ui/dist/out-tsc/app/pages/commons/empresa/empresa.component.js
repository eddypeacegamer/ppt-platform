import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Empresa } from '../../../models/empresa';
import { CatalogsData } from '../../../@core/data/catalogs-data';
import { CompaniesData } from '../../../@core/data/companies-data';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
let EmpresaComponent = class EmpresaComponent {
    constructor(router, catalogsService, empresaService, route, sanitizer) {
        this.router = router;
        this.catalogsService = catalogsService;
        this.empresaService = empresaService;
        this.route = route;
        this.sanitizer = sanitizer;
        this.formInfo = { rfc: '', message: '', coloniaId: '*', success: '', certificateFileName: '', keyFileName: '', logoFileName: '' };
        this.coloniaId = 0;
        this.colonias = [];
        this.paises = ['México'];
        this.girosCat = [];
        this.errorMessages = [];
    }
    ngOnInit() {
        this.companyInfo = new Empresa();
        this.companyInfo.regimenFiscal = '*';
        this.companyInfo.giro = '*';
        this.companyInfo.tipo = '*';
        this.errorMessages = [];
        /** recovering folio info**/
        this.route.paramMap.subscribe(route => {
            const rfc = route.get('rfc');
            this.empresaService.getCompanyByRFC(rfc)
                .subscribe((data) => { this.companyInfo = data, this.formInfo.rfc = rfc; }, (error) => console.log(error.error.message || `${error.statusText} : ${error.message}`));
        });
        /**** LOADING CAT INFO ****/
        this.catalogsService.getAllGiros().subscribe((giros) => this.girosCat = giros, (error) => this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`));
    }
    sanitize(url) {
        return this.sanitizer.bypassSecurityTrustUrl(url);
    }
    zipCodeInfo(zipcode) {
        let zc = new String(zipcode);
        if (zc.length > 4 && zc.length < 6) {
            this.colonias = [];
            this.catalogsService.getZipCodeInfo(zipcode).subscribe((data) => {
                this.companyInfo.informacionFiscal.estado = data.estado;
                this.companyInfo.informacionFiscal.municipio = data.municipio;
                this.colonias = data.colonias;
                this.companyInfo.informacionFiscal.localidad = data.colonias[0];
            }, (error) => console.error(error));
        }
    }
    onLocation(index) {
        this.companyInfo.informacionFiscal.localidad = this.colonias[index];
    }
    onRegimenFiscalSelected(regimen) {
        this.companyInfo.regimenFiscal = regimen;
    }
    onGiroSelection(giro) {
        this.companyInfo.giro = giro;
    }
    onLineaSelected(linea) {
        this.companyInfo.tipo = linea;
    }
    logoUploadListener(event) {
        let reader = new FileReader();
        if (event.target.files && event.target.files.length > 0) {
            let file = event.target.files[0];
            if (file.size > 200000) {
                alert('El archivo demasiado grande, intenta con un archivo mas pequeño.');
            }
            else {
                reader.readAsDataURL(file);
                reader.onload = () => { this.formInfo.logoFileName = file.name + " " + file.type; this.companyInfo.logotipo = reader.result.toString(); };
                reader.onerror = (error) => { this.errorMessages.push('Error parsing image file'); console.error(error); };
            }
        }
    }
    keyUploadListener(event) {
        let reader = new FileReader();
        if (event.target.files && event.target.files.length > 0) {
            let file = event.target.files[0];
            reader.readAsDataURL(file);
            reader.onload = () => { this.formInfo.keyFileName = file.name + " " + file.type; this.companyInfo.llavePrivada = reader.result.toString(); };
            reader.onerror = (error) => { this.errorMessages.push('Error parsing key file'); console.error(error); };
        }
    }
    certificateUploadListener(event) {
        let reader = new FileReader();
        if (event.target.files && event.target.files.length > 0) {
            let file = event.target.files[0];
            reader.readAsDataURL(file);
            reader.onload = () => { this.formInfo.certificateFileName = file.name + " " + file.type; this.companyInfo.certificado = reader.result.toString(); };
            reader.onerror = (error) => { this.errorMessages.push('Error parsing certificate file'); };
        }
    }
    insertNewCompany() {
        this.errorMessages = [];
        this.formInfo.success = '';
        this.empresaService.insertNewCompany(this.companyInfo)
            .subscribe((empresa) => { this.router.navigate([`./pages/operaciones/empresas`]); }, (error) => { this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`); });
    }
    updateCompany() {
        this.errorMessages = [];
        this.formInfo.success = '';
        this.empresaService.updateCompany(this.companyInfo.informacionFiscal.rfc, this.companyInfo)
            .subscribe((data) => { this.router.navigate([`./pages/contablidad/empresas`]); }, (error) => { this.errorMessages.push(error.error.message || `${error.statusText} : ${error.message}`); });
    }
};
EmpresaComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-empresa',
        templateUrl: './empresa.component.html',
        styleUrls: ['./empresa.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [Router,
        CatalogsData,
        CompaniesData,
        ActivatedRoute,
        DomSanitizer])
], EmpresaComponent);
export { EmpresaComponent };
//# sourceMappingURL=empresa.component.js.map