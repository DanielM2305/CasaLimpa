package casalimpa.projeto.com.casalimpa.service;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.api.ApiConnection;
import casalimpa.projeto.com.casalimpa.entity.Endereco;

public class UsuarioService {


    public Boolean cadastrarUsuario(List<NameValuePair> parametros) {
        Boolean resultado = false;
        try {
            ApiConnection apiConnection = new ApiConnection();

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoCadastroUsuario());
            if(jsonObject.get("result") != null && jsonObject.get("result").equals("true")){
                resultado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Boolean logar(List<NameValuePair> parametros, Context contexto) {
        Boolean resultado = false;
        try {
            ApiConnection apiConnection = new ApiConnection();

            JSONObject jsonObject = apiConnection.getConnection(parametros, apiConnection.getRequisicaoLoginUsuario());
            if(jsonObject.get("result") != null && jsonObject.get("result").equals("true")){
                resultado = true;
            }

            SharedPreferences preferences = contexto.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);

            preferences.edit().putString("idUsuarioLogado", jsonObject.get("idUsuario").toString()).commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }


}
