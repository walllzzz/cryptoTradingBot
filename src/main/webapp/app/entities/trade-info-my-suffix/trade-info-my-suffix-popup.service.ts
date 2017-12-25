import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { TradeInfoMySuffix } from './trade-info-my-suffix.model';
import { TradeInfoMySuffixService } from './trade-info-my-suffix.service';

@Injectable()
export class TradeInfoMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private tradeInfoService: TradeInfoMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.tradeInfoService.find(id).subscribe((tradeInfo) => {
                    tradeInfo.time = this.datePipe
                        .transform(tradeInfo.time, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.tradeInfoModalRef(component, tradeInfo);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tradeInfoModalRef(component, new TradeInfoMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tradeInfoModalRef(component: Component, tradeInfo: TradeInfoMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tradeInfo = tradeInfo;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
