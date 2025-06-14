import { HttpClient } from '@angular/common/http';
import {RegistrationRequest} from '../model/RegistrationRequest'
import { inject, Injectable } from '@angular/core';
import { LoginRequest } from '../model/LoginRequest';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../model/JwtPayload';
import { Observable } from 'rxjs';
import { JwtToken } from '../model/JwtToken';
import { Router } from '@angular/router';
import { Utente } from '../model/Utente';

@Injectable({providedIn: 'root'})
export class AdminService{

    private _url: string = 'http://localhost:8080/api/admin'
    private _http = inject(HttpClient);
    private _router = inject(Router)

    getAll():Observable<Utente[]>{
        return this._http.get<Utente[]>(this._url)
    }

    promote(id:number){
        return this._http.put(`${this._url}/${id}/role`,null);
    }
    
    delete(id:number){
        return (this._http.delete(`${this._url}/${id}`))
    }
}
