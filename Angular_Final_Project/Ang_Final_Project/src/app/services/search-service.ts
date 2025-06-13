import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { SearchModel } from "../model/SearchModel";

@Injectable({
    providedIn: 'root'
})
export class SearchService {
    private _url: string = 'http://localhost:8080/api/ricerca-per-nome';
    private _http: HttpClient = inject(HttpClient);
    getOperaBySearchModel( searchModel: Partial<SearchModel>): Observable<SearchModel[]> {
        return this._http.get<SearchModel[]>(this._url);
    }
}