import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class AtividadeTest {

    @Test
    void deveArmazenarNome() {
        Atividade atividade = new Atividade();

        atividade.setNome("Musculação");

        assertEquals("Musculação", atividade.getNome());
    }

    @Test
    void deveArmazenarDescricao() {
        Atividade atividade = new Atividade();

        atividade.setDescricao("Treinamento de força");

        assertEquals("Treinamento de força", atividade.getDescricao());
    }

    @Test
    void quantidadeInicialDeveSerZero() {
        Atividade atividade = new Atividade();

        assertEquals(0, atividade.qtd_alunos());
    }

    @Test
    void deveAdicionarAluno() {
        Atividade atividade = new Atividade();
        Aluno aluno = new Aluno();

        atividade.adc_aluno(aluno);

        assertEquals(1, atividade.qtd_alunos());
        assertTrue(atividade.lista_alunos().contains(aluno));
    }

    @Test
    void naoDeveAdicionarAlunoDuplicado() {
        Atividade atividade = new Atividade();
        Aluno aluno = new Aluno();

        atividade.adc_aluno(aluno);
        atividade.adc_aluno(aluno);

        assertEquals(1, atividade.qtd_alunos());
    }

    @Test
    void naoDeveAdicionarAlunoNulo() {
        Atividade atividade = new Atividade();

        atividade.adc_aluno(null);

        assertEquals(0, atividade.qtd_alunos());
    }

    @Test
    void deveRemoverAluno() {
        Atividade atividade = new Atividade();
        Aluno aluno = new Aluno();

        atividade.adc_aluno(aluno);
        atividade.remove_aluno(aluno);

        assertEquals(0, atividade.qtd_alunos());
        assertFalse(atividade.lista_alunos().contains(aluno));
    }

    @Test
    void naoDeveAlterarQuantidadeAoRemoverAlunoInexistente() {
        Atividade atividade = new Atividade();
        Aluno aluno = new Aluno();

        atividade.remove_aluno(aluno);

        assertEquals(0, atividade.qtd_alunos());
    }

    @Test
    void deveRetornarListaDeAlunos() {
        Atividade atividade = new Atividade();
        Aluno aluno = new Aluno();

        atividade.adc_aluno(aluno);

        ArrayList<Aluno> lista = atividade.lista_alunos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertTrue(lista.contains(aluno));
    }
}


