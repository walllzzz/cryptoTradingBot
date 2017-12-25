import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CryptoTradingBotTradeInfoMySuffixModule } from './trade-info-my-suffix/trade-info-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        CryptoTradingBotTradeInfoMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CryptoTradingBotEntityModule {}
