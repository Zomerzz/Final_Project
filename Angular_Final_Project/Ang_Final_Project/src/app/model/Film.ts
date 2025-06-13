import { Casa } from "./Casa";

export interface Film {
    id:number;
    titolo:string;
    durata:number;
    dataDiPubblicazione:string;
    descrizione:string;
    casaDiProduzione:Casa;
    casaDiPubblicazione: Casa;
    voto:number;
    imgUrl:string;
}