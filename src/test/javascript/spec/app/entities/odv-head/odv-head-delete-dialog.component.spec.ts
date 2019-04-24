/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CasoinTestModule } from '../../../test.module';
import { ODVHeadDeleteDialogComponent } from 'app/entities/odv-head/odv-head-delete-dialog.component';
import { ODVHeadService } from 'app/entities/odv-head/odv-head.service';

describe('Component Tests', () => {
    describe('ODVHead Management Delete Component', () => {
        let comp: ODVHeadDeleteDialogComponent;
        let fixture: ComponentFixture<ODVHeadDeleteDialogComponent>;
        let service: ODVHeadService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVHeadDeleteDialogComponent]
            })
                .overrideTemplate(ODVHeadDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODVHeadDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODVHeadService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
