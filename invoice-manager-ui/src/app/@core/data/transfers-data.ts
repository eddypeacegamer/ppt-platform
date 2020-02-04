import { GenericPage } from '../../models/generic-page';
import { Observable } from 'rxjs';
import { Transferencia } from '../../models/transferencia';

export abstract class TransferData{

    abstract getAllTransfers(page: number, size: number, filterParams?: any) : Observable<GenericPage<Transferencia>>;

    abstract saveAllTransfers(transferencias: Transferencia[]) : Observable<Transferencia[]>;

    abstract updateTranfer(transfer : Transferencia): Observable<Transferencia>;
}