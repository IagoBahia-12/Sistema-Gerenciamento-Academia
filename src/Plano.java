import java.time.LocalDate;

public class Plano {

    protected String nome;
    protected Pagamento pag;
    protected String beneficios;
    protected LocalDate data_inicio;
    protected LocalDate data_final;
    protected boolean ativo = true;

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

    public void realizar_matricula(LocalDate inicio, int meses){
        this.data_inicio = inicio;
        this.data_final = inicio.plusMonths(meses);
        this.ativo = true;
    }

    public void cancelar_matricula(){
        this.ativo = false;
    }

    public boolean renovar_matricula(int meses){
        if(data_final == null){
            System.out.println("Plano nunca matriculado!");
            return false;
        }
        if(!ativo){
            System.out.println("Não é possivel renovar um plano cancelado!");
            return false;
        }

        LocalDate base;

        if(data_final.isBefore(LocalDate.now())){
            base = LocalDate.now();
        }else{
            base = data_final;
        }

        this.data_inicio = base;
        this.data_final = base.plusMonths(meses);

        return true;
    }

    public Plano copiar() {
        Plano copia = new Plano();
        copia.setNome(this.nome);
        copia.setBeneficios(this.beneficios);
        return copia;
    }

    //minha misericordia prevalece sobre a minha ira
}
