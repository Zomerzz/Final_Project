package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.LibroLetto;

public class LibroLettoDto {
    private int libroLettoId;
    private int libroId;
    private int utenteId;
    private int recensioneId;

    public LibroLettoDto() {
    }

    public LibroLettoDto(int libroLettoId, int libroId, int utenteId, int recensioneId) {
        this.libroLettoId = libroLettoId;
        this.libroId = libroId;
        this.utenteId = utenteId;
        this.recensioneId = recensioneId;
    }

    public static LibroLettoDto toDto(LibroLetto libroLetto){
        return new LibroLettoDto(libroLetto.getLibroLettoId(), libroLetto.getLibro().getLibroId(), libroLetto.getUtente().getUtenteId(), libroLetto.getRecensione().getRecensioneId());
    }

    public LibroLetto toLibroLetto(){
        return new LibroLetto(libroLettoId, null, null, null);
    }

    public int getLibroLettoId() {
        return libroLettoId;
    }

    public void setLibroLettoId(int libroLettoId) {
        this.libroLettoId = libroLettoId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public int getRecensioneId() {
        return recensioneId;
    }

    public void setRecensioneId(int recensioneId) {
        this.recensioneId = recensioneId;
    }
}