import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Papa } from 'ngx-papaparse';
let DownloadCsvService = class DownloadCsvService {
    constructor(csvParser) {
        this.csvParser = csvParser;
        this.DEFAULT_FILENAME = 'csvSample';
    }
    exportCsv(data, filename) {
        if (data == null || data == undefined || data.length < 1) {
            console.error("Donwload service can't generate report from empty or null data.");
            alert("No se encontro información, imposible generar reporte.");
            return;
        }
        else {
            let content = this.csvParser.unparse(data);
            let blob = new Blob([content], { "type": "text/csv;charset=utf8;" });
            if (navigator.msSaveBlob) {
                navigator.msSaveBlob(blob, (filename == undefined || filename == null || filename == '') ? this.DEFAULT_FILENAME : filename.replace(/ /g, "_") + ".csv");
            }
            else {
                let link = document.createElement("a");
                link.href = URL.createObjectURL(blob);
                link.setAttribute('visibility', 'hidden');
                link.download = (filename == undefined || filename == null || filename == '') ? this.DEFAULT_FILENAME : filename.replace(/ /g, "_") + ".csv";
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }
        }
    }
};
DownloadCsvService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [Papa])
], DownloadCsvService);
export { DownloadCsvService };
//# sourceMappingURL=download-csv.service.js.map