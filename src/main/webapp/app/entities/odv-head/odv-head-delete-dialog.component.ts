import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IODVHead } from 'app/shared/model/odv-head.model';
import { ODVHeadService } from './odv-head.service';

@Component({
    selector: 'jhi-odv-head-delete-dialog',
    templateUrl: './odv-head-delete-dialog.component.html'
})
export class ODVHeadDeleteDialogComponent {
    oDVHead: IODVHead;

    constructor(protected oDVHeadService: ODVHeadService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.oDVHeadService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'oDVHeadListModification',
                content: 'Deleted an oDVHead'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-odv-head-delete-popup',
    template: ''
})
export class ODVHeadDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDVHead }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ODVHeadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.oDVHead = oDVHead;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/odv-head', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/odv-head', { outlets: { popup: null } }]);
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
