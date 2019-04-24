/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CasoinTestModule } from '../../../test.module';
import { ODAHeadDetailComponent } from 'app/entities/oda-head/oda-head-detail.component';
import { ODAHead } from 'app/shared/model/oda-head.model';

describe('Component Tests', () => {
    describe('ODAHead Management Detail Component', () => {
        let comp: ODAHeadDetailComponent;
        let fixture: ComponentFixture<ODAHeadDetailComponent>;
        const route = ({ data: of({ oDAHead: new ODAHead(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CasoinTestModule],
                declarations: [ODAHeadDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ODAHeadDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ODAHeadDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oDAHead).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
