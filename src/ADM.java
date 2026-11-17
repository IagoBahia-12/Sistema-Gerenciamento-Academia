import java.util.ArrayList;
import java.time.LocalDate;

public class ADM extends Pessoa{

    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Plano> planos = new ArrayList<>();
    private ArrayList<Atividade> atividades = new ArrayList<>();

    public void cadastrar_aluno(String nome, String cpf, String tel, String email, String senha){
        if (nome == null || nome.isBlank()) {
            System.out.println("Nome inválido!");
            return;
        }

        if (cpf == null || cpf.isBlank()) {
            System.out.println("CPF inválido!");
            return;
        }

        if (senha == null || senha.isBlank()) {
            System.out.println("Senha inválida!");
            return;
        }

        if (nome.contains("#") || cpf.contains("#") || (tel != null && tel.contains("#"))
                || (email != null && email.contains("#"))) {
            System.out.println("Os dados não podem conter o caractere #");
            return;
        }

        for(Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Aluno já cadastrado!");
                return;
            }
        }

        Aluno aluno = new Aluno();
        aluno.setId(Aluno.gerarId());

        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTel(tel);
        aluno.setEmail(email);
        aluno.setSenha(SenhaUtil.gerarHash(senha));
        aluno.cancelar_matricula();

        alunos.add(aluno);
        salvar_aluno(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void salvar_aluno(Aluno aluno) {
        Main.salvarAluno(aluno);
    }

    public void carregar_aluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void listar_alunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
            return;
        }

