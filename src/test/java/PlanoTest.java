import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class PlanoTest {

    @Test 
    void deveArmazenarDadosdoPlano(){
        Plano pla = new Plano();

        pla.setNome("Mensal");
        pla.setBeneficios("Nenhum");

        assertEquals("Mensal", pla.getNome());
        assertEquals("Nenhum", pla.getBeneficios());
    }

    @Test 
    void planoDeveComecarAtivo() {
        Plano plano = new Plano();

        assertTrue(plano.ativo);
    }

    @Test 
    void deveRealizarMatricula() {
        Plano plano = new Plano();
        LocalDate inicio = LocalDate.of(2026, 11, 18);

        plano.realizar_matricula(inicio, 3);

        assertEquals(inicio, plano.getData_inicio());
        assertEquals(LocalDate.of(2027, 2, 18), plano.getData_final());
        assertTrue(plano.ativo);
    }

    @Test
    void deveCancelarMatricula(){
        Plano plano = new Plano();


        plano.cancelar_matricula();
        assertFalse(plano.ativo);

    }
    
    @Test 
    void naoDeveRenovarPlanoSemMatricula(){
        Plano plano = new Plano();

        boolean r = plano.renovar_matricula(3);
        assertFalse(r);
    }

    @Test 
    void  naoDeveRenovarPlanoCancelado(){
        Plano plano = new Plano();

        plano.realizar_matricula(LocalDate.now(), 5);
        plano.cancelar_matricula();
        
        boolean r = plano.renovar_matricula(1);

        assertFalse(r);
    }

    @Test 
    void deveRenovarPlanoAtivo(){
        Plano plano = new Plano();

        plano.realizar_matricula(LocalDate.now(), 5);
        boolean r = plano.renovar_matricula(1);

        assertTrue(r);
        assertEquals(LocalDate.now(), plano.getData_inicio());
        assertEquals(LocalDate.now().plusMonths(6), plano.getData_final()); //em plus precisa por a a soma dos meses
    }

    @Test 
    void deveCopiarPlano(){
        Plano original = new Plano();
        original.setNome("Plano Premium");
        original.setBeneficios("Acesso completo");

        Plano copia = original.copiar();

        assertNotSame(original, copia);
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getBeneficios(), copia.getBeneficios());
    }

}
