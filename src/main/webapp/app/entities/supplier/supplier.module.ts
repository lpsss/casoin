import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CasoinSharedModule } from 'app/shared';
import {
    SupplierComponent,
    SupplierDetailComponent,
    SupplierUpdateComponent,
    SupplierDeletePopupComponent,
    SupplierDeleteDialogComponent,
    supplierRoute,
    supplierPopupRoute
} from './';

const ENTITY_STATES = [...supplierRoute, ...supplierPopupRoute];

@NgModule({
    imports: [CasoinSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SupplierComponent,
        SupplierDetailComponent,
        SupplierUpdateComponent,
        SupplierDeleteDialogComponent,
        SupplierDeletePopupComponent
    ],
    entryComponents: [SupplierComponent, SupplierUpdateComponent, SupplierDeleteDialogComponent, SupplierDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CasoinSupplierModule {}