        for (Aluno a : alunos) {
            System.out.println(a.getNome());
        }
    }

    public Aluno buscar_aluno(String cpf){
        for(Aluno a : alunos){
            if(a.getCpf().equals(cpf)) {
                return a;
            }
        }
        return null;
    }

    public void realizar_matricula(String cpf, String nomePlano, int meses) {

        Aluno aluno = buscar_aluno(cpf);

        if(aluno == null){
            System.out.println("Nenhum aluno foi encontrado!");
            return;
        }

        if (aluno.esta_ativo()) {
            System.out.println("Aluno já está matriculado em um plano!");
            return;
        }

        Plano modelo = buscar_plano(nomePlano);

        if (modelo == null) {
            System.out.println("Nenhum plano foi encontrado!");
            return;
        }

        Plano plano = modelo.copiar();
        plano.realizar_matricula(LocalDate.now(), meses);

        aluno.setTipo(plano);
        aluno.ativar_aluno();

        Main.atualizarArquivo(alunos);

        System.out.println("Matrícula realizada com sucesso!");
    }

    public void renovar_matricula(String cpf, int meses) {

        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Nenhum aluno foi encontrado!");
            return;
        }

        if (!aluno.esta_ativo()) {
            System.out.println("O aluno não possui matrícula ativa!");
            return;
        }

        if (aluno.getTipo() == null) {
            System.out.println("O aluno não possui um plano associado!");
            return;
        }

        boolean renovou = aluno.getTipo().renovar_matricula(meses);

        if (renovou) {
            Main.atualizarArquivo(alunos);
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
            for (Atividade a : atividades) {
                a.remove_aluno(aluno);
            }

            Main.salvarAtividades(atividades);
            aluno.cancelar_matricula();
            Main.atualizarArquivo(alunos);

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

        Main.atualizarArquivo(alunos);

        System.out.println("Dados do aluno atualizados com sucesso!");

    }

    public void excluir_aluno(String cpf) {
        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Nenhum aluno encontrado!");
            return;
        }

        alunos.remove(aluno);

        Main.atualizarArquivo(alunos);
        Main.salvarAtividades(atividades);

        System.out.println("Aluno excluído com sucesso!");
    }

    public Plano buscar_plano(String nome){
        for(Plano p : planos){
            if(p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public void cadastrar_plano(String nome, Pagamento pag, String beneficios) {
        if (nome == null) {
            System.out.println("Plano inválido!");
            return;
        }

        if (nome.contains("#") || (beneficios != null && beneficios.contains("#"))) {
            System.out.println("Os dados não podem conter o caractere #");
            return;
        }

        if (buscar_plano(nome) != null) {
            System.out.println("Plano existente!");
            return;
        }

        Plano plano = new Plano();
        plano.setNome(nome);
        plano.setPag(pag);
        plano.setBeneficios(beneficios);
        
        planos.add(plano);

        Main.salvarPlano(planos);

        System.out.println("Plano cadastrado com sucesso!");
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

        Pagamento pag = plano.getPag();

        if (pag == null) {
            System.out.println("Pagamento: Nenhum pagamento encontrado!");
        }

        else {
            System.out.println("Valor: R$ " + pag.getValor());
            System.out.println("Forma de pagamento: " + pag.getForma_pagamento());
            System.out.println("Data: " + pag.getData_pag());
        }
    }

    public Aluno fazer_login(String cpf, String senha) {
        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return null;
        }

        if (!SenhaUtil.verificarSenha(senha, aluno.getSenha())) {
            System.out.println("Senha incorreta!");
            return null;
        }

        System.out.println("Login realizado com sucesso!");
        return aluno;
    }

    public void atualizar_plano(String nome, String beneficios) {
        Plano plano = buscar_plano(nome);

        if (plano == null) {
            System.out.println("Nenhum plano encontrado!");
            return;
        }

        plano.setNome(nome);
        plano.setBeneficios(beneficios);

        Main.salvarPlano(planos);

        System.out.println("Dados do plano atualizados com sucesso!");
    }

    public void cadastrar_atividade(String nome, String descricao) {
        if (nome == null) {
            System.out.println("Atividade inválida!");
            return;
        }

        if (nome.contains("#") || (descricao != null && descricao.contains("#"))) {
            System.out.println("Os dados não podem conter o caractere #");
            return;
        }

        if (buscar_atividade(nome) != null) {
            System.out.println("Atividade já existe!");
            return;
        }

        Atividade atividade = new Atividade();
        atividade.setNome(nome);
        atividade.setDescricao(descricao);

        atividades.add(atividade);
        Main.salvarAtividades(atividades);

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
        System.out.println("Alunos inscritos: " + atividade.qtd_alunos());

        for (Aluno a : atividade.lista_alunos()) {
            System.out.println(" - " + a.getNome());
        }
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

    public void registrar_pagamento_aluno(
            String cpf, double valor, String forma) {

        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        Pagamento pagamento = new Pagamento();
        pagamento.registrar_pag(valor, forma);

        aluno.registrar_pag(pagamento);
        Main.salvarPagamento(cpf, pagamento);

        System.out.println("Pagamento registrado com sucesso!");
    }

    public void registrar_pagamento_plano(
            String nomePlano, double valor, String forma) {

        Plano plano = buscar_plano(nomePlano);

        if (plano == null) {
            System.out.println("Plano não encontrado!");
            return;
        }

        Pagamento pagamento = new Pagamento();
        pagamento.registrar_pag(valor, forma);

        plano.setPag(pagamento);

        Main.salvarPlano(planos);

        System.out.println("Pagamento do plano registrado com sucesso!");
    }

    public void carregar_plano(Plano plano) {
        planos.add(plano);
    }

    public void carregar_atividade(Atividade atividade) {
        atividades.add(atividade);
    }

    public void inscrever_aluno(String cpf, String nomeAtividade) {
        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        if (!aluno.esta_ativo()) {
            System.out.println("Aluno não matriculado!");
            return;
        }

        Atividade atividade = buscar_atividade(nomeAtividade);

        if (atividade == null) {
            System.out.println("Atividade não encontrada!");
            return;
        }

        if (atividade.lista_alunos().contains(aluno)) {
            System.out.println("Aluno já inscrito!");
            return;
        }

        atividade.adc_aluno(aluno);
        Main.salvarAtividades(atividades);

        System.out.println("Aluno inscrito com sucesso na atividade!");

    }

    public void remover_aluno_atividade(String cpf, String nomeAtividade) {

        Aluno aluno = buscar_aluno(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        Atividade atividade = buscar_atividade(nomeAtividade);

        if (atividade == null) {
            System.out.println("Atividade não encontrada!");
            return;
        }

        if (!atividade.lista_alunos().contains(aluno)) {
            System.out.println("Aluno não está inscrito na atividade!");
            return;
        }

        atividade.remove_aluno(aluno);
        Main.salvarAtividades(atividades);

        System.out.println("Aluno removido da atividade com sucesso!");
    }


}
