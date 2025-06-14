import { authority } from "./Authority";

export interface Utente {
    id:number;
    nome:string;
    email:string;
    password:string;
    authorities:authority[];
}