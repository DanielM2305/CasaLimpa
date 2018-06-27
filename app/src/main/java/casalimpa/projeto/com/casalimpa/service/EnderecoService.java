package casalimpa.projeto.com.casalimpa.service;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.api.ApiConnection;
import casalimpa.projeto.com.casalimpa.entity.Endereco;

public class EnderecoService {


    public Endereco pesquisarCep(String cep) {
        Endereco endereco = null;
        try {
            ApiConnection apiConnection = new ApiConnection();

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("cep", cep.replace(".", "").replace("-", "")));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoCEP());

            if (jsonObject.get("result").equals("true")) {
                endereco =  new Endereco();
                endereco.setLogradouro(jsonObject.get("logradouro").toString());
                endereco.setEstado(jsonObject.get("uf").toString());
                endereco.setBairro(jsonObject.get("bairro").toString());
                endereco.setCidade(jsonObject.get("cidade").toString());
                endereco.setIdLogradouro(new Long(jsonObject.get("idLogradouro").toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endereco;
    }

    public JSONObject getCidades(String idUsuario) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("idOption", "GET_CIDADES"));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoBuscarServicos());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getBairros(String idUsuario, String idCidade) {

        try {
            ApiConnection apiConnection = new ApiConnection();
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("idUsuario", idUsuario));
            parametros.add(new BasicNameValuePair("idCidade", idCidade));
            parametros.add(new BasicNameValuePair("idOption", "GET_BAIRROS_POR_CIDADE"));

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoBuscarServicos());
            if (jsonObject.get("result") != null && jsonObject.get("result").equals("true")) {
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
