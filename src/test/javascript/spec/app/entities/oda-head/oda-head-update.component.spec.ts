/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODAHeadUpdateComponent } from 'app/entities/oda-head/oda-head-update.component';
import { ODAHeadService } from 'app/entities/oda-head/oda-head.service';
import { ODAHead } from 'app/shared/model/oda-head.model';

describe('Component Tests', () => {
    describe('ODAHead Management Update Component', () => {
        let comp: ODAHeadUpdateComponent;
        let fixture: ComponentFixture<ODAHeadUpdateComponent>;
        let service: ODAHeadService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODAHeadUpdateComponent]
            })
                .overrideTemplate(ODAHeadUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODAHeadUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODAHeadService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ODAHead(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDAHead = entity;
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
                    const entity = new ODAHead();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDAHead = entity;
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
