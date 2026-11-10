import java.time.LocalDate;

public class Plano {

    protected String nome;
    protected Pagamento pag;
    protected String beneficios;
    protected LocalDate data_inicio;
    protected LocalDate data_final;

    public String getNome(){
        return nome;
    }

    public Pagamento getPag(){
        return pag;
    }

    public String getBeneficios(){
        return beneficios;
    }

    public LocalDate getData_inicio(){
        return data_inicio;
    }

    public LocalDate getData_final(){
        return data_final;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPag(Pagamento pag){
        this.pag = pag;
    }

    public void setBeneficios(String beneficios){
        this.beneficios = beneficios;
    }

    public void setData_inicio(LocalDate data_inicio){
        this.data_inicio = data_inicio;
    }

    public void setData_final(LocalDate data_final){
        this.data_final = data_final;
    }

    public void realizar_matricula(){}
    public void cancelar_matricula(){}
    public void renovar_matricula(){}

    //minha misericordia prevalece sobre a minha ira
}
