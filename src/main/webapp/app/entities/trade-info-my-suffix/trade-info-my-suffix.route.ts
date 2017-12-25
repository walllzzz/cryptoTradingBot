import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TradeInfoMySuffixComponent } from './trade-info-my-suffix.component';
import { TradeInfoMySuffixDetailComponent } from './trade-info-my-suffix-detail.component';
import { TradeInfoMySuffixPopupComponent } from './trade-info-my-suffix-dialog.component';
import { TradeInfoMySuffixDeletePopupComponent } from './trade-info-my-suffix-delete-dialog.component';

@Injectable()
export class TradeInfoMySuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const tradeInfoRoute: Routes = [
    {
        path: 'trade-info-my-suffix',
        component: TradeInfoMySuffixComponent,
        resolve: {
            'pagingParams': TradeInfoMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TradeInfos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'trade-info-my-suffix/:id',
        component: TradeInfoMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TradeInfos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tradeInfoPopupRoute: Routes = [
    {
        path: 'trade-info-my-suffix-new',
        component: TradeInfoMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TradeInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trade-info-my-suffix/:id/edit',
        component: TradeInfoMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TradeInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trade-info-my-suffix/:id/delete',
        component: TradeInfoMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TradeInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
