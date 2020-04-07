export class Impuesto {
    constructor(impuesto, tasaOCuota, base, importe, tipoFactor) {
        this.base = base || 0;
        this.importe = importe || 0;
        this.impuesto = impuesto || '002';
        this.tipoFactor = tipoFactor || 'Tasa';
        this.tasaOCuota = tasaOCuota || '0.160000';
    }
}
//# sourceMappingURL=impuesto.js.map