/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODARowDetailComponent } from 'app/entities/oda-row/oda-row-detail.component';
import { ODARow } from 'app/shared/model/oda-row.model';

describe('Component Tests', () => {
    describe('ODARow Management Detail Component', () => {
        let comp: ODARowDetailComponent;
        let fixture: ComponentFixture<ODARowDetailComponent>;
        const route = ({ data: of({ oDARow: new ODARow(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODARowDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ODARowDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODARowDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oDARow).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
