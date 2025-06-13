import { Injectable } from '@angular/core';
import { Videogioco } from '../model/Videogioco';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VideogiocoService {
  private _apiUrl = 'http://localhost:8080/api/videogioco';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Videogioco[]> {
    return this.http.get<Videogioco[]>(this._apiUrl);
  }

  getById(id: number): Observable<Videogioco> {
    return this.http.get<Videogioco>(`${this._apiUrl}/${id}`);
  }
  getByName(titolo: string|undefined): Observable<Videogioco[]>{
    return this.http.get<Videogioco[]>(`${this._apiUrl}?titolo=${titolo}`);
  }
  getByFilters(queryString:string): Observable<Videogioco[]>{
          return this.http.get<Videogioco[]>(`${this._apiUrl}${queryString}`);
      }
}
