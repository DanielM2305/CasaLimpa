package casalimpa.projeto.com.casalimpa.entity;

public class Endereco {
    private Long idLogradouro;
    private String bairro;
    private String Logradouro;
    private String cidade;
    private String estado;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(Long idLogradouro) {
        this.idLogradouro = idLogradouro;
    }
}
