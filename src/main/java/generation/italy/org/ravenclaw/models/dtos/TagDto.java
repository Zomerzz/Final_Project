package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Tag;

public class TagDto {
    private int tagId;
    private String nome;
    private boolean isGenre;

    public TagDto(String nome, boolean isGenre){
        this.nome = nome;
        this.isGenre = isGenre;
    }

    public static TagDto toDto(Tag tag){
        return new TagDto (tag.getNome(), tag.isGenere());
    }

    public Tag toTag (TagDto tagDto){
        return new Tag (nome, isGenre);
    }
}
