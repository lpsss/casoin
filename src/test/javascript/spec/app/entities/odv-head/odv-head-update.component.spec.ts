/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODVHeadUpdateComponent } from 'app/entities/odv-head/odv-head-update.component';
import { ODVHeadService } from 'app/entities/odv-head/odv-head.service';
import { ODVHead } from 'app/shared/model/odv-head.model';

describe('Component Tests', () => {
    describe('ODVHead Management Update Component', () => {
        let comp: ODVHeadUpdateComponent;
        let fixture: ComponentFixture<ODVHeadUpdateComponent>;
        let service: ODVHeadService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVHeadUpdateComponent]
            })
                .overrideTemplate(ODVHeadUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODVHeadUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODVHeadService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ODVHead(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDVHead = entity;
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
                    const entity = new ODVHead();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oDVHead = entity;
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
