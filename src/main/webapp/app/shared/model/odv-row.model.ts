export interface IODVRow {
    id?: number;
    qta?: number;
    costo?: number;
    oDVHeadId?: number;
    productId?: number;
}

export class ODVRow implements IODVRow {
    constructor(public id?: number, public qta?: number, public costo?: number, public oDVHeadId?: number, public productId?: number) {}
}
