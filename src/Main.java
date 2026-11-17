import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Aluno> alunos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("src/alunos.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
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

                alunos.add(aluno);
            }

        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        for(Aluno a: alunos){
            System.out.println(a.getNome());
        }
    }
}