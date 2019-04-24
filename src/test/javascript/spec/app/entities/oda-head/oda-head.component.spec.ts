/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CasoinTestModule } from '../../../test.module';
import { ODAHeadComponent } from 'app/entities/oda-head/oda-head.component';
import { ODAHeadService } from 'app/entities/oda-head/oda-head.service';
import { ODAHead } from 'app/shared/model/oda-head.model';

describe('Component Tests', () => {
    describe('ODAHead Management Component', () => {
        let comp: ODAHeadComponent;
        let fixture: ComponentFixture<ODAHeadComponent>;
        let service: ODAHeadService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODAHeadComponent],
                providers: []
            })
                .overrideTemplate(ODAHeadComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODAHeadComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODAHeadService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ODAHead(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.oDAHeads[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
