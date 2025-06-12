import { HttpClient } from '@angular/common/http';
import {RegistrationRequest} from '../model/RegistrationRequest'
import { inject, Injectable } from '@angular/core';
import { LoginRequest } from '../model/LoginRequest';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../model/JwtPayload';
import { Observable } from 'rxjs';
import { JwtToken } from '../model/JwtToken';
import { Router } from '@angular/router';

@Injectable({providedIn: 'root'})
export class AdminService{

    private _url: string = 'http://localhost:8080/api/admin'
    private _http = inject(HttpClient);
    private _router = inject(Router)

    getAll(){
        this._http.get('')
    }

    promote(id:number):boolean{
        this._http.put(`${this._url}/${id}/role`,null).subscribe({
            next: ()=> {return true;}
        });
        return false;
    }
    
    delete(id:number):boolean{
        return (this._http.delete(`${this._url}/${id}`))!= null;
    }
}
