package casalimpa.projeto.com.casalimpa.api;

import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ApiConnection {


    public String getRequisicaoCEP() {
        return "api_cep_request.action.php";
    }

    public String getRequisicaoCadastroUsuario() {
        return "api_cadastro_usuario.action.php";
    }

    public String getRequisicaoLoginUsuario() {
        return "api_login.action.php";
    }

    public String getRequisicaoDashboard() {
        return "api_dashboard.action.php";
    }

    public String getRequisicaoServico() {
        return "api_servico.action.php";
    }

    public String getRequisicaoBuscarServicos() {
        return "api_buscar_servicos.action.php";
    }

    public String getRequisicaoContratarServico() {
        return "api_contratar_servico.action.php";
    }

    public String getRequisicaoAvaliacoes() {
        return "api_avaliacoes.action.php";
    }



    public JSONObject getConnection(List<NameValuePair> parametros, String requisicao) {
        JSONObject jsonObject = null;
        try {

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://54.226.103.216:80/casaLimpa/android/" + requisicao);

            HttpResponse response = null;
            String responseAsString = null;

            post.setEntity(new UrlEncodedFormEntity(parametros));
            response = client.execute(post);
            responseAsString = EntityUtils.toString(response.getEntity());

            for(int i = 0; i<12; i++){
                System.out.println("-------------------------DEBUG-----------------");
            }
            System.out.println(responseAsString);
            for(int i = 0; i<12; i++){
                System.out.println("-------------------------DEBUG-----------------");
            }

            jsonObject = new JSONObject(responseAsString);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
