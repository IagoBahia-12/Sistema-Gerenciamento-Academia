import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {
    
    @Test 
    void alunoDeveComecarInativo(){
        Aluno aluno = new Aluno();

        assertFalse(aluno.esta_ativo());
    }

    @Test 
    void deveAtivarAluno(){
        Aluno aluno = new Aluno();
        
        aluno.ativar_aluno();

        assertTrue(aluno.esta_ativo());
    }

    @Test 
    void deveDesativarAluno(){
        Aluno aluno = new Aluno();
        aluno.ativar_aluno();

        aluno.desativar_aluno();

        assertFalse(aluno.esta_ativo());
    }

    @Test 
    void deveCancelarMatricula(){
        Aluno aluno = new Aluno();
        Plano plano = new Plano();
        aluno.setTipo(plano);

        aluno.cancelar_matricula();

        assertFalse(aluno.esta_ativo());
        assertNull(aluno.getTipo());
    }

    @Test 
    void deveArmazenarDadosDaPessoa(){
        Aluno al = new Aluno();

        al.setNome("sofia");
        al.setCpf("54845789562");
        al.setEmail("sofia@gmail.com");
        al.setTel("77998756425");
        al.setSenha("soso10");

        assertEquals("sofia", al.getNome());
        assertEquals("54845789562", al.getCpf());
        assertEquals("sofia@gmail.com", al.getEmail());
        assertEquals("77998756425", al.getTel());
        assertEquals("soso10", al.getSenha());
    }

    @Test 
    void deveArmazenarID(){
        Aluno aluno = new Aluno();

        aluno.setId(15);

        assertEquals(15, aluno.getId());
    }

    @Test 
    void deveGerarIdSequencial(){
        int um = Aluno.gerarId();
        int dos = Aluno.gerarId();

        assertEquals(um+1, dos);
    }

    @Test
    void historicoDeveComecarVazio() {
        Aluno aluno = new Aluno();

        assertTrue(aluno.mostrar_historico_pagamentos().isEmpty());
    }

    @Test
    void naoDeveRegistrarPagamentoNulo() {
        Aluno aluno = new Aluno();

        aluno.registrar_pag(null);

        assertTrue(aluno.mostrar_historico_pagamentos().isEmpty());
    }
    
}
