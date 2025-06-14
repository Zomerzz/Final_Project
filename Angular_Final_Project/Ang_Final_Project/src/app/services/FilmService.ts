import { inject, Injectable } from '@angular/core';
import { Film } from '../model/Film';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { PageResponse } from '../model/PageResponse';

@Injectable({
  providedIn: 'root'
})
export class FilmService{
    private _url="http://localhost:8080/api/film";
    private _http = inject(HttpClient);

    
    findAll(): Observable<Film[]> {
            return this._http.get<Film[]>(this._url);
        }
        // deleteToDo(libroId: number): Observable<void>{
        //     return this._http.delete<void>(`${this._url}/${libroId}`);
        // }
    findByName(titolo:string|undefined):  Observable<Film[]>{
            return this._http.get<PageResponse<Film>>(`${this._url}?titolo=${titolo}`).pipe(
                map(page => page.content));
        }
    findByFilters(queryString:string): Observable<Film[]>{
            return this._http.get<PageResponse<Film>>(`${this._url}${queryString}`).pipe(
                map(page => page.content));
        }
}