import java.util.ArrayList;
import java.time.LocalDate;

public class ADM extends Pessoa{

    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Plano> planos = new ArrayList<>();
    private ArrayList<Atividade> atividades = new ArrayList<>();

    public void cadastrar_aluno(String nome, String cpf, String tel, String email, String senha){
        for(Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Aluno já cadastrado!");
                return;
            }
        }

        Aluno aluno = new Aluno();

        aluno.setNome(nome);
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

    public void atualizar_aluno(String cpf, String nome, String tel, String email) {
        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Nenhum aluno encontrado!");
            return;
        }

        aluno.setNome(nome);
        aluno.setTel(tel);
        aluno.setEmail(email);

        System.out.println("Dados do aluno atualizados com sucesso!");

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

        System.out.println("Plano cadastrado com sucesso!");
    }

    public Plano buscar_plano(String nome){
        for(Plano p : planos){
            if(p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public void consultar_plano(String nome) {
        Plano plano = buscar_plano(nome);

        if (plano == null) {
            System.out.println("Plano não encontrado!");
            return;
        }

        System.out.println("DADOS DO PLANO");
        System.out.println("Nome: " + plano.getNome());
        System.out.println("Benefícios: " + plano.getBeneficios());
        System.out.println("Data de início: " + plano.getData_inicio());
        System.out.println("Data de encerramento: " + plano.getData_final());
    }

    public void atualizar_plano(String nome, String beneficios, LocalDate data_inicio, LocalDate data_final) {
        Plano plano = buscar_plano(nome);

        if (plano == null) {
            System.out.println("Nenhum plano encontrado!");
            return;
        }

        plano.setNome(nome);
        plano.setBeneficios(beneficios);
        plano.setData_inicio(data_inicio);
        plano.setData_final(data_final);

        System.out.println("Dados do plano atualizados com sucesso!");
    }

    public void cadastrar_atividade(String nome, String descricao) {
        for(Atividade a : atividades){
            if(a.getNome().equalsIgnoreCase(nome)){
                System.out.println("Aividade já existe!");
                return;
            }
        }

        Atividade atividade = new Atividade();
        atividade.setNome(nome);
        atividade.setDescricao(atividade.descricao);

        atividades.add(atividade);

        System.out.println("Atividade cadastrada com sucesso!");
    }

    public Atividade buscar_atividade(String nome) {
        for (Atividade a : atividades) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }

    public void consultar_atividade(String nome) {
        Atividade atividade = buscar_atividade(nome);

        if (atividade == null) {
            System.out.println("Atividade não encontrada!");
            return;
        }

        System.out.println("DADOS DA ATIVIDADE");
        System.out.println("Nome: " + atividade.getNome());
        System.out.println("Descrição: " + atividade.getDescricao());
    }

    public void consultar_pagamento(String cpf) {
        Aluno aluno = buscar_aluno(cpf);

        if(aluno == null){
            System.out.println("Aluno não encontrado!");
            return;
        }

        ArrayList<Pagamento> historico = aluno.mostrar_historico_pagamentos();

        if(historico.isEmpty()){
            System.out.println("Nenhum pagamento registrado!");
            return;
        }

        System.out.println("HISTORICO PAGAMENTO");
        System.out.println("Aluno: " + aluno.getNome());

        for(Pagamento p : historico){
            System.out.println("Valor: R$ " + p.getValor());
            System.out.println("Forma de pagamento: " + p.getForma_pagamento());
            System.out.println("Data: " + p.getData_pag());
        }
    }

}
