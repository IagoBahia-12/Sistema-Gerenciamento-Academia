public class Aluno extends Pessoa{

    protected int id;
    protected boolean estado;
    protected Plano tipo;
    //fazer historico de pagamento

    protected boolean esta_ativo() {
        return estado;
    }

    //protected mostrar historico de pagamento

}
