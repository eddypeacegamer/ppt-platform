import { Injectable } from '@angular/core';
import { InvoicesData } from '../data/invoices-data';

@Injectable({
    providedIn: 'root',
  })
export class DonwloadFileService {


    constructor(private invoiceService:InvoicesData){}

    public exportFiles(folio:string,filename:string){
        this.invoiceService.getInvoiceFiles(folio).subscribe(files=>{
          this.downloadFile(files.xml,`${filename}.xml`,'text/xml;charset=utf8;');
          this.downloadFile(files.pdf,`${filename}.pdf`,'application/pdf;')});
    }

  
    public downloadFile(data:any,filename:string,fileType:string){
        console.log(`Downloading ${filename} ...`)
        if (data == null || data == undefined || data.length < 1) {
            console.error("Donwload service can't generate report from empty or null data.");
            alert("Donwload service can't generate report from empty or null data.");
            return;
          } else {
            const byteString = window.atob(data);
        const arrayBuffer = new ArrayBuffer(byteString.length);
        const int8Array = new Uint8Array(arrayBuffer);
        for (let i = 0; i < byteString.length; i++) {
          int8Array[i] = byteString.charCodeAt(i);
        }
        const blob = new Blob([int8Array], { type: fileType });
            if (navigator.msSaveBlob) {
              navigator.msSaveBlob(blob, filename.replace(/ /g, "_"));
            } else {
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



}