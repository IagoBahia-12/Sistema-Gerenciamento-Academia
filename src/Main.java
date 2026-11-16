import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try (BufferedReader leitor = new BufferedReader(
                new FileReader("src/alunos.txt"))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}