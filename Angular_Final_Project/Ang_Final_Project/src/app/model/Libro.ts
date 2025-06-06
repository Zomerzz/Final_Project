import { Casa } from "./Casa";

export interface Libro {
    id:number;
    titolo:string;
    numeroDiPagine:number;
    casaDiProduzione:Casa;
    descrizione:string;
    annoDiPubblicazione:string;
    voto:number;
    imgUrl:string;
}