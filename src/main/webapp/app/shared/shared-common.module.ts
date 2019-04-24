import { NgModule } from '@angular/core';

import { CasoinSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [CasoinSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [CasoinSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CasoinSharedCommonModule {}
