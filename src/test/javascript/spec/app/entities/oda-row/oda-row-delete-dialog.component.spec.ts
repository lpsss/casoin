/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CasoinTestModule } from '../../../test.module';
import { ODARowDeleteDialogComponent } from 'app/entities/oda-row/oda-row-delete-dialog.component';
import { ODARowService } from 'app/entities/oda-row/oda-row.service';

describe('Component Tests', () => {
    describe('ODARow Management Delete Component', () => {
        let comp: ODARowDeleteDialogComponent;
        let fixture: ComponentFixture<ODARowDeleteDialogComponent>;
        let service: ODARowService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODARowDeleteDialogComponent]
            })
                .overrideTemplate(ODARowDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODARowDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODARowService);
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
