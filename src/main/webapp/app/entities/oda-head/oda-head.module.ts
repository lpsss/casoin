import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CasoinSharedModule } from 'app/shared';
import {
    ODAHeadComponent,
    ODAHeadDetailComponent,
    ODAHeadUpdateComponent,
    ODAHeadDeletePopupComponent,
    ODAHeadDeleteDialogComponent,
    oDAHeadRoute,
    oDAHeadPopupRoute
} from './';

const ENTITY_STATES = [...oDAHeadRoute, ...oDAHeadPopupRoute];

@NgModule({
    imports: [CasoinSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ODAHeadComponent,
        ODAHeadDetailComponent,
        ODAHeadUpdateComponent,
        ODAHeadDeleteDialogComponent,
        ODAHeadDeletePopupComponent
    ],
    entryComponents: [ODAHeadComponent, ODAHeadUpdateComponent, ODAHeadDeleteDialogComponent, ODAHeadDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CasoinODAHeadModule {}
