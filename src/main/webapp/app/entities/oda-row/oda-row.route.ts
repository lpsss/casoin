import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ODARow } from 'app/shared/model/oda-row.model';
import { ODARowService } from './oda-row.service';
import { ODARowComponent } from './oda-row.component';
import { ODARowDetailComponent } from './oda-row-detail.component';
import { ODARowUpdateComponent } from './oda-row-update.component';
import { ODARowDeletePopupComponent } from './oda-row-delete-dialog.component';
import { IODARow } from 'app/shared/model/oda-row.model';

@Injectable({ providedIn: 'root' })
export class ODARowResolve implements Resolve<IODARow> {
    constructor(private service: ODARowService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IODARow> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ODARow>) => response.ok),
                map((oDARow: HttpResponse<ODARow>) => oDARow.body)
            );
        }
        return of(new ODARow());
    }
}

export const oDARowRoute: Routes = [
    {
        path: '',
        component: ODARowComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODARows'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ODARowDetailComponent,
        resolve: {
            oDARow: ODARowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODARows'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ODARowUpdateComponent,
        resolve: {
            oDARow: ODARowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODARows'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ODARowUpdateComponent,
        resolve: {
            oDARow: ODARowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODARows'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const oDARowPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ODARowDeletePopupComponent,
        resolve: {
            oDARow: ODARowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODARows'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
