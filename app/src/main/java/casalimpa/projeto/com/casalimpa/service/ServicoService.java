package casalimpa.projeto.com.casalimpa.service;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.api.ApiConnection;

public class ServicoService {

    public JSONObject getContadoresServicosPorUsuario(String idUsuario) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoDashboard());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getServicosUsuario(String idUsuario) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("idOption", "GET_SERVICOS_USUARIO"));


            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoServico());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getServicosRealizadosPorUsuario(String idUsuario) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("idOption", "GET_SERVICOS_USUARIO_REALIZADOS"));


            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoServico());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getCategoriasServico(String idUsuario) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("idOption", "CATEGORIA_SERVICO"));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoServico());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getServicosPorFiltro(String idUsuario, String idCidade, String idBairro, String idCategoria) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("idCidade", idCidade));
            parametros.add(new BasicNameValuePair("idBairro", idBairro));
            parametros.add(new BasicNameValuePair("idCategoria", idCategoria));
            parametros.add(new BasicNameValuePair("idOption", "GET_SERVICOS"));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoBuscarServicos());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getServicoPorId(String idUsuario, String idServico) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("codServico", idServico));


            parametros.add(new BasicNameValuePair("idOption", "GET_SERVICO_POR_ID"));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoBuscarServicos());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject salvarServico(List<NameValuePair> parametros) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            parametros.add(new BasicNameValuePair("idOption", "SALVAR_SERVICO"));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoServico());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean contratarServico(List<NameValuePair> parametros) {

        try {
            ApiConnection apiConnection = new ApiConnection();

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoContratarServico());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public JSONObject getAvaliacoesUsuario(String idUsuario) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoAvaliacoes());
            if (jsonObject.get("resultProfissional") != null && jsonObject.get("resultProfissional").equals("true") || jsonObject.get("resultCliente") != null && jsonObject.get("resultCliente").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
