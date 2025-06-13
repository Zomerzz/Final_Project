import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Libro } from "../model/Libro";

@Injectable({
    providedIn:"root"
})
export class LibroService{
    private _url:string = "http://localhost:8080/api/libro";
    private _http = inject(HttpClient);


    findAll(): Observable<Libro[]> {
        return this._http.get<Libro[]>(this._url);
    }
    // deleteLibro(libroId: number): Observable<void>{
    //     return this._http.delete<void>(`${this._url}/${libroId}`);
    // }
    findByName(titolo:string|undefined):  Observable<Libro[]>{
        return this._http.get<Libro[]>(`${this._url}?titolo=${titolo}`);
    }
    findByFilters(queryString:string): Observable<Libro[]>{
        return this._http.get<Libro[]>(`${this._url}${queryString}`);
    }

}
