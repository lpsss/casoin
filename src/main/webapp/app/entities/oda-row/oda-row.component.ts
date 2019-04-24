import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IODARow } from 'app/shared/model/oda-row.model';
import { AccountService } from 'app/core';
import { ODARowService } from './oda-row.service';

@Component({
    selector: 'jhi-oda-row',
    templateUrl: './oda-row.component.html'
})
export class ODARowComponent implements OnInit, OnDestroy {
    oDARows: IODARow[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected oDARowService: ODARowService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.oDARowService
            .query()
            .pipe(
                filter((res: HttpResponse<IODARow[]>) => res.ok),
                map((res: HttpResponse<IODARow[]>) => res.body)
            )
            .subscribe(
                (res: IODARow[]) => {
                    this.oDARows = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInODARows();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IODARow) {
        return item.id;
    }

    registerChangeInODARows() {
        this.eventSubscriber = this.eventManager.subscribe('oDARowListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
