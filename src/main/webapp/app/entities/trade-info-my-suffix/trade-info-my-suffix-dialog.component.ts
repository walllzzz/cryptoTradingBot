import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TradeInfoMySuffix } from './trade-info-my-suffix.model';
import { TradeInfoMySuffixPopupService } from './trade-info-my-suffix-popup.service';
import { TradeInfoMySuffixService } from './trade-info-my-suffix.service';

@Component({
    selector: 'jhi-trade-info-my-suffix-dialog',
    templateUrl: './trade-info-my-suffix-dialog.component.html'
})
export class TradeInfoMySuffixDialogComponent implements OnInit {

    tradeInfo: TradeInfoMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tradeInfoService: TradeInfoMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tradeInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tradeInfoService.update(this.tradeInfo));
        } else {
            this.subscribeToSaveResponse(
                this.tradeInfoService.create(this.tradeInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<TradeInfoMySuffix>) {
        result.subscribe((res: TradeInfoMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TradeInfoMySuffix) {
        this.eventManager.broadcast({ name: 'tradeInfoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-trade-info-my-suffix-popup',
    template: ''
})
export class TradeInfoMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tradeInfoPopupService: TradeInfoMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tradeInfoPopupService
                    .open(TradeInfoMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tradeInfoPopupService
                    .open(TradeInfoMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
