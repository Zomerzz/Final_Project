import { Casa } from "./Casa";

export interface Videogioco {
    id:number;
    titolo:string;
    casaDiProduzione:Casa;
    casaDiPubblicazione:Casa;
    dataDiPubblicazione:number;
    oreStoriaPrincipale:number|null;
    descrizione:string;
    voto:number;
    imgUrl:string;
}