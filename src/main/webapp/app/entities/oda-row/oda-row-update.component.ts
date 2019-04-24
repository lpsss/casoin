import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IODARow } from 'app/shared/model/oda-row.model';
import { ODARowService } from './oda-row.service';
import { IODAHead } from 'app/shared/model/oda-head.model';
import { ODAHeadService } from 'app/entities/oda-head';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
    selector: 'jhi-oda-row-update',
    templateUrl: './oda-row-update.component.html'
})
export class ODARowUpdateComponent implements OnInit {
    oDARow: IODARow;
    isSaving: boolean;

    odaheads: IODAHead[];

    products: IProduct[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected oDARowService: ODARowService,
        protected oDAHeadService: ODAHeadService,
        protected productService: ProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ oDARow }) => {
            this.oDARow = oDARow;
        });
        this.oDAHeadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IODAHead[]>) => mayBeOk.ok),
                map((response: HttpResponse<IODAHead[]>) => response.body)
            )
            .subscribe((res: IODAHead[]) => (this.odaheads = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        if (this.oDARow.id !== undefined) {
            this.subscribeToSaveResponse(this.oDARowService.update(this.oDARow));
        } else {
            this.subscribeToSaveResponse(this.oDARowService.create(this.oDARow));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IODARow>>) {
        result.subscribe((res: HttpResponse<IODARow>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackODAHeadById(index: number, item: IODAHead) {
        return item.id;
    }

    trackProductById(index: number, item: IProduct) {
        return item.id;
    }
}
