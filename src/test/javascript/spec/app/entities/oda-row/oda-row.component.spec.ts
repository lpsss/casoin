/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CasoinTestModule } from '../../../test.module';
import { ODARowComponent } from 'app/entities/oda-row/oda-row.component';
import { ODARowService } from 'app/entities/oda-row/oda-row.service';
import { ODARow } from 'app/shared/model/oda-row.model';

describe('Component Tests', () => {
    describe('ODARow Management Component', () => {
        let comp: ODARowComponent;
        let fixture: ComponentFixture<ODARowComponent>;
        let service: ODARowService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODARowComponent],
                providers: []
            })
                .overrideTemplate(ODARowComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODARowComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODARowService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ODARow(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.oDARows[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
