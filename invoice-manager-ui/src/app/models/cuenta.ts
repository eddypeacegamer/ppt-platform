export class Cuenta {

    public id: string;
    public empresa: string;
    public banco: string;
    public cuenta: string;
    public clabe: string;
    public fechaCreacion: Date;
    public fechaActualizacion: Date;
    constructor(id?: string, banco?: string, cuenta?: string) {
        this.id = id;
        this.banco = banco;
        this.cuenta = cuenta;
    }
}
