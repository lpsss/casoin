import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CasoinSharedModule } from 'app/shared';
import {
    ODVHeadComponent,
    ODVHeadDetailComponent,
    ODVHeadUpdateComponent,
    ODVHeadDeletePopupComponent,
    ODVHeadDeleteDialogComponent,
    oDVHeadRoute,
    oDVHeadPopupRoute
} from './';

const ENTITY_STATES = [...oDVHeadRoute, ...oDVHeadPopupRoute];

@NgModule({
    imports: [CasoinSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ODVHeadComponent,
        ODVHeadDetailComponent,
        ODVHeadUpdateComponent,
        ODVHeadDeleteDialogComponent,
        ODVHeadDeletePopupComponent
    ],
    entryComponents: [ODVHeadComponent, ODVHeadUpdateComponent, ODVHeadDeleteDialogComponent, ODVHeadDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CasoinODVHeadModule {}
