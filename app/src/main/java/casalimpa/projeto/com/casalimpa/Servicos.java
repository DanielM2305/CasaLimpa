package casalimpa.projeto.com.casalimpa;

/**
 * Created by danie on 17/04/2018.
 */

/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/

public class Servicos {



    private String categoria,
            prazoMedioSugerido,
            precoSugerido,
            prestadorServico,
            registroSalarial;

    private int fotoServico;

    public Servicos(String categoria, String prazoMedioSugerido, String precoSugerido, String prestadorServico, String registroSalarial, int fotoServico) {
        this.categoria = categoria;
        this.prazoMedioSugerido = prazoMedioSugerido;
        this.precoSugerido = precoSugerido;
        this.prestadorServico = prestadorServico;
        this.registroSalarial = registroSalarial;
        this.fotoServico = fotoServico;

    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrazoMedioSugerido() {
        return prazoMedioSugerido;
    }

    public void setPrazoMedioSugerido(String prazoMedioSugerido) {
        this.prazoMedioSugerido = prazoMedioSugerido;
    }

    public String getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(String precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    public String getPrestadorServico() {
        return prestadorServico;
    }

    public void setPrestadorServico(String prestadorServico) {
        this.prestadorServico = prestadorServico;
    }

    public String getRegistroSalarial() {
        return registroSalarial;
    }

    public void setRegistroSalarial(String registroSalarial) {
        this.registroSalarial = registroSalarial;
    }

    public int getFotoServico() {
        return fotoServico;
    }

    public void setFotoServico(int fotoServico) {
        this.fotoServico = fotoServico;
    }
}

