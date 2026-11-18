import java.util.ArrayList;

public class Aluno extends Pessoa {

    protected int id;
    protected boolean estado= false;
    protected Plano tipo;
    protected ArrayList<Pagamento> historico_pagamentos = new ArrayList<>();
    private static int proximoid = 1;

    public boolean esta_ativo() {
        return estado;
    }

    public int getId(){
        return id;
    }

    public Plano getTipo(){
        return tipo;
    }

    public void setId(int id) {
        this.id = id;

        if (id >= proximoid) {
            proximoid = id + 1;
        }
    }

    public static int gerarId() {
        return proximoid++;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public void setTipo(Plano tipo){
        this.tipo = tipo;
    }

    public void ativar_aluno(){
        this.estado = true;
    }

    public void desativar_aluno(){
        this.estado = false;
    }

    public void cancelar_matricula() {
        this.estado = false;
        this.tipo = null;
    }

    public ArrayList<Pagamento> mostrar_historico_pagamentos() {
        return historico_pagamentos;
    }

    public void registrar_pag(Pagamento pagamento) {
        if (pagamento == null) {
            System.out.println("Pagamento inválido!");
            return;
        }

        historico_pagamentos.add(pagamento);
    }

}
