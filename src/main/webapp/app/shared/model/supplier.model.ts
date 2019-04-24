export interface ISupplier {
    id?: number;
    nome?: string;
    cognome?: string;
    piva?: string;
    ragSociale?: string;
    indirizzo?: string;
    oDVHeadId?: number;
}

export class Supplier implements ISupplier {
    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public piva?: string,
        public ragSociale?: string,
        public indirizzo?: string,
        public oDVHeadId?: number
    ) {}
}
