import { Recensione } from "./Recensione";
import { Videogioco } from "./Videogioco";

export interface VideogiocoGiocato {
    videogiocoGiocatoId:number;
    videogioco: Videogioco;
    utenteId:number;
    recensione:Recensione;
}