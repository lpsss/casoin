import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ODVRow } from 'app/shared/model/odv-row.model';
import { ODVRowService } from './odv-row.service';
import { ODVRowComponent } from './odv-row.component';
import { ODVRowDetailComponent } from './odv-row-detail.component';
import { ODVRowUpdateComponent } from './odv-row-update.component';
import { ODVRowDeletePopupComponent } from './odv-row-delete-dialog.component';
import { IODVRow } from 'app/shared/model/odv-row.model';

@Injectable({ providedIn: 'root' })
export class ODVRowResolve implements Resolve<IODVRow> {
    constructor(private service: ODVRowService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IODVRow> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ODVRow>) => response.ok),
                map((oDVRow: HttpResponse<ODVRow>) => oDVRow.body)
            );
        }
        return of(new ODVRow());
    }
}

export const oDVRowRoute: Routes = [
    {
        path: '',
        component: ODVRowComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVRows'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ODVRowDetailComponent,
        resolve: {
            oDVRow: ODVRowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVRows'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ODVRowUpdateComponent,
        resolve: {
            oDVRow: ODVRowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVRows'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ODVRowUpdateComponent,
        resolve: {
            oDVRow: ODVRowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVRows'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const oDVRowPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ODVRowDeletePopupComponent,
        resolve: {
            oDVRow: ODVRowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVRows'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
