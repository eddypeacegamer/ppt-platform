import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class SharedService{
    variableState:any;
    filterParams: any = { emisor: '', remitente: '', prefolio: '', status: '*', since: '', to: '', lineaEmisor: 'A', solicitante: '' };

    public setFormData(formData: any): any {
        this.filterParams = formData;
        console.log("set");
      }
    
      public getFormData(): any {
        console.log("get");
        return this.filterParams;  
      
      }
}