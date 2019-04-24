/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODARowUpdateComponent } from 'app/entities/oda-row/oda-row-update.component';
import { ODARowService } from 'app/entities/oda-row/oda-row.service';
import { ODARow } from 'app/shared/model/oda-row.model';

describe('Component Tests', () => {
    describe('ODARow Management Update Component', () => {
        let comp: ODARowUpdateComponent;
        let fixture: ComponentFixture<ODARowUpdateComponent>;
        let service: ODARowService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODARowUpdateComponent]
            })
                .overrideTemplate(ODARowUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODARowUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODARowService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ODARow(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDARow = entity;
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
                    const entity = new ODARow();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDARow = entity;
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
