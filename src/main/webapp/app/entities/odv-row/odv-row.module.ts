import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CasoinSharedModule } from 'app/shared';
import {
    ODVRowComponent,
    ODVRowDetailComponent,
    ODVRowUpdateComponent,
    ODVRowDeletePopupComponent,
    ODVRowDeleteDialogComponent,
    oDVRowRoute,
    oDVRowPopupRoute
} from './';

const ENTITY_STATES = [...oDVRowRoute, ...oDVRowPopupRoute];

@NgModule({
    imports: [CasoinSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ODVRowComponent, ODVRowDetailComponent, ODVRowUpdateComponent, ODVRowDeleteDialogComponent, ODVRowDeletePopupComponent],
    entryComponents: [ODVRowComponent, ODVRowUpdateComponent, ODVRowDeleteDialogComponent, ODVRowDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CasoinODVRowModule {}
