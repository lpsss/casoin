/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODVRowUpdateComponent } from 'app/entities/odv-row/odv-row-update.component';
import { ODVRowService } from 'app/entities/odv-row/odv-row.service';
import { ODVRow } from 'app/shared/model/odv-row.model';

describe('Component Tests', () => {
    describe('ODVRow Management Update Component', () => {
        let comp: ODVRowUpdateComponent;
        let fixture: ComponentFixture<ODVRowUpdateComponent>;
        let service: ODVRowService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVRowUpdateComponent]
            })
                .overrideTemplate(ODVRowUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODVRowUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODVRowService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ODVRow(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDVRow = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ODVRow();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDVRow = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
