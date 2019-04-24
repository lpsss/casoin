import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IODAHead } from 'app/shared/model/oda-head.model';
import { ODAHeadService } from './oda-head.service';

@Component({
    selector: 'jhi-oda-head-delete-dialog',
    templateUrl: './oda-head-delete-dialog.component.html'
})
export class ODAHeadDeleteDialogComponent {
    oDAHead: IODAHead;

    constructor(protected oDAHeadService: ODAHeadService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.oDAHeadService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'oDAHeadListModification',
                content: 'Deleted an oDAHead'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-oda-head-delete-popup',
    template: ''
})
export class ODAHeadDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oDAHead }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ODAHeadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.oDAHead = oDAHead;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/oda-head', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/oda-head', { outlets: { popup: null } }]);
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
