import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class SharedService{
    variableState:any;
    filterParams: any = { emisor: '', remitente: '', prefolio: '', status: '*', since: '', to: '', lineaEmisor: 'A', solicitante: '' };
}