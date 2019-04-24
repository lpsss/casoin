import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CasoinSharedModule } from 'app/shared';
import {
    ODARowComponent,
    ODARowDetailComponent,
    ODARowUpdateComponent,
    ODARowDeletePopupComponent,
    ODARowDeleteDialogComponent,
    oDARowRoute,
    oDARowPopupRoute
} from './';

const ENTITY_STATES = [...oDARowRoute, ...oDARowPopupRoute];

@NgModule({
    imports: [CasoinSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ODARowComponent, ODARowDetailComponent, ODARowUpdateComponent, ODARowDeleteDialogComponent, ODARowDeletePopupComponent],
    entryComponents: [ODARowComponent, ODARowUpdateComponent, ODARowDeleteDialogComponent, ODARowDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CasoinODARowModule {}
