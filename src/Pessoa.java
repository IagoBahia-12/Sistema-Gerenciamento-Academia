public class Pessoa {

    protected String nome;
    protected String cpf;
    protected String tel;
    protected String email;
    protected String login;
    protected String senha;

    String getNome(){
        return nome;
    }

    String getCpf(){
        return cpf;
    }

    String getTel(){
        return tel;
    }

    String getEmail(){
        return email;
    }

    String getLogin(){
        return login;
    }

    String getSenha(){
        return senha;
    }

    void setNome(String nome){
        this.nome = nome;
    }

    void setCpf(String cpf){
        this.cpf = cpf;
    }

    void setTel(String tel){
        this.tel = tel;
    }

    void setEmail(String email){
        this.email = email;
    }

    void setLogin(String login){
        this.login = login;
    }

    void setSenha(String senha){
        this.senha = senha;
    }

}
