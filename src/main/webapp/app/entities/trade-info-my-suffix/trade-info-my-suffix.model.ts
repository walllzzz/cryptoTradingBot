import { BaseEntity } from './../../shared';

export const enum Decision {
    'BUY',
    'SELL',
    'WATCH'
}

export class TradeInfoMySuffix implements BaseEntity {
    constructor(
        public id?: string,
        public time?: any,
        public action?: Decision,
        public lastAction?: Decision,
        public assetPrice?: number,
        public balance?: number,
    ) {
    }
}
