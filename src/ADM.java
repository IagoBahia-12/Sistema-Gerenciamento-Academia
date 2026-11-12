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

    public void realizar_matricula(String cpf, Plano plano){

        Aluno aluno = buscar_aluno(cpf);

        if(aluno == null){
            System.out.println("Nenhum aluno foi encontrado!");
        } else if (aluno.esta_ativo()) {
            System.out.println("Aluno já está matriculado em um plano");
        }
        else{
            aluno.setTipo(plano);
            aluno.ativar_aluno();
            System.out.println("Matrícula realizada com sucesso!");
        }
    }

    public void renovar_matricula(String cpf, LocalDate data_inicio, LocalDate data_final) {

        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Nenhum aluno foi encontrado!");
        }
        else if (!aluno.esta_ativo()) {
            System.out.println("O aluno não possui matrícula ativa!");
        }
        else if (aluno.getTipo() == null) {
            System.out.println("O aluno não possui um plano associado!");
        }
        else {
            aluno.getTipo().setData_inicio(data_inicio);
            aluno.getTipo().setData_final(data_final);

            System.out.println("Matrícula renovada com sucesso!");
        }
    }

    public void cancelar_matricula(String cpf) {

        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Nenhum aluno foi encontrado!");
        }
        else if (!aluno.esta_ativo()) {
            System.out.println("O aluno não possui matrícula ativa!");
        }
        else {
            aluno.cancelar_matricula();

            System.out.println("Matrícula cancelada com sucesso!");
        }
    }

}
