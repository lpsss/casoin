import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IODVRow } from 'app/shared/model/odv-row.model';

type EntityResponseType = HttpResponse<IODVRow>;
type EntityArrayResponseType = HttpResponse<IODVRow[]>;

@Injectable({ providedIn: 'root' })
export class ODVRowService {
    public resourceUrl = SERVER_API_URL + 'api/odv-rows';

    constructor(protected http: HttpClient) {}

    create(oDVRow: IODVRow): Observable<EntityResponseType> {
        return this.http.post<IODVRow>(this.resourceUrl, oDVRow, { observe: 'response' });
    }

    update(oDVRow: IODVRow): Observable<EntityResponseType> {
        return this.http.put<IODVRow>(this.resourceUrl, oDVRow, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IODVRow>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IODVRow[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
