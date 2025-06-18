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
    loadTags(): Observable<Tag[]> {
        return this._http.get<Tag[]>(`${this._url}`);
    }

    loadByIsGenere(isGenere:boolean): Observable<Tag[]> {
        return this._http.get<Tag[]>(`${this._url}/genere/${isGenere}`);
    }

    loadTagsByOperaId(id:number, tipoMedia:string): Observable<Tag[]>{
        return this._http.get<Tag[]>(`${this._url}/${tipoMedia}/${id}`);
    }
}