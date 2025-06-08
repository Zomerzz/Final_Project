import { Casa } from "./Casa";

export interface Libro {
    id:number;
    titolo:string;
    numeroPagine:number;
    descrizione:string;
    dataDiPubblicazione:number;
    casaEditriceId:number;
    voto:number;
    imgUrl:string;
}