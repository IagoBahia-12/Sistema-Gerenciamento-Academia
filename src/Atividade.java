import java.util.ArrayList;

public class Atividade {

    protected String nome;
    protected String descricao;
    protected ArrayList<Aluno> alunos = new ArrayList<>();

    String getNome(){
        return nome;
    }

    String getDescricao(){
        return descricao;
    }

    void setNome(String nome){
        this.nome = nome;
    }

    void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int qtd_alunos(){
        return alunos.size();
    }

    public void adc_aluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void remove_aluno(Aluno aluno){}

    //public String lista_alunos(){
       // return for
    }



