/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CryptoTradingBotTestModule } from '../../../test.module';
import { TradeInfoMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix-delete-dialog.component';
import { TradeInfoMySuffixService } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.service';

describe('Component Tests', () => {

    describe('TradeInfoMySuffix Management Delete Component', () => {
        let comp: TradeInfoMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TradeInfoMySuffixDeleteDialogComponent>;
        let service: TradeInfoMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CryptoTradingBotTestModule],
                declarations: [TradeInfoMySuffixDeleteDialogComponent],
                providers: [
                    TradeInfoMySuffixService
                ]
            })
            .overrideTemplate(TradeInfoMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TradeInfoMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TradeInfoMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete('123');
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith('123');
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
