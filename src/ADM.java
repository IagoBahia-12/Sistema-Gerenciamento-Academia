import java.util.ArrayList;
import java.time.LocalDate;

public class ADM extends Pessoa{

    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Plano> planos = new ArrayList<>();

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

    public void consultar_aluno(String cpf) {
        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Nenhum aluno foi encontrado!");
            return;
        }

        System.out.println("DADOS DO ALUNO:");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("CPF: " + aluno.getCpf());
        System.out.println("Telefone: " + aluno.getTel());
        System.out.println("Email: " + aluno.getEmail());
        System.out.println("ID: " + aluno.getId());
        System.out.println("Estado: " + (aluno.esta_ativo() ? "Ativo" : "Inativo"));

        Plano plano = aluno.getTipo();

        if (plano == null) {
            System.out.println("Plano: Sem plano!");
        }

        else {
            System.out.println("Plano: " + plano.getNome());
        }
    }

    public void atualizar_aluno() {

    }

    public void cadastrar_plano(String nome, Pagamento pag, String beneficios) {
        for(Plano p : planos){
            if(p.getNome().equalsIgnoreCase(nome)){
                System.out.println("Plano existente!");
                return;
            }
        }

        Plano plano = new Plano();
        plano.setNome(nome);
        plano.setPag(pag);
        plano.setBeneficios(beneficios);
        
        planos.add(plano);
    }

    public void consultar_plano() {

    }

    public void atualizar_plano() {

    }

    public void cadastrar_atividade() {

    }

    public void consultar_atividade() {

    }

    public void consultar_pagamento() {

    }

}
