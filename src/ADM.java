import java.time.LocalDate;

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

    public void renovar_matricula(Aluno aluno, LocalDate data_inicio, LocalDate data_final){

        if(!aluno.esta_ativo()){
            System.out.println("O aluno não está matriculado!");
        }
        else {
            aluno.getTipo().setData_inicio(data_inicio);
            aluno.getTipo().setData_final(data_final);
        }
        }

    public void cancelar_matricula(Aluno aluno){

        if(!aluno.esta_ativo()){
            System.out.println("O aluno não está matriculado!");
        }
        else {
            aluno.cancelar_matricula();
        }
        }

}
