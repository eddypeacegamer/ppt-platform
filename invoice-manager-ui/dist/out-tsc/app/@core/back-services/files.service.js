import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
let FilesService = class FilesService {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    getFacturaFile(folio, tipoArchivo) {
        return this.httpClient.get(`../api/facturas/${folio}/files/${tipoArchivo}`);
    }
    getResourceFile(referencia, tipoRecurso, tipoArchivo) {
        return this.httpClient.get(`../api/recursos/${tipoRecurso}/files/${tipoArchivo}/referencias/${referencia}`);
    }
    insertFacturaFile(file) {
        return this.httpClient.post(`../api/facturas/${file.folio}/files`, file);
    }
    insertResourceFile(file) {
        return this.httpClient.post(`../api/recursos/${file.tipoRecurso}/files`, file);
    }
    deleteFacturaFile(id) {
        return this.httpClient.delete(`../api/facturas/files/${id}`);
    }
    deleteResourceFile(id) {
        return this.httpClient.delete(`../api/recursos/files/${id}`);
    }
};
FilesService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], FilesService);
export { FilesService };
//# sourceMappingURL=files.service.js.map