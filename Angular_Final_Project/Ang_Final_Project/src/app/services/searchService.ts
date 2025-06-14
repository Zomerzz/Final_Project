import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Tag } from "../model/Tag";

@Injectable({
    providedIn: 'root'
})
export class TagService {
    private _url: string = 'http://localhost:8080/api/tag';
    private _http: HttpClient = inject(HttpClient);
    loadTagByMedia(mediaType: string): Observable<Tag[]> {
        return this._http.get<Tag[]>(`${this._url}?mediaType=${mediaType}`);
    }
}