import { IODVHead } from 'app/shared/model/odv-head.model';

export interface ISupplier {
    id?: number;
    nome?: string;
    cognome?: string;
    piva?: string;
    ragSociale?: string;
    indirizzo?: string;
    oDVHead?: IODVHead;
}

export class Supplier implements ISupplier {
    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public piva?: string,
        public ragSociale?: string,
        public indirizzo?: string,
        public oDVHead?: IODVHead
    ) {}
}
