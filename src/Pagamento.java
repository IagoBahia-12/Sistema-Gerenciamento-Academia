import java.time.LocalDate;

public class Pagamento {

    protected double valor;
    protected String forma_pagamento;
    protected LocalDate data_pag;

    double getValor(){
        return valor;
    }

    String getForma_pagamento(){
        return forma_pagamento;
    }

    LocalDate getData_pag(){
        return data_pag;
    }

    void setValor(double valor){
        this.valor = valor;
    }

    void setForma_pagamento(String forma_pagamento){
        this.forma_pagamento = forma_pagamento;
    }

    void setData_pag(LocalDate data_pag1){
        this.data_pag = data_pag1;
    }

    protected void registrar_pag(){}

}
