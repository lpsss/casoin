import { Moment } from 'moment';
import { IODARow } from 'app/shared/model/oda-row.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IODAHead {
    id?: number;
    nrFt?: number;
    dataFattura?: Moment;
    totaleFt?: number;
    orders?: IODARow[];
    customers?: ICustomer[];
}

export class ODAHead implements IODAHead {
    constructor(
        public id?: number,
        public nrFt?: number,
        public dataFattura?: Moment,
        public totaleFt?: number,
        public orders?: IODARow[],
        public customers?: ICustomer[]
    ) {}
}
