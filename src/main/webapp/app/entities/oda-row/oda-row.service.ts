import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IODARow } from 'app/shared/model/oda-row.model';

type EntityResponseType = HttpResponse<IODARow>;
type EntityArrayResponseType = HttpResponse<IODARow[]>;

@Injectable({ providedIn: 'root' })
export class ODARowService {
    public resourceUrl = SERVER_API_URL + 'api/oda-rows';

    constructor(protected http: HttpClient) {}

    create(oDARow: IODARow): Observable<EntityResponseType> {
        return this.http.post<IODARow>(this.resourceUrl, oDARow, { observe: 'response' });
    }

    update(oDARow: IODARow): Observable<EntityResponseType> {
        return this.http.put<IODARow>(this.resourceUrl, oDARow, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IODARow>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IODARow[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
