import { Casa } from "./Casa";

export interface Videogioco {
    id:number;
    titolo:string;
    casaDiProduzioneId:number;
    casaDiPubblicazioneId:number;
    oreStoriaPrincipale:number|null;
    descrizione:string;
    voto:number;
    imgUrl:string;
}