/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { CryptoTradingBotTestModule } from '../../../test.module';
import { TradeInfoMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix-detail.component';
import { TradeInfoMySuffixService } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.service';
import { TradeInfoMySuffix } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.model';

describe('Component Tests', () => {

    describe('TradeInfoMySuffix Management Detail Component', () => {
        let comp: TradeInfoMySuffixDetailComponent;
        let fixture: ComponentFixture<TradeInfoMySuffixDetailComponent>;
        let service: TradeInfoMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CryptoTradingBotTestModule],
                declarations: [TradeInfoMySuffixDetailComponent],
                providers: [
                    TradeInfoMySuffixService
                ]
            })
            .overrideTemplate(TradeInfoMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TradeInfoMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TradeInfoMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new TradeInfoMySuffix('123')));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith('123');
                expect(comp.tradeInfo).toEqual(jasmine.objectContaining({id: '123'}));
            });
        });
    });

});
