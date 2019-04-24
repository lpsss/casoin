import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ODVHead } from 'app/shared/model/odv-head.model';
import { ODVHeadService } from './odv-head.service';
import { ODVHeadComponent } from './odv-head.component';
import { ODVHeadDetailComponent } from './odv-head-detail.component';
import { ODVHeadUpdateComponent } from './odv-head-update.component';
import { ODVHeadDeletePopupComponent } from './odv-head-delete-dialog.component';
import { IODVHead } from 'app/shared/model/odv-head.model';

@Injectable({ providedIn: 'root' })
export class ODVHeadResolve implements Resolve<IODVHead> {
    constructor(private service: ODVHeadService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IODVHead> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ODVHead>) => response.ok),
                map((oDVHead: HttpResponse<ODVHead>) => oDVHead.body)
            );
        }
        return of(new ODVHead());
    }
}

export const oDVHeadRoute: Routes = [
    {
        path: '',
        component: ODVHeadComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVHeads'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ODVHeadDetailComponent,
        resolve: {
            oDVHead: ODVHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVHeads'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ODVHeadUpdateComponent,
        resolve: {
            oDVHead: ODVHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVHeads'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ODVHeadUpdateComponent,
        resolve: {
            oDVHead: ODVHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVHeads'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const oDVHeadPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ODVHeadDeletePopupComponent,
        resolve: {
            oDVHead: ODVHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ODVHeads'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
