/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CasoinTestModule } from '../../../test.module';
import { ODAHeadDeleteDialogComponent } from 'app/entities/oda-head/oda-head-delete-dialog.component';
import { ODAHeadService } from 'app/entities/oda-head/oda-head.service';

describe('Component Tests', () => {
    describe('ODAHead Management Delete Component', () => {
        let comp: ODAHeadDeleteDialogComponent;
        let fixture: ComponentFixture<ODAHeadDeleteDialogComponent>;
        let service: ODAHeadService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODAHeadDeleteDialogComponent]
            })
                .overrideTemplate(ODAHeadDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODAHeadDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODAHeadService);
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
