package casalimpa.projeto.com.casalimpa;

import android.graphics.Bitmap;

/**
 * Created by danie on 17/04/2018.
 */

/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/

public class Servicos {



    private String categoria;
    private String prazoMedioSugerido;
    private String precoSugerido;
    private String prestadorServico;
    private String registroSalarial;

    private String statusAtual;
    private int codServico, fotoServico;
    private Bitmap bitmapServico;

    public Servicos(String categoria, String prazoMedioSugerido, String precoSugerido, String prestadorServico, String registroSalarial, int fotoServico) {
        this.categoria = categoria;
        this.prazoMedioSugerido = prazoMedioSugerido;
        this.precoSugerido = precoSugerido;
        this.prestadorServico = prestadorServico;
        this.registroSalarial = registroSalarial;
        this.fotoServico = fotoServico;

    }

    public Servicos(String categoria, String prazoMedioSugerido, String precoSugerido, String prestadorServico, String registroSalarial, int fotoServico, String statusAtual) {
        this.categoria = categoria;
        this.prazoMedioSugerido = prazoMedioSugerido;
        this.precoSugerido = precoSugerido;
        this.prestadorServico = prestadorServico;
        this.registroSalarial = registroSalarial;
        this.fotoServico = fotoServico;
        this.statusAtual = statusAtual;

    }

    public Servicos(Integer codServico, String categoria, String prazoMedioSugerido, String precoSugerido, String prestadorServico, String registroSalarial, int fotoServico, String statusAtual, Bitmap bitmapServico) {
        this.codServico = codServico;
        this.categoria = categoria;
        this.prazoMedioSugerido = prazoMedioSugerido;
        this.precoSugerido = precoSugerido;
        this.prestadorServico = prestadorServico;
        this.registroSalarial = registroSalarial;
        this.fotoServico = fotoServico;
        this.statusAtual = statusAtual;
        this.bitmapServico = bitmapServico;
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

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }


    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public Bitmap getBitmapServico() {
        return bitmapServico;
    }

    public void setBitmapServico(Bitmap bitmapServico) {
        this.bitmapServico = bitmapServico;
    }
}

