import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ADM adm = new ADM();

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("planos.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split("#");

                Plano plano = new Plano();
                plano.setNome(campos[0]);

                if (campos.length >= 5 && !campos[2].equals("-")) {
                    Pagamento pag = new Pagamento();

                    pag.setValor(Double.parseDouble(campos[2]));
                    pag.setForma_pagamento(campos[3]);

                    if (!campos[4].equals("-")) {
                        pag.setData_pag(LocalDate.parse(campos[4]));
                    }

                    plano.setPag(pag);
                }

                if (campos.length >= 2 && !campos[1].equals("-")) {
                    plano.setBeneficios(campos[1]);
                }

                adm.carregar_plano(plano);
            }
        }

        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("alunos.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split("#");

                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String cpf = campos[2];
                String telefone = campos[3];
                String email = campos[4];
                String senha = campos[5];
                boolean estado = Boolean.parseBoolean(campos[6]);

                Aluno aluno = new Aluno();

                aluno.setId(id);
                aluno.setNome(nome);
                aluno.setCpf(cpf);
                aluno.setTel(telefone);
                aluno.setEmail(email);
                aluno.setSenha(senha);

                if (estado) {
                    aluno.ativar_aluno();
                } else {
                    aluno.desativar_aluno();
                }

                DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;

                if (campos.length >= 10 && !campos[7].equals("-")) {
                    Plano plano = new Plano();

                    plano.setNome(campos[7]);

                    Plano modelo = adm.buscar_plano(campos[7]);
                    if (modelo != null) {
                        plano.setBeneficios(modelo.getBeneficios());
                    }

                    if (!campos[8].equals("-")) {
                        plano.setData_inicio(LocalDate.parse(campos[8], formato));
                    }

                    if (!campos[9].equals("-")) {
                        plano.setData_final(LocalDate.parse(campos[9], formato));
                    }

                    aluno.setTipo(plano);
                }

                adm.carregar_aluno(aluno);
            }

        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("adm.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split("#");

                ADM administrador = new ADM();

                administrador.setNome(campos[0]);
                administrador.setCpf(campos[1]);
                administrador.setTel(campos[2]);
                administrador.setEmail(campos[3]);
                administrador.setSenha(campos[4]);

                adm.carregar_administrador(administrador);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de administradores: " + e.getMessage());
        }

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("pagamentos.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] campos = linha.split("#");

                String cpf = campos[0];
                double valor = Double.parseDouble(campos[1]);
                String forma = campos[2];
                LocalDate data = LocalDate.parse(campos[3]);

                Aluno aluno = adm.buscar_aluno(cpf);

                if (aluno != null) {
                    Pagamento pagamento = new Pagamento();
                    pagamento.setValor(valor);
                    pagamento.setForma_pagamento(forma);
                    pagamento.setData_pag(data);

                    aluno.registrar_pag(pagamento);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar pagamentos: " + e.getMessage());
        }

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("atividades.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] campos = linha.split("#");

                Atividade atividade = new Atividade();
                atividade.setNome(campos[0]);
                atividade.setDescricao(campos[1]);

                for (String cpf : campos[2].split(",")) {
                    Aluno aluno = adm.buscar_aluno(cpf);
                    if (aluno != null) {
                        atividade.adc_aluno(aluno);
                    }
                }

                adm.carregar_atividade(atividade);
            }
        }

        catch (IOException e) {
            System.out.println("Erro ao ler atividades: " + e.getMessage());
        }

        menu(adm);
    }

    public static void salvarAluno(Aluno aluno) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("alunos.txt", true))) {

            Plano plano = aluno.getTipo();

            String nomePlano = "-";
            String inicio = "-";
            String fim = "-";

            if (plano != null) {
                if (plano.getNome() != null) {
                    nomePlano = plano.getNome();
                }
                if (plano.getData_inicio() != null) {
                    inicio = plano.getData_inicio().toString();
                }
                if (plano.getData_final() != null) {
                    fim = plano.getData_final().toString();
                }
            }

            escritor.write(
                    aluno.getId() + "#" +
                            aluno.getNome() + "#" +
                            aluno.getCpf() + "#" +
                            aluno.getTel() + "#" +
                            aluno.getEmail() + "#" +
                            aluno.getSenha() + "#" +
                            aluno.esta_ativo() + "#" +
                            nomePlano + "#" +
                            inicio + "#" +
                            fim
            );

            escritor.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public static void salvarPlano(ArrayList<Plano> planos) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("planos.txt"))) {

            for (Plano plano : planos) {

                Pagamento pag = plano.getPag();

                String beneficios = plano.getBeneficios() == null
                        || plano.getBeneficios().isBlank()
                        ? "-" : plano.getBeneficios();

                String valor = "-";
                String forma = "-";
                String data = "-";

                if (pag != null) {
                    valor = String.valueOf(pag.getValor());
                    forma = pag.getForma_pagamento();

                    if (pag.getData_pag() != null) {
                        data = pag.getData_pag().toString();
                    }
                }

                escritor.write(
                        plano.getNome() + "#" +
                                beneficios + "#" +
                                valor + "#" +
                                forma + "#" +
                                data
                );

                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar plano: " + e.getMessage());
        }
    }

    public static void salvarPagamento(String cpf, Pagamento pagamento) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("pagamentos.txt", true))) {

            escritor.write(
                    cpf + "#" +
                            pagamento.getValor() + "#" +
                            pagamento.getForma_pagamento() + "#" +
                            pagamento.getData_pag()
            );

            escritor.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar pagamento: " + e.getMessage());
        }
    }

    public static void atualizarArquivo(ArrayList<Aluno> alunos) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("alunos.txt"))) {

            for (Aluno aluno : alunos) {

                Plano plano = aluno.getTipo();

                String nomePlano = "-";
                String inicio = "-";
                String fim = "-";

                if (plano != null) {
                    if (plano.getNome() != null) {
                        nomePlano = plano.getNome();
                    }
                    if (plano.getData_inicio() != null) {
                        inicio = plano.getData_inicio().toString();
                    }
                    if (plano.getData_final() != null) {
                        fim = plano.getData_final().toString();
                    }
                }

                escritor.write(
                        aluno.getId() + "#" +
                                aluno.getNome() + "#" +
                                aluno.getCpf() + "#" +
                                aluno.getTel() + "#" +
                                aluno.getEmail() + "#" +
                                aluno.getSenha() + "#" +
                                aluno.esta_ativo() + "#" +
                                nomePlano + "#" +
                                inicio + "#" +
                                fim
                );

                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo: " + e.getMessage());
        }
    }

    public static void salvarAtividades(ArrayList<Atividade> atividades) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("atividades.txt"))) {

            for (Atividade atividade : atividades) {
                String descricao = "-";
                if (atividade.getDescricao() != null && !atividade.getDescricao().isBlank()) {
                    descricao = atividade.getDescricao();
                }

                String cpfs = "";
                for (Aluno aluno : atividade.lista_alunos()) {
                    if (!cpfs.isEmpty()) {
                        cpfs += ",";
                    }

                    if (cpfs.isEmpty()) {
                        cpfs = "-";
                    }

                    escritor.write(atividade.getNome() + "#" +
                                    descricao + "#" +
                                    cpfs
                    );

                    escritor.newLine();
                }
            }
        }

        catch (IOException e) {
            System.out.println("Erro ao salvar atividades: " + e.getMessage());
        }
    }

    private static String lerTexto(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
    }

    private static void menu(ADM adm) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== ACADEMIA =====");
            System.out.println("1 - Alunos");
            System.out.println("2 - Matrícula");
            System.out.println("3 - Planos");
            System.out.println("4 - Atividades");
            System.out.println("5 - Pagamentos");
            System.out.println("0 - Sair");

            opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1:
                    menuAlunos(adm, sc);
                    break;
                case 2:
                    menuMatricula(adm, sc);
                    break;
                case 3:
                    menuPlanos(adm, sc);
                    break;
                case 4:
                    menuAtividades(adm, sc);
                    break;
                case 5:
                    menuPagamentos(adm, sc);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void menuAlunos(ADM adm, Scanner sc) {
        int opcao;

        do {
            System.out.println("\n--- ALUNOS ---");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Consultar aluno");
            System.out.println("3 - Atualizar aluno");
            System.out.println("4 - Excluir aluno");
            System.out.println("5 - Listar alunos");
            System.out.println("0 - Voltar");

            opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1: {
                    String nome = lerTexto(sc, "Nome: ");
                    String cpf = lerTexto(sc, "CPF: ");
                    String tel = lerTexto(sc, "Telefone: ");
                    String email = lerTexto(sc, "Email: ");
                    String senha = lerTexto(sc, "Senha: ");
                    adm.cadastrar_aluno(nome, cpf, tel, email, senha);
                    break;
                }
                case 2: {
                    String cpf = lerTexto(sc, "CPF do aluno: ");
                    adm.consultar_aluno(cpf);
                    break;
                }
                case 3: {
                    String cpf = lerTexto(sc, "CPF do aluno: ");
                    System.out.println("Deixe em branco o que não quiser alterar.");
                    String nome = lerTexto(sc, "Novo nome: ");
                    String tel = lerTexto(sc, "Novo telefone: ");
                    String email = lerTexto(sc, "Novo email: ");
                    adm.atualizar_aluno(cpf, nome, tel, email);
                    break;
                }
                case 4: {
                    String cpf = lerTexto(sc, "CPF do aluno: ");
                    adm.excluir_aluno(cpf);
                    break;
                }
                case 5:
                    adm.listar_alunos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }

        while (opcao != 0);
    }

    public static void menuMatricula(ADM adm, Scanner sc) {
        int opcao;

        do {
            System.out.println("\n--- MATRÍCULA ---");
            System.out.println("1 - Realizar matrícula");
            System.out.println("2 - Renovar matrícula");
            System.out.println("3 - Cancelar matrícula");
            System.out.println("0 - Sair");

            opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1: {
                    String cpf = lerTexto(sc, "CPF do aluno: ");
                    String plano = lerTexto(sc, "Nome do plano: ");
                    int meses = lerInteiro(sc, "Duração em meses: ");
                    adm.realizar_matricula(cpf, plano, meses);
                    break;
                }
                case 2: {
                    String cpf = lerTexto(sc, "CPF do aluno: ");
                    int meses = lerInteiro(sc, "Renovar por quantos meses: ");
                    adm.renovar_matricula(cpf, meses);
                    break;
                }
                case 3: {
                    String cpf = lerTexto(sc, "CPF do aluno: ");
                    adm.cancelar_matricula(cpf);
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        while (opcao != 0);
    }

    public static void menuPlanos(ADM adm, Scanner sc) {
        int opcao;

            System.out.println("\n--- PLANOS ---");
            System.out.println("1 - Cadastrar plano");
            System.out.println("2 - Consultar plano");
            System.out.println("3 - Atualizar plano");
            System.out.println("4 - Excluir plano");
            System.out.println("5 - Listar planos");
            System.out.println("0 - Voltar");

    }

    public static void menuAtividades(ADM adm, Scanner sc) {

    }

    public static void menuPagamentos(ADM adm, Scanner sc) {

    }



}

