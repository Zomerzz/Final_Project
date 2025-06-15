package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.LibroLetto;

public class LibroLettoDto {
    private int libroLettoId;
    private LibroDto libro;
    private int utenteId;
    private RecensioneDto recensione;

    public LibroLettoDto() {
    }

    public LibroLettoDto(int libroLettoId, LibroDto libro, int utenteId) {
        this.libroLettoId = libroLettoId;
        this.libro = libro;
        this.utenteId = utenteId;
    }

    public LibroLettoDto(int libroLettoId, LibroDto libro, int utenteId, RecensioneDto recensione) {
        this.libroLettoId = libroLettoId;
        this.libro = libro;
        this.utenteId = utenteId;
        this.recensione = recensione;
    }

    public static LibroLettoDto toDto(LibroLetto libroLetto){
        LibroLettoDto dto = new LibroLettoDto(libroLetto.getLibroLettoId(),
                LibroDto.toDto(libroLetto.getLibro()),
                libroLetto.getUtente().getUtenteId());
        if(libroLetto.getRecensione() != null){
            dto.setRecensione(RecensioneDto.toDto(libroLetto.getRecensione()));
        }
        return dto;
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

    public LibroDto getLibro() {
        return libro;
    }

    public void setLibro(LibroDto libro) {
        this.libro = libro;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public RecensioneDto getRecensione() {
        return recensione;
    }

    public void setRecensione(RecensioneDto recensione) {
        this.recensione = recensione;
    }
}