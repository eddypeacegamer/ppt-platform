import { Injectable } from '@angular/core';
import { Papa } from 'ngx-papaparse';

@Injectable({
  providedIn: 'root'
})
export class DownloadCsvService {

  private DEFAULT_FILENAME = 'csvSample';

  constructor(private csvParser: Papa) { }

  public exportCsv(data: any[], filename: string) {

    if (data == null || data == undefined || data.length < 1) {
      console.error("Donwload service can't generate report from empty or null data.");
      alert("Donwload service can't generate report from empty or null data.");
      return;
    } else {
      let content = this.csvParser.unparse(data);
      let blob = new Blob([content], { "type": "text/csv;charset=utf8;" });

      if (navigator.msSaveBlob) {
        navigator.msSaveBlob(blob, (filename == undefined || filename == null || filename == '') ? this.DEFAULT_FILENAME : filename.replace(/ /g, "_") + ".csv");
      } else {
      
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

}
