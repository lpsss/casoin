import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IODVHead } from 'app/shared/model/odv-head.model';
import { AccountService } from 'app/core';
import { ODVHeadService } from './odv-head.service';

@Component({
    selector: 'jhi-odv-head',
    templateUrl: './odv-head.component.html'
})
export class ODVHeadComponent implements OnInit, OnDestroy {
    oDVHeads: IODVHead[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected oDVHeadService: ODVHeadService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.oDVHeadService
            .query()
            .pipe(
                filter((res: HttpResponse<IODVHead[]>) => res.ok),
                map((res: HttpResponse<IODVHead[]>) => res.body)
            )
            .subscribe(
                (res: IODVHead[]) => {
                    this.oDVHeads = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInODVHeads();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IODVHead) {
        return item.id;
    }

    registerChangeInODVHeads() {
        this.eventSubscriber = this.eventManager.subscribe('oDVHeadListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
