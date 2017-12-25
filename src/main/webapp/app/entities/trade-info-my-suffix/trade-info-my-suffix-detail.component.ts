import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TradeInfoMySuffix } from './trade-info-my-suffix.model';
import { TradeInfoMySuffixService } from './trade-info-my-suffix.service';

@Component({
    selector: 'jhi-trade-info-my-suffix-detail',
    templateUrl: './trade-info-my-suffix-detail.component.html'
})
export class TradeInfoMySuffixDetailComponent implements OnInit, OnDestroy {

    tradeInfo: TradeInfoMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tradeInfoService: TradeInfoMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTradeInfos();
    }

    load(id) {
        this.tradeInfoService.find(id).subscribe((tradeInfo) => {
            this.tradeInfo = tradeInfo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTradeInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tradeInfoListModification',
            (response) => this.load(this.tradeInfo.id)
        );
    }
}
