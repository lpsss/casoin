export interface ICustomer {
    id?: number;
    nome?: string;
    cognome?: string;
    telefono?: string;
    mail?: string;
    oDAHeadId?: number;
}

export class Customer implements ICustomer {
    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public telefono?: string,
        public mail?: string,
        public oDAHeadId?: number
    ) {}
}
