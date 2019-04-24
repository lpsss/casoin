import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IODAHead } from 'app/shared/model/oda-head.model';
import { AccountService } from 'app/core';
import { ODAHeadService } from './oda-head.service';

@Component({
    selector: 'jhi-oda-head',
    templateUrl: './oda-head.component.html'
})
export class ODAHeadComponent implements OnInit, OnDestroy {
    oDAHeads: IODAHead[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected oDAHeadService: ODAHeadService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.oDAHeadService
            .query()
            .pipe(
                filter((res: HttpResponse<IODAHead[]>) => res.ok),
                map((res: HttpResponse<IODAHead[]>) => res.body)
            )
            .subscribe(
                (res: IODAHead[]) => {
                    this.oDAHeads = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInODAHeads();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IODAHead) {
        return item.id;
    }

    registerChangeInODAHeads() {
        this.eventSubscriber = this.eventManager.subscribe('oDAHeadListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
