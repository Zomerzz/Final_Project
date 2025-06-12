import { Film } from "./Film";
import { Recensione } from "./Recensione";

export interface FilmVisto {
    filmVistoId:number;
    film: Film;
    utenteId:number;
    recensione:Recensione;
}