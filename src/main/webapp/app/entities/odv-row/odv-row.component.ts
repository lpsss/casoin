import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IODVRow } from 'app/shared/model/odv-row.model';
import { AccountService } from 'app/core';
import { ODVRowService } from './odv-row.service';

@Component({
    selector: 'jhi-odv-row',
    templateUrl: './odv-row.component.html'
})
export class ODVRowComponent implements OnInit, OnDestroy {
    oDVRows: IODVRow[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected oDVRowService: ODVRowService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.oDVRowService
            .query()
            .pipe(
                filter((res: HttpResponse<IODVRow[]>) => res.ok),
                map((res: HttpResponse<IODVRow[]>) => res.body)
            )
            .subscribe(
                (res: IODVRow[]) => {
                    this.oDVRows = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInODVRows();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IODVRow) {
        return item.id;
    }

    registerChangeInODVRows() {
        this.eventSubscriber = this.eventManager.subscribe('oDVRowListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
