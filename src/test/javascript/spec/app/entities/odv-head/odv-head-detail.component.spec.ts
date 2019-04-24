/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODVHeadDetailComponent } from 'app/entities/odv-head/odv-head-detail.component';
import { ODVHead } from 'app/shared/model/odv-head.model';

describe('Component Tests', () => {
    describe('ODVHead Management Detail Component', () => {
        let comp: ODVHeadDetailComponent;
        let fixture: ComponentFixture<ODVHeadDetailComponent>;
        const route = ({ data: of({ oDVHead: new ODVHead(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODVHeadDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ODVHeadDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODVHeadDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oDVHead).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
