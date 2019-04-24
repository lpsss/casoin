import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IODARow } from 'app/shared/model/oda-row.model';
import { ODARowService } from './oda-row.service';

@Component({
    selector: 'jhi-oda-row-delete-dialog',
    templateUrl: './oda-row-delete-dialog.component.html'
})
export class ODARowDeleteDialogComponent {
    oDARow: IODARow;

    constructor(protected oDARowService: ODARowService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.oDARowService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'oDARowListModification',
                content: 'Deleted an oDARow'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-oda-row-delete-popup',
    template: ''
})
export class ODARowDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDARow }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ODARowDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.oDARow = oDARow;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/oda-row', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/oda-row', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
