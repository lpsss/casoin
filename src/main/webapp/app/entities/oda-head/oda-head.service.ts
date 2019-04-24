import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IODAHead } from 'app/shared/model/oda-head.model';

type EntityResponseType = HttpResponse<IODAHead>;
type EntityArrayResponseType = HttpResponse<IODAHead[]>;

@Injectable({ providedIn: 'root' })
export class ODAHeadService {
    public resourceUrl = SERVER_API_URL + 'api/oda-heads';

    constructor(protected http: HttpClient) {}

    create(oDAHead: IODAHead): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(oDAHead);
        return this.http
            .post<IODAHead>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(oDAHead: IODAHead): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(oDAHead);
        return this.http
            .put<IODAHead>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IODAHead>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IODAHead[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(oDAHead: IODAHead): IODAHead {
        const copy: IODAHead = Object.assign({}, oDAHead, {
            dataFattura: oDAHead.dataFattura != null && oDAHead.dataFattura.isValid() ? oDAHead.dataFattura.format(DATE_FORMAT) : null
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
            res.body.forEach((oDAHead: IODAHead) => {
                oDAHead.dataFattura = oDAHead.dataFattura != null ? moment(oDAHead.dataFattura) : null;
            });
        }
        return res;
    }
}
