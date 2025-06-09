package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Tag;

public class TagDto {
    private int tagId;
    private String nome;
    private boolean isGenre;

    public TagDto(int tagId, String nome, boolean isGenre){
        this.tagId = tagId;
        this.nome = nome;
        this.isGenre = isGenre;
    }

    public static TagDto toDto(Tag tag){
        return new TagDto (tag.getTagId(), tag.getNome(), tag.isGenere());
    }

    public Tag toTag (){
        return new Tag (nome, isGenre);
    }

    //GETTER & SETTER

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isGenre() {
        return isGenre;
    }

    public void setGenre(boolean genre) {
        isGenre = genre;
    }
}
