import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IODAHead } from 'app/shared/model/oda-head.model';

@Component({
    selector: 'jhi-oda-head-detail',
    templateUrl: './oda-head-detail.component.html'
})
export class ODAHeadDetailComponent implements OnInit {
    oDAHead: IODAHead;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDAHead }) => {
            this.oDAHead = oDAHead;
        });
    }

    previousState() {
        window.history.back();
    }
}
