public class Aluno extends Pessoa{

    protected int id;
    protected boolean estado;
    protected Plano tipo;
    //fazer historico de pagamento

    protected boolean esta_ativo() {
        return estado;
    }

    int getId(){
        return id;
    }

    Plano getTipo(){
        return tipo;
    }

    void setId(int id){
        this.id = id;
    }

    void setEstado(boolean estado){
        this.estado = estado;
    }

    void setTipo(Plano tipo){
        this.tipo = tipo;
    }

    //protected mostrar historico de pagamento

}
