import java.util.ArrayList;

public class Atividade {

    protected String nome;
    protected String descricao;
    protected ArrayList<Aluno> alunos = new ArrayList<>();

    protected int qtd_alunos(){
        return alunos.size();
    }

    protected void adc_aluno(Aluno aluno){
        alunos.add(aluno);
    }

    protected void remove_aluno(Aluno aluno){}

    //protected String lista_alunos(){
       // return for
    //}



}
