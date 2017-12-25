import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TradeInfoMySuffix } from './trade-info-my-suffix.model';
import { TradeInfoMySuffixPopupService } from './trade-info-my-suffix-popup.service';
import { TradeInfoMySuffixService } from './trade-info-my-suffix.service';

@Component({
    selector: 'jhi-trade-info-my-suffix-delete-dialog',
    templateUrl: './trade-info-my-suffix-delete-dialog.component.html'
})
export class TradeInfoMySuffixDeleteDialogComponent {

    tradeInfo: TradeInfoMySuffix;

    constructor(
        private tradeInfoService: TradeInfoMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.tradeInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tradeInfoListModification',
                content: 'Deleted an tradeInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-trade-info-my-suffix-delete-popup',
    template: ''
})
export class TradeInfoMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tradeInfoPopupService: TradeInfoMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tradeInfoPopupService
                .open(TradeInfoMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
