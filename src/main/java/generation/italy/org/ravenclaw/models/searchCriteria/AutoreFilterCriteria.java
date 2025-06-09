package generation.italy.org.ravenclaw.models.searchCriteria;


import java.time.LocalDate;

public class AutoreFilterCriteria {
    private String nome;
    private LocalDate minData;
    private LocalDate maxData;


    public AutoreFilterCriteria(String nome, LocalDate minData, LocalDate maxData) {
        this.nome = nome;
        this.minData = minData;
        this.maxData = maxData;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getMinData() {
        return minData;
    }

    public void setMinData(LocalDate minData) {
        this.minData = minData;
    }

    public LocalDate getMaxData() {
        return maxData;
    }

    public void setMaxData(LocalDate maxData) {
        this.maxData = maxData;
    }
}
