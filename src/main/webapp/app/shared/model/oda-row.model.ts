export interface IODARow {
    id?: number;
    qta?: number;
    costo?: number;
    oDAHeadId?: number;
    productId?: number;
}

export class ODARow implements IODARow {
    constructor(public id?: number, public qta?: number, public costo?: number, public oDAHeadId?: number, public productId?: number) {}
}
