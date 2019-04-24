import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IODARow } from 'app/shared/model/oda-row.model';

@Component({
    selector: 'jhi-oda-row-detail',
    templateUrl: './oda-row-detail.component.html'
})
export class ODARowDetailComponent implements OnInit {
    oDARow: IODARow;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDARow }) => {
            this.oDARow = oDARow;
        });
    }

    previousState() {
        window.history.back();
    }
}
