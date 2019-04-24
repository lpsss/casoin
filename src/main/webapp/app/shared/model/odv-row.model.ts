import { IODVHead } from 'app/shared/model/odv-head.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IODVRow {
    id?: number;
    qta?: number;
    costo?: number;
    oDVHead?: IODVHead;
    product?: IProduct;
}

export class ODVRow implements IODVRow {
    constructor(public id?: number, public qta?: number, public costo?: number, public oDVHead?: IODVHead, public product?: IProduct) {}
}
