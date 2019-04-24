/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CasoinTestModule } from '../../../test.module';
import { ODVRowComponent } from 'app/entities/odv-row/odv-row.component';
import { ODVRowService } from 'app/entities/odv-row/odv-row.service';
import { ODVRow } from 'app/shared/model/odv-row.model';

describe('Component Tests', () => {
    describe('ODVRow Management Component', () => {
        let comp: ODVRowComponent;
        let fixture: ComponentFixture<ODVRowComponent>;
        let service: ODVRowService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVRowComponent],
                providers: []
            })
                .overrideTemplate(ODVRowComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ODVRowComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ODVRowService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ODVRow(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.oDVRows[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
