import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CryptoTradingBotSharedModule } from '../../shared';
import {
    TradeInfoMySuffixService,
    TradeInfoMySuffixPopupService,
    TradeInfoMySuffixComponent,
    TradeInfoMySuffixDetailComponent,
    TradeInfoMySuffixDialogComponent,
    TradeInfoMySuffixPopupComponent,
    TradeInfoMySuffixDeletePopupComponent,
    TradeInfoMySuffixDeleteDialogComponent,
    tradeInfoRoute,
    tradeInfoPopupRoute,
    TradeInfoMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tradeInfoRoute,
    ...tradeInfoPopupRoute,
];

@NgModule({
    imports: [
        CryptoTradingBotSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TradeInfoMySuffixComponent,
        TradeInfoMySuffixDetailComponent,
        TradeInfoMySuffixDialogComponent,
        TradeInfoMySuffixDeleteDialogComponent,
        TradeInfoMySuffixPopupComponent,
        TradeInfoMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TradeInfoMySuffixComponent,
        TradeInfoMySuffixDialogComponent,
        TradeInfoMySuffixPopupComponent,
        TradeInfoMySuffixDeleteDialogComponent,
        TradeInfoMySuffixDeletePopupComponent,
    ],
    providers: [
        TradeInfoMySuffixService,
        TradeInfoMySuffixPopupService,
        TradeInfoMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CryptoTradingBotTradeInfoMySuffixModule {}
