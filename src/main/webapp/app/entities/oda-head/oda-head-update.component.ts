import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IODAHead } from 'app/shared/model/oda-head.model';
import { ODAHeadService } from './oda-head.service';

@Component({
    selector: 'jhi-oda-head-update',
    templateUrl: './oda-head-update.component.html'
})
export class ODAHeadUpdateComponent implements OnInit {
    oDAHead: IODAHead;
    isSaving: boolean;
    dataFatturaDp: any;

    constructor(protected oDAHeadService: ODAHeadService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ oDAHead }) => {
            this.oDAHead = oDAHead;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.oDAHead.id !== undefined) {
            this.subscribeToSaveResponse(this.oDAHeadService.update(this.oDAHead));
        } else {
            this.subscribeToSaveResponse(this.oDAHeadService.create(this.oDAHead));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IODAHead>>) {
        result.subscribe((res: HttpResponse<IODAHead>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
