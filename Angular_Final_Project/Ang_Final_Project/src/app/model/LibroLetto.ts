import { Libro } from "./Libro";
import { Recensione } from "./Recensione";

export interface LibroLetto {
    libroLettoId:number;
    libro: Libro;
    utenteId:number;
    recensione:Recensione;
}