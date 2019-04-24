import { IODVRow } from 'app/shared/model/odv-row.model';
import { IODARow } from 'app/shared/model/oda-row.model';

export interface IProduct {
    id?: number;
    nome?: string;
    codice?: string;
    marca?: string;
    oDVRows?: IODVRow[];
    oDARows?: IODARow[];
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public nome?: string,
        public codice?: string,
        public marca?: string,
        public oDVRows?: IODVRow[],
        public oDARows?: IODARow[]
    ) {}
}
