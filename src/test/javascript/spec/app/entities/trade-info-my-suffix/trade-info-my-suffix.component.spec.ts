/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { CryptoTradingBotTestModule } from '../../../test.module';
import { TradeInfoMySuffixComponent } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.component';
import { TradeInfoMySuffixService } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.service';
import { TradeInfoMySuffix } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.model';

describe('Component Tests', () => {

    describe('TradeInfoMySuffix Management Component', () => {
        let comp: TradeInfoMySuffixComponent;
        let fixture: ComponentFixture<TradeInfoMySuffixComponent>;
        let service: TradeInfoMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CryptoTradingBotTestModule],
                declarations: [TradeInfoMySuffixComponent],
                providers: [
                    TradeInfoMySuffixService
                ]
            })
            .overrideTemplate(TradeInfoMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TradeInfoMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TradeInfoMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new TradeInfoMySuffix('123')],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tradeInfos[0]).toEqual(jasmine.objectContaining({id: '123'}));
            });
        });
    });

});
