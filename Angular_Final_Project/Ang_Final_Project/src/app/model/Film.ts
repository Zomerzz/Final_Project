import { Casa } from "./Casa";

export interface Film {
    id:number;
    titolo:string;
    durata:number;
    dataDiPubblicazione:string;
    descrizione:string;
    casaDiProduzione:Casa;
    annoDiPubblicazione:string;
    voto:number;
    imgUrl:string;
}