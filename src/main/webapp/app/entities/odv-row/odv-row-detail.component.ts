import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IODVRow } from 'app/shared/model/odv-row.model';

@Component({
    selector: 'jhi-odv-row-detail',
    templateUrl: './odv-row-detail.component.html'
})
export class ODVRowDetailComponent implements OnInit {
    oDVRow: IODVRow;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDVRow }) => {
            this.oDVRow = oDVRow;
        });
    }

    previousState() {
        window.history.back();
    }
}
