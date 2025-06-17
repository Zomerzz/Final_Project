import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Utente } from "../model/Utente";

@Injectable({
    providedIn: 'root'
})
export class UtenteService {
    private _url: string = 'http://localhost:8080/api/utente';
    private _http: HttpClient = inject(HttpClient);
    
    getUtentiByUsername(username: string):Observable<Utente[]>{
        return this._http.get<Utente[]>(`${this._url}/username/${username}`);
    }
}