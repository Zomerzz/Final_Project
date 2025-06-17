import { Injectable } from '@angular/core';
import { Videogioco } from '../model/Videogioco';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { PageResponse } from "../model/PageResponse";


@Injectable({
  providedIn: 'root'
})
export class VideogiocoService {
  private _apiUrl = 'http://localhost:8080/api/videogioco';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Videogioco[]> {
    return this.http.get<Videogioco[]>(this._apiUrl);
  }

  getById(id: number): Observable<Videogioco> {
    return this.http.get<Videogioco>(`${this._apiUrl}/${id}`);
  }

  getByName(titolo: string | undefined): Observable<Videogioco[]> {
    return this.http.get<PageResponse<Videogioco>>(`${this._apiUrl}?titolo=${titolo}`)
      .pipe(map(page => page.content));
  }

  getByFilters(queryString: string): Observable<Videogioco[]> {
    return this.http.get<PageResponse<Videogioco>>(`${this._apiUrl}${queryString}`)
      .pipe(map(page => page.content));
  }

  getConsigliati(id: number): Observable<Videogioco[]> {
    return this.http.get<Videogioco[]>(`${this._apiUrl}/utente/${id}`)
  }
}
