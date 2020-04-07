import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { GenericPage } from '../../../models/generic-page';
import { TransferData } from '../../../@core/data/transfers-data';
import { DownloadCsvService } from '../../../@core/util-services/download-csv.service';
import { NbDialogService } from '@nebular/theme';
import { InvoiceRequestComponent } from './invoice-request/invoice-request.component';
let InvoiceGeneratorComponent = class InvoiceGeneratorComponent {
    constructor(tranferService, donwloadService, dialogService) {
        this.tranferService = tranferService;
        this.donwloadService = donwloadService;
        this.dialogService = dialogService;
        this.headers = ['RFC Emisor', 'RFC Receptor', 'Linea Emisor', 'Linea Receptor', 'Total', 'Fecha Solicitud'];
        this.page = new GenericPage();
        this.pageSize = '10';
        this.filterParams = { tipoEmisor: 'A', tipoReceptor: 'B', since: '', to: '' };
    }
    ngOnInit() {
        this.filterParams = { tipoEmisor: 'A', tipoReceptor: 'B', since: '', to: '' };
        this.updateDataTable();
    }
    updateDataTable(currentPage, pageSize, filterParams) {
        const pageValue = currentPage || 0;
        const sizeValue = pageSize || 10;
        this.tranferService.getAllTransfers(pageValue, sizeValue, filterParams)
            .subscribe((result) => this.page = result);
    }
    onChangePageSize(pageSize) {
        this.updateDataTable(this.page.number, pageSize);
    }
    downloadHandler() {
        this.tranferService.getAllTransfers(0, 10000, this.filterParams).subscribe(result => {
            console.log(result.content);
            this.donwloadService.exportCsv(result.content, 'Transferencias');
        });
    }
    openDialog(transferencia) {
        this.dialogService.open(InvoiceRequestComponent, {
            context: {
                transfer: transferencia,
            },
        }).onClose.subscribe(() => this.updateDataTable(this.page.number, this.page.size, this.filterParams));
    }
};
InvoiceGeneratorComponent = tslib_1.__decorate([
    Component({
        selector: 'ngx-invoice-generator',
        templateUrl: './invoice-generator.component.html',
        styleUrls: ['./invoice-generator.component.scss']
    }),
    tslib_1.__metadata("design:paramtypes", [TransferData,
        DownloadCsvService,
        NbDialogService])
], InvoiceGeneratorComponent);
export { InvoiceGeneratorComponent };
//# sourceMappingURL=invoice-generator.component.js.map