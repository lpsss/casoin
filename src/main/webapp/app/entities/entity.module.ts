import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'odv-head',
                loadChildren: './odv-head/odv-head.module#CasoinODVHeadModule'
            },
            {
                path: 'odv-row',
                loadChildren: './odv-row/odv-row.module#CasoinODVRowModule'
            },
            {
                path: 'oda-head',
                loadChildren: './oda-head/oda-head.module#CasoinODAHeadModule'
            },
            {
                path: 'oda-row',
                loadChildren: './oda-row/oda-row.module#CasoinODARowModule'
            },
            {
                path: 'supplier',
                loadChildren: './supplier/supplier.module#CasoinSupplierModule'
            },
            {
                path: 'customer',
                loadChildren: './customer/customer.module#CasoinCustomerModule'
            },
            {
                path: 'product',
                loadChildren: './product/product.module#CasoinProductModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CasoinEntityModule {}
