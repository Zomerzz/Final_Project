import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { FilmVisto } from "../model/FilmVisto";
import { LibroLetto } from "../model/LibroLetto";
import { VideogiocoGiocato } from "../model/VideogiocoGiocato";

@Injectable({
    providedIn: 'root'
})
export class MediaRegistratoService {
    private _filmUrl = 'http://localhost:8080/api/film-visto'
    private _libroUrl = 'http://localhost:8080/api/libro-letto'
    private _videogiocoUrl = 'http://localhost:8080/api/videogioco-giocato'
    private _http = inject(HttpClient);

    getFilmVisti(utenteId: number): Observable<FilmVisto[]>{
        return this._http.get<FilmVisto[]>(`${this._filmUrl}?utenteId=${utenteId}`)
    }

    addFilmVisto(filmVisto: Partial<FilmVisto>): Observable<FilmVisto>{
        return this._http.post<FilmVisto>(this._filmUrl, filmVisto);
    }

    deleteFilmVisto(filmVistoId: number): Observable<void>{
        return this._http.delete<void>(`${this._filmUrl}/${filmVistoId}`)
    }

    getFilmVistoByFilmIdAndUtenteId(filmId: number, utenteId: number): Observable<FilmVisto> {
        return this._http.get<FilmVisto>(`${this._videogiocoUrl}/${filmId}/${utenteId}`);
    }
    
    getLibriLetti(utenteId: number): Observable<LibroLetto[]>{
        return this._http.get<LibroLetto[]>(`${this._libroUrl}?utenteId=${utenteId}`)
    }
    
    addLibroLetto(libroLetto: Partial<LibroLetto>): Observable<LibroLetto>{
        return this._http.post<LibroLetto>(this._libroUrl, libroLetto);
    }
    
    deleteLibroLetto(libroLettoId: number): Observable<void>{
        return this._http.delete<void>(`${this._libroUrl}/${libroLettoId}`)
    }

    getLibroLettoByLibroIdAndUtenteId(libroId: number, utenteId: number): Observable<LibroLetto> {
        return this._http.get<LibroLetto>(`${this._libroUrl}/${libroId}/${utenteId}`);
    }

    getVideogiochiGiocati(utenteId: number): Observable<VideogiocoGiocato[]>{
        return this._http.get<VideogiocoGiocato[]>(`${this._videogiocoUrl}?utenteId=${utenteId}`)
    }
    
    addVideogiocoGiocato(videogiocoGiocato: Partial<VideogiocoGiocato>): Observable<VideogiocoGiocato>{
        return this._http.post<VideogiocoGiocato>(this._videogiocoUrl, videogiocoGiocato);
    }
    
    deleteVideogiocoGiocato(videogiocoGiocatoId: number): Observable<void>{
        return this._http.delete<void>(`${this._videogiocoUrl}/${videogiocoGiocatoId}`)
    }

    getVideogiocoGiocatoByVideogiocoIdAndUtenteId(videogiocoId: number, utenteId: number): Observable<VideogiocoGiocato> {
        return this._http.get<VideogiocoGiocato>(`${this._videogiocoUrl}/${videogiocoId}/${utenteId}`);
    }
}