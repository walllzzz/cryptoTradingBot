import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { TradeInfoMySuffix } from './trade-info-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TradeInfoMySuffixService {

    private resourceUrl = SERVER_API_URL + 'api/trade-infos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(tradeInfo: TradeInfoMySuffix): Observable<TradeInfoMySuffix> {
        const copy = this.convert(tradeInfo);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(tradeInfo: TradeInfoMySuffix): Observable<TradeInfoMySuffix> {
        const copy = this.convert(tradeInfo);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: string): Observable<TradeInfoMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to TradeInfoMySuffix.
     */
    private convertItemFromServer(json: any): TradeInfoMySuffix {
        const entity: TradeInfoMySuffix = Object.assign(new TradeInfoMySuffix(), json);
        entity.time = this.dateUtils
            .convertDateTimeFromServer(json.time);
        return entity;
    }

    /**
     * Convert a TradeInfoMySuffix to a JSON which can be sent to the server.
     */
    private convert(tradeInfo: TradeInfoMySuffix): TradeInfoMySuffix {
        const copy: TradeInfoMySuffix = Object.assign({}, tradeInfo);

        copy.time = this.dateUtils.toDate(tradeInfo.time);
        return copy;
    }
}
