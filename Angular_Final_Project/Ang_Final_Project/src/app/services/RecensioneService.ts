import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Recensione } from "../model/Recensione";
import { RecensioneRequest } from "../model/RecensioneRequest";

@Injectable({
    providedIn: 'root'
})
export class RecensioneService{
    private _url: string = 'http://localhost:8080/api/recensione';
    private _http: HttpClient = inject(HttpClient);

    getRecensioni(mediaType:string, mediaId: number){
        return this._http.get<Recensione[]>(`${this._url}?mediaType=${mediaType}&mediaId=${mediaId}`);
    }

    // getRecensioniByFilm(filmId: number): Observable<Recensione[]> {
    //     return this._http.get<Recensione[]>(`${this._url}/film-id=${filmId}`);
    // }

    // getRecensioniByLibro(libroId: number): Observable<Recensione[]> {
    //     return this._http.get<Recensione[]>(`${this._url}/libro-id=${libroId}`);
    // }

    // getRecensioniByVideogioco(videogiocoId: number): Observable<Recensione[]> {
    //     return this._http.get<Recensione[]>(`${this._url}/videogioco-id=${videogiocoId}`);
    // }

    addRecensione(recensioneRequest: Partial<RecensioneRequest>): Observable<Recensione> {
        return this._http.post<Recensione>(this._url, recensioneRequest);
    }

    updateRecensione(updatedRecensione: Partial<Recensione>): Observable<Recensione> {
        return this._http.put<Recensione>(`${this._url}/${updatedRecensione.recensioneId}`, updatedRecensione);
    }

    deleteRecensione(id: number): Observable<void> {
        return this._http.delete<void>(`${this._url}/${id}`);
    }
}
