import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IODVRow } from 'app/shared/model/odv-row.model';
import { ODVRowService } from './odv-row.service';

@Component({
    selector: 'jhi-odv-row-delete-dialog',
    templateUrl: './odv-row-delete-dialog.component.html'
})
export class ODVRowDeleteDialogComponent {
    oDVRow: IODVRow;

    constructor(protected oDVRowService: ODVRowService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.oDVRowService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'oDVRowListModification',
                content: 'Deleted an oDVRow'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-odv-row-delete-popup',
    template: ''
})
export class ODVRowDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDVRow }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ODVRowDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.oDVRow = oDVRow;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/odv-row', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/odv-row', { outlets: { popup: null } }]);
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
