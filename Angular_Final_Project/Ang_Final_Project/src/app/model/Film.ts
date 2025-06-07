import { Casa } from "./Casa";

export interface Film {
    id:number;
    titolo:string;
    durata:number;
    dataDiPubblicazione:string;
    descrizione:string;
    casaDiProduzioneId:number;
    annoDiPubblicazione:string;
    voto:number;
    imgUrl:string;
}