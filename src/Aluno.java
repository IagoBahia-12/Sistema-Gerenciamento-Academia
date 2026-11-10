public class Aluno extends Pessoa{

    protected int id;
    protected boolean estado;
    protected Plano tipo;
    //fazer historico de pagamento

    public boolean esta_ativo() {
        return estado;
    }

    public int getId(){
        return id;
    }

    public Plano getTipo(){
        return tipo;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public void setTipo(Plano tipo){
        this.tipo = tipo;
    }

    public void ativar_aluno(){
        this.estado = true;
    }

    public void desativar_aluno(){
        this.estado = false;
    }

    public void cancelar_matricula(){
        this.estado = false;
        this.tipo = null;
    }

    //public mostrar historico de pagamento

}
