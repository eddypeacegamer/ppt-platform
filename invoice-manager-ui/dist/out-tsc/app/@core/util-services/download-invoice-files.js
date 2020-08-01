import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { InvoicesData } from '../data/invoices-data';
let DownloadInvoiceFilesService = class DownloadInvoiceFilesService {
    constructor(invoiceService) {
        this.invoiceService = invoiceService;
    }
    exportFiles(folio, filename) {
        this.invoiceService.getInvoiceFiles(folio).subscribe(files => {
            this.downloadFile(files.xml, `${filename}.xml`, 'text/xml;charset=utf8;');
            this.downloadFile(files.pdf, `${filename}.pdf`, 'application/pdf;');
        });
    }
    downloadFile(data, filename, fileType) {
        console.log(`Downloading ${filename} ...`);
        if (data == null || data == undefined || data.length < 1) {
            console.error("Donwload service can't generate report from empty or null data.");
            alert("No se encontro información, imposible generar reporte.");
            return;
        }
        else {
            const byteString = window.atob(data);
            const arrayBuffer = new ArrayBuffer(byteString.length);
            const int8Array = new Uint8Array(arrayBuffer);
            for (let i = 0; i < byteString.length; i++) {
                int8Array[i] = byteString.charCodeAt(i);
            }
            const blob = new Blob([int8Array], { type: fileType });
            if (navigator.msSaveBlob) {
                navigator.msSaveBlob(blob, filename.replace(/ /g, "_"));
            }
            else {
                let link = document.createElement("a");
                link.href = URL.createObjectURL(blob);
                link.setAttribute('visibility', 'hidden');
                link.download = filename.replace(/ /g, "_");
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }
        }
    }
};
DownloadInvoiceFilesService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [InvoicesData])
], DownloadInvoiceFilesService);
export { DownloadInvoiceFilesService };
//# sourceMappingURL=download-invoice-files.js.map