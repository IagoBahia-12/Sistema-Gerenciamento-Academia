public class Pessoa {

    protected String nome;
    protected String cpf;
    protected String tel;
    protected String email;
    protected String senha;

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getTel(){
        return tel;
    }

    public String getEmail(){
        return email;
    }


    public String getSenha(){
        return senha;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

}
