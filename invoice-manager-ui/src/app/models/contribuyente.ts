export class Contribuyente{

    public id: number;
    public rfc: string;
    public giro: string;
    public nombre: string;
    public razonSocial: string;
    public calle: string;
    public noInterior: number;
    public noExterior: number;
    public municipio: string;
    public estado: string;
    public pais: string;
    public localidad: string;
    public cp: string;
    public coo: string;
    public correo: string;


    constructor(rfc?: string, razonSocial?: string) {
        this.rfc = rfc;
        this.razonSocial = razonSocial;
    }
}