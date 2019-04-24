import { Moment } from 'moment';
import { IODVRow } from 'app/shared/model/odv-row.model';
import { ISupplier } from 'app/shared/model/supplier.model';

export interface IODVHead {
    id?: number;
    nrFt?: number;
    dataFattura?: Moment;
    totaleFt?: number;
    orders?: IODVRow[];
    suppliers?: ISupplier[];
}

export class ODVHead implements IODVHead {
    constructor(
        public id?: number,
        public nrFt?: number,
        public dataFattura?: Moment,
        public totaleFt?: number,
        public orders?: IODVRow[],
        public suppliers?: ISupplier[]
    ) {}
}
