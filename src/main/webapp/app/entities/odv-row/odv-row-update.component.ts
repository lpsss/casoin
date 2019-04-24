import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IODVRow } from 'app/shared/model/odv-row.model';
import { ODVRowService } from './odv-row.service';
import { IODVHead } from 'app/shared/model/odv-head.model';
import { ODVHeadService } from 'app/entities/odv-head';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
    selector: 'jhi-odv-row-update',
    templateUrl: './odv-row-update.component.html'
})
export class ODVRowUpdateComponent implements OnInit {
    oDVRow: IODVRow;
    isSaving: boolean;

    odvheads: IODVHead[];

    products: IProduct[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected oDVRowService: ODVRowService,
        protected oDVHeadService: ODVHeadService,
        protected productService: ProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ oDVRow }) => {
            this.oDVRow = oDVRow;
        });
        this.oDVHeadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IODVHead[]>) => mayBeOk.ok),
                map((response: HttpResponse<IODVHead[]>) => response.body)
            )
            .subscribe((res: IODVHead[]) => (this.odvheads = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.productService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
                map((response: HttpResponse<IProduct[]>) => response.body)
            )
            .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.oDVRow.id !== undefined) {
            this.subscribeToSaveResponse(this.oDVRowService.update(this.oDVRow));
        } else {
            this.subscribeToSaveResponse(this.oDVRowService.create(this.oDVRow));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IODVRow>>) {
        result.subscribe((res: HttpResponse<IODVRow>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackODVHeadById(index: number, item: IODVHead) {
        return item.id;
    }

    trackProductById(index: number, item: IProduct) {
        return item.id;
    }
}
