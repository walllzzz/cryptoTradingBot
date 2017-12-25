/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CryptoTradingBotTestModule } from '../../../test.module';
import { TradeInfoMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix-dialog.component';
import { TradeInfoMySuffixService } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.service';
import { TradeInfoMySuffix } from '../../../../../../main/webapp/app/entities/trade-info-my-suffix/trade-info-my-suffix.model';

describe('Component Tests', () => {

    describe('TradeInfoMySuffix Management Dialog Component', () => {
        let comp: TradeInfoMySuffixDialogComponent;
        let fixture: ComponentFixture<TradeInfoMySuffixDialogComponent>;
        let service: TradeInfoMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CryptoTradingBotTestModule],
                declarations: [TradeInfoMySuffixDialogComponent],
                providers: [
                    TradeInfoMySuffixService
                ]
            })
            .overrideTemplate(TradeInfoMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TradeInfoMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TradeInfoMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TradeInfoMySuffix('123');
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.tradeInfo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tradeInfoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TradeInfoMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.tradeInfo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tradeInfoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
