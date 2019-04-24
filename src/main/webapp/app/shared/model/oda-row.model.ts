import { IODAHead } from 'app/shared/model/oda-head.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IODARow {
    id?: number;
    qta?: number;
    costo?: number;
    oDAHead?: IODAHead;
    product?: IProduct;
}

export class ODARow implements IODARow {
    constructor(public id?: number, public qta?: number, public costo?: number, public oDAHead?: IODAHead, public product?: IProduct) {}
}
