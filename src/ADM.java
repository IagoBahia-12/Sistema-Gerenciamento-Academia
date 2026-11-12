import java.util.ArrayList;
import java.time.LocalDate;

public class ADM extends Pessoa{

    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void cadastrar_aluno(String nome, String cpf, String tel, String email, String senha){
        Aluno aluno = new Aluno();

        aluno.setNome(nome);
        for(Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Aluno já cadastrado!");
                return;
            }
        }
        aluno.setCpf(cpf);
        aluno.setTel(tel);
        aluno.setEmail(email);
        aluno.setSenha(senha);
        aluno.cancelar_matricula();

        alunos.add(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    public Aluno buscar_aluno(String cpf){
        for(Aluno a : alunos){
            if(a.getCpf().equals(cpf)) {
                return a;
            }
        }
        return null;
    }

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
