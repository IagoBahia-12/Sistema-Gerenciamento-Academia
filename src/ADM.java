public class ADM extends Pessoa{

    public void realizar_matricula(Aluno aluno, Plano plano){

        if(aluno.esta_ativo()){
            System.out.println("O aluno já está matriculado!");
        }
        else {
            aluno.setTipo(plano);
            aluno.ativar_aluno();
        }
    }

}
