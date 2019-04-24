import { IODAHead } from 'app/shared/model/oda-head.model';

export interface ICustomer {
    id?: number;
    nome?: string;
    cognome?: string;
    telefono?: string;
    mail?: string;
    oDAHead?: IODAHead;
}

export class Customer implements ICustomer {
    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public telefono?: string,
        public mail?: string,
        public oDAHead?: IODAHead
    ) {}
}
