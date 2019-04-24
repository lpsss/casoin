/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODVRowDetailComponent } from 'app/entities/odv-row/odv-row-detail.component';
import { ODVRow } from 'app/shared/model/odv-row.model';

describe('Component Tests', () => {
    describe('ODVRow Management Detail Component', () => {
        let comp: ODVRowDetailComponent;
        let fixture: ComponentFixture<ODVRowDetailComponent>;
        const route = ({ data: of({ oDVRow: new ODVRow(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVRowDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ODVRowDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODVRowDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oDVRow).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
