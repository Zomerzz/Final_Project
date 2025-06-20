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
export class AuthService{

    private _url: string = 'http://localhost:8080/api/auth'
    private _http = inject(HttpClient);
    private _router = inject(Router)

    register(regreq:RegistrationRequest):Observable<JwtToken>{
        return this._http.post<JwtToken>(`${this._url}/register`,regreq);
    }

    login(logreq:LoginRequest){
        this._http.post<JwtToken>(`${this._url}/login`,logreq).subscribe({
            next: (authToken) =>{
                localStorage.setItem("jwt",authToken.token );
                console.log("Authenticated as "+ logreq.email);
                this._router.navigate(['/home']);
            },error: e => alert('Errore nel accesso')
        });
    }

    logout(){
        localStorage.removeItem('jwt');
        console.log("Logged out");
    }

    

    isLogged():boolean{
        return localStorage.getItem("jwt")!=null;
    }

    getToken():string|null{
        // ritorna il token dallo storage
        return localStorage.getItem("jwt");
    }

    getUserId():string|null{
        const token = this.getToken(); // prende il token dal local storage
        if(!token){
            return null; // check se non c'è
        }
        const decoded = jwtDecode<JwtPayload>(token); // decoda il token
        return decoded.userId || null; // ritorna il valore del id dello user loggato
    }
}
