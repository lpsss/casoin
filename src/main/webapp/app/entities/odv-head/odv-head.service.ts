import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IODVHead } from 'app/shared/model/odv-head.model';

type EntityResponseType = HttpResponse<IODVHead>;
type EntityArrayResponseType = HttpResponse<IODVHead[]>;

@Injectable({ providedIn: 'root' })
export class ODVHeadService {
    public resourceUrl = SERVER_API_URL + 'api/odv-heads';

    constructor(protected http: HttpClient) {}

    create(oDVHead: IODVHead): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(oDVHead);
        return this.http
            .post<IODVHead>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(oDVHead: IODVHead): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(oDVHead);
        return this.http
            .put<IODVHead>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IODVHead>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IODVHead[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(oDVHead: IODVHead): IODVHead {
        const copy: IODVHead = Object.assign({}, oDVHead, {
            dataFattura: oDVHead.dataFattura != null && oDVHead.dataFattura.isValid() ? oDVHead.dataFattura.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataFattura = res.body.dataFattura != null ? moment(res.body.dataFattura) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((oDVHead: IODVHead) => {
                oDVHead.dataFattura = oDVHead.dataFattura != null ? moment(oDVHead.dataFattura) : null;
            });
        }
        return res;
    }
}
