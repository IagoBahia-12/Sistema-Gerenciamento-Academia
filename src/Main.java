import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        ADM adm = new ADM();

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

            escritor.write(
                    aluno.getId() + "#" +
                            aluno.getNome() + "#" +
                            aluno.getCpf() + "#" +
                            aluno.getTel() + "#" +
                            aluno.getEmail() + "#" +
                            aluno.getSenha() + "#" +
                            aluno.esta_ativo()
            );

            escritor.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public static void atualizarArquivo(ArrayList<Aluno> alunos) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("src/alunos.txt"))) {

            for (Aluno aluno : alunos) {
                escritor.write(
                        aluno.getId() + "#" +
                                aluno.getNome() + "#" +
                                aluno.getCpf() + "#" +
                                aluno.getTel() + "#" +
                                aluno.getEmail() + "#" +
                                aluno.getSenha() + "#" +
                                aluno.esta_ativo()
                );

                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo: " + e.getMessage());
        }
    }
}

