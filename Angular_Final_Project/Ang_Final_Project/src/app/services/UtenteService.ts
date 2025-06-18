import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UtenteNoPass } from "../model/UtenteNoPass";

@Injectable({
    providedIn: 'root'
})
export class UtenteService {
    private _url: string = 'http://localhost:8080/api/utente';
    private _http: HttpClient = inject(HttpClient);
    
    getUtentiByUsername(username: string):Observable<UtenteNoPass[]>{
        return this._http.get<UtenteNoPass[]>(`${this._url}/username/${username}`);
    }

    getUtenteByUtenteId(utenteId: number):Observable<UtenteNoPass>{
        return this._http.get<UtenteNoPass>(`${this._url}/${utenteId}`);
    }
}