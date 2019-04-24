import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IODVHead } from 'app/shared/model/odv-head.model';
import { ODVHeadService } from './odv-head.service';

@Component({
    selector: 'jhi-odv-head-update',
    templateUrl: './odv-head-update.component.html'
})
export class ODVHeadUpdateComponent implements OnInit {
    oDVHead: IODVHead;
    isSaving: boolean;
    dataFatturaDp: any;

    constructor(protected oDVHeadService: ODVHeadService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ oDVHead }) => {
            this.oDVHead = oDVHead;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.oDVHead.id !== undefined) {
            this.subscribeToSaveResponse(this.oDVHeadService.update(this.oDVHead));
        } else {
            this.subscribeToSaveResponse(this.oDVHeadService.create(this.oDVHead));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IODVHead>>) {
        result.subscribe((res: HttpResponse<IODVHead>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
