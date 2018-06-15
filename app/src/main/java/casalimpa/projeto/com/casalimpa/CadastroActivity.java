package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private EditText telEditText;
    private EditText cpfEditText;
    private EditText dataEditText;
    private EditText cepEditText;

    private EditText logradouroEditText;
    private EditText estadoEditText;
    private EditText cidadeEditText;
    private EditText bairroEditText;

    private Long idLogradouro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        /*
        Passos para criar a mascara]
           Crie um id para o campo no arquvio XML
           Crie uma variavel no java exemplo private EditText telEditText;
           Dentro da classe onCreate
           recupere o ID via findViewById
           telEditText = (EditText) findViewById(R.id.telEditTextId);

         Use os seguintes comandos de exemplo

        SimpleMaskFormatter smfTel = new SimpleMaskFormatter("(NN)NNNNN NNNN");
        MaskTextWatcher mtwTel = new MaskTextWatcher(telEditText, smfTel);
        telEditText.addTextChangedListener(mtwTel);

        */

     //recuperando id
        cpfEditText = (EditText) findViewById(R.id.cpfEditTextId);
        telEditText = (EditText) findViewById(R.id.telEditTextId);
        dataEditText = (EditText) findViewById(R.id.dataEditTextId);
        cepEditText = (EditText) findViewById(R.id.cepEditTextId);
        logradouroEditText = (EditText) findViewById(R.id.logradouroEditTextId);
        estadoEditText = (EditText) findViewById(R.id.estadoEditTextId);
        cidadeEditText = (EditText) findViewById(R.id.cidadeEditTextId);
        bairroEditText = (EditText) findViewById(R.id.bairroEditTextId);

        //Mascara de Cpf
        SimpleMaskFormatter smfCpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtwCpf = new MaskTextWatcher(cpfEditText, smfCpf);
        cpfEditText.addTextChangedListener(mtwCpf);

        //Final da Mascara CPF

        //Criando mascara para o campo de Telefone
//testar if
        //if (telEditText. ==  ){

        SimpleMaskFormatter smfTel = new SimpleMaskFormatter("(NN)NNNN-NNNNN");
        MaskTextWatcher mtwTel = new MaskTextWatcher(telEditText, smfTel);
        telEditText.addTextChangedListener(mtwTel);
          //  }else{

    /*     SimpleMaskFormatter smfTel = new SimpleMaskFormatter("(NN)NNNN-NNNN");
        MaskTextWatcher mtwTel = new MaskTextWatcher(telEditText, smfTel);
        telEditText.addTextChangedListener(mtwTel);
   // }*/



        //final da mascara Telefone

        //Criando mascara para o campo de Data
        SimpleMaskFormatter smfData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwData = new MaskTextWatcher(dataEditText, smfData);
        dataEditText.addTextChangedListener(mtwData);
        //final da mascara Data

        //Criandao mascara para o campo de CEP
        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NN.NNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(cepEditText, smfCep);
        cepEditText.addTextChangedListener(mtwCep);


        cepEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                try {
                if(!hasFocus) {

                    logradouroEditText.setText(null);
                    estadoEditText.setText(null);
                    cidadeEditText.setText(null);
                    bairroEditText.setText(null);

                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost("http://192.168.43.113:80/casaLimpa/android/api_cep_request.php");

                    List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                    pairs.add(new BasicNameValuePair("cep", cepEditText.getText().toString().replace(".","").replace("-","")));

                    HttpResponse response = null;
                    String responseAsString = null;

                        post.setEntity(new UrlEncodedFormEntity(pairs));
                        response = client.execute(post);
                        responseAsString = EntityUtils.toString(response.getEntity());

                        JSONObject json = new JSONObject(responseAsString);
                        if(json.get("result").equals("true")){
                            logradouroEditText.setText(json.get("logradouro").toString());
                            estadoEditText.setText(json.get("uf").toString());
                            cidadeEditText.setText(json.get("cidade").toString());
                            bairroEditText.setText(json.get("bairro").toString());
                            idLogradouro = new Long(json.get("idLogradouro").toString());
                        }else{
                            Toast.makeText(getApplicationContext(), "CEP não encontrado, impossível prosseguir.", Toast.LENGTH_LONG).show();
                        }


                    /*if(responseAsString != null){
                        Toast.makeText(getApplicationContext(), responseAsString, Toast.LENGTH_SHORT).show();
                    }*/


                }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //final da mascara de CEP

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



    }


    public void cadastrarUsuario(View view) throws IOException {

       try {


        Intent intent = new Intent(getApplicationContext(), PaginaInicioActivity.class);
        startActivity(intent);


           HttpClient client = new DefaultHttpClient();
           HttpPost post = new HttpPost("http://192.168.43.113:80/casaLimpa/android/api.php");

           List<NameValuePair> pairs = new ArrayList<NameValuePair>();
           pairs.add(new BasicNameValuePair("parametro1", "JAKU"));
           pairs.add(new BasicNameValuePair("parametro2", "FABU"));
           post.setEntity(new UrlEncodedFormEntity(pairs));
           HttpResponse response = client.execute(post);

           String responseAsString = EntityUtils.toString(response.getEntity());

           Toast.makeText(this, responseAsString, Toast.LENGTH_SHORT).show();
/*
        HttpPost httppost = new
                HttpPost("http://192.168.43.113:80/casaLimpa/android/api.php");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("parametro1", "daniel111"));
        nameValuePairs.add(new BasicNameValuePair("parametro2", "marcos222"));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        Log.i("postData", response.getStatusLine().toString());
*/  }catch (ClientProtocolException cpe){
          System.out.print(cpe.getMessage());

    }catch (IOException e){
        System.out.print(e.getMessage());
    }
    }








}
