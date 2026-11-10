import java.time.LocalDate;

public class Plano {

    protected String nome;
    protected Pagamento pag;
    protected String beneficios;
    protected LocalDate data_inicio;
    protected LocalDate data_final;

    String getNome(){
        return nome;
    }

    Pagamento getPag(){
        return pag;
    }

    String getBeneficios(){
        return beneficios;
    }

    LocalDate getData_inicio(){
        return data_inicio;
    }

    LocalDate getData_final(){
        return data_final;
    }

    void setNome(String nome){
        this.nome = nome;
    }

    void setPag(Pagamento pag){
        this.pag = pag;
    }

    void setBeneficios(String beneficios){
        this.beneficios = beneficios;
    }

    void setData_inicio(LocalDate data_inicio){
        this.data_inicio = data_inicio;
    }

    void setData_final(LocalDate data_final){
        this.data_final = data_final;
    }

    protected void realizar_matricula(){}
    protected void cancelar_matricula(){}
    protected void renovar_matricula(){}

    //minha misericordia prevalece sobre a minha ira
}
