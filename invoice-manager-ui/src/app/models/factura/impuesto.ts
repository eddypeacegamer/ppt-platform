export class Impuesto{

    public base : number;
    public importe : number;

    public impuesto : string;
    public tipoFactor : string;
    public tasaOCuota : string;


    constructor(impuesto ?:string,tasaOCuota?:string,base?:number,importe?:number,tipoFactor?:string){
        this.base = base || 0;
        this.importe = importe || 0;
        this.impuesto = impuesto || '002';
        this.tipoFactor = tipoFactor || 'Tasa';
        this.tasaOCuota = tasaOCuota || '0.160000';
    }
}