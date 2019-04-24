/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CasoinTestModule } from '../../../test.module';
import { ODVRowDeleteDialogComponent } from 'app/entities/odv-row/odv-row-delete-dialog.component';
import { ODVRowService } from 'app/entities/odv-row/odv-row.service';

describe('Component Tests', () => {
    describe('ODVRow Management Delete Component', () => {
        let comp: ODVRowDeleteDialogComponent;
        let fixture: ComponentFixture<ODVRowDeleteDialogComponent>;
        let service: ODVRowService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVRowDeleteDialogComponent]
            })
                .overrideTemplate(ODVRowDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODVRowDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODVRowService);
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
