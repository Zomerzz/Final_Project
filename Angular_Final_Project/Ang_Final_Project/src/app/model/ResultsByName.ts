import { Film } from "./Film";
import { Libro } from "./Libro";
import { Videogioco } from "./Videogioco";

export interface ResultsByName{
    listaLibro: Libro[]|undefined;
    listaFilm: Film[]|undefined;
    listaVideogioco: Videogioco[]|undefined;
    
}