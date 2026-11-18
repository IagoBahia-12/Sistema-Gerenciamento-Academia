import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ADMTest{

    @Test 
    void buscarAlunoPeloCpf(){
        ADM adm = new ADM();
        Aluno aluno = new Aluno();

        aluno.setNome("monara");
        aluno.setCpf("7785468744");

        adm.carregar_aluno(aluno);

        Aluno enc = adm.buscar_aluno("7785468744");

        assertNotNull(enc);
        assertEquals("monara", enc.getNome());
    }

    @Test 
    void deveRetornarNuloQndalunoNExistir(){
        ADM adm = new ADM();
        Aluno nenc = adm.buscar_aluno("58798965244");

        assertNull(nenc);
    }

    @Test 
    void retonor(){

    }
}