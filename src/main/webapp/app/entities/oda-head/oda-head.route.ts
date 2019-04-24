import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ODAHead } from 'app/shared/model/oda-head.model';
import { ODAHeadService } from './oda-head.service';
import { ODAHeadComponent } from './oda-head.component';
import { ODAHeadDetailComponent } from './oda-head-detail.component';
import { ODAHeadUpdateComponent } from './oda-head-update.component';
import { ODAHeadDeletePopupComponent } from './oda-head-delete-dialog.component';
import { IODAHead } from 'app/shared/model/oda-head.model';

@Injectable({ providedIn: 'root' })
export class ODAHeadResolve implements Resolve<IODAHead> {
    constructor(private service: ODAHeadService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IODAHead> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ODAHead>) => response.ok),
                map((oDAHead: HttpResponse<ODAHead>) => oDAHead.body)
            );
        }
        return of(new ODAHead());
    }
}

export const oDAHeadRoute: Routes = [
    {
        path: '',
        component: ODAHeadComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODAHeads'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ODAHeadDetailComponent,
        resolve: {
            oDAHead: ODAHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODAHeads'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ODAHeadUpdateComponent,
        resolve: {
            oDAHead: ODAHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODAHeads'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ODAHeadUpdateComponent,
        resolve: {
            oDAHead: ODAHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODAHeads'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const oDAHeadPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ODAHeadDeletePopupComponent,
        resolve: {
            oDAHead: ODAHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODAHeads'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
