import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        ADM adm = new ADM();

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("src/planos.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split("#");

                Plano plano = new Plano();
                plano.setNome(campos[0]);

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
                new FileReader("src/alunos.txt"))) {

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

                if (campos.length >= 10 && !campos[7].equals("-")) {
                    Plano plano = new Plano();

                    plano.setNome(campos[7]);

                    Plano modelo = adm.buscar_plano(campos[7]);
                    if (modelo != null) {
                        plano.setBeneficios(modelo.getBeneficios());
                    }

                    if (!campos[8].equals("-")) {
                        plano.setData_inicio(LocalDate.parse(campos[8]));
                    }

                    if (!campos[9].equals("-")) {
                        plano.setData_final(LocalDate.parse(campos[9]));
                    }

                    aluno.setTipo(plano);
                }

                adm.carregar_aluno(aluno);
            }

        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        adm.listar_alunos();
    }

    public static void salvarAluno(Aluno aluno) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("src/alunos.txt", true))) {

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
                new FileWriter("src/planos.txt"))) {

            for (Plano plano : planos) {

                String beneficios = "-";
                if (plano.getBeneficios() != null && !plano.getBeneficios().isBlank()) {
                    beneficios = plano.getBeneficios();
                }

                escritor.write(plano.getNome() + "#" + beneficios);
                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar plano: " + e.getMessage());
        }
    }

    public static void atualizarArquivo(ArrayList<Aluno> alunos) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("src/alunos.txt"))) {

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
}

