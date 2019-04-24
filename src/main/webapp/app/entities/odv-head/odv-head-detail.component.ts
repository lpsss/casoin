import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IODVHead } from 'app/shared/model/odv-head.model';

@Component({
    selector: 'jhi-odv-head-detail',
    templateUrl: './odv-head-detail.component.html'
})
export class ODVHeadDetailComponent implements OnInit {
    oDVHead: IODVHead;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDVHead }) => {
            this.oDVHead = oDVHead;
        });
    }

    previousState() {
        window.history.back();
    }
}
