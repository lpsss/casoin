/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CasoinTestModule } from '../../../test.module';
import { ODVHeadComponent } from 'app/entities/odv-head/odv-head.component';
import { ODVHeadService } from 'app/entities/odv-head/odv-head.service';
import { ODVHead } from 'app/shared/model/odv-head.model';

describe('Component Tests', () => {
    describe('ODVHead Management Component', () => {
        let comp: ODVHeadComponent;
        let fixture: ComponentFixture<ODVHeadComponent>;
        let service: ODVHeadService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVHeadComponent],
                providers: []
            })
                .overrideTemplate(ODVHeadComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODVHeadComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODVHeadService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ODVHead(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.oDVHeads[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
