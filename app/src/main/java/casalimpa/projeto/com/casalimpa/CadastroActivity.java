package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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

import casalimpa.projeto.com.casalimpa.entity.Endereco;
import casalimpa.projeto.com.casalimpa.service.EnderecoService;
import casalimpa.projeto.com.casalimpa.service.UsuarioService;

public class CadastroActivity extends AppCompatActivity {

    private EditText telEditText;
    private EditText cpfEditText;
    private EditText dataEditText;
    private EditText cepEditText;

    private EditText logradouroEditText;
    private EditText estadoEditText;
    private EditText cidadeEditText;
    private EditText bairroEditText;

    private RadioButton tipoPerfilPrestador;
    private RadioButton tipoPerfilEmpregador;
    private EditText nomeCompleto;
    private EditText email;
    private EditText login;
    private EditText senha;
    private EditText telefone;
    private EditText complementoEndereco;

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

        tipoPerfilPrestador = (RadioButton) findViewById(R.id.idTipoPerfilPrestador);
        tipoPerfilEmpregador = (RadioButton) findViewById(R.id.idTipoPerfilEmpregador);
        nomeCompleto = (EditText) findViewById(R.id.nomeCompleto);
        email = (EditText) findViewById(R.id.email);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);

        complementoEndereco = (EditText) findViewById(R.id.complementoEndereco);


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

                if (!hasFocus) {
                    EnderecoService enderecoService = new EnderecoService();

                    logradouroEditText.setText(null);
                    estadoEditText.setText(null);
                    cidadeEditText.setText(null);
                    bairroEditText.setText(null);
                    Endereco endereco = enderecoService.pesquisarCep(cepEditText.getText().toString());
                    if (endereco != null) {
                        logradouroEditText.setText(endereco.getLogradouro());
                        estadoEditText.setText(endereco.getEstado());
                        cidadeEditText.setText(endereco.getCidade());
                        bairroEditText.setText(endereco.getBairro());
                        idLogradouro = endereco.getIdLogradouro();
                    } else {
                        Toast.makeText(getApplicationContext(), "CEP não encontrado!", Toast.LENGTH_LONG).show();
                    }

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
            Boolean valoresPreenchidos = false;

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            if(this.login.getText() != null){
                parametros.add(new BasicNameValuePair("login", this.login.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.senha.getText() != null){
                parametros.add(new BasicNameValuePair("senha", this.senha.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.email.getText() != null){
                parametros.add(new BasicNameValuePair("email", this.email.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.cpfEditText.getText() != null){
                parametros.add(new BasicNameValuePair("docIdentificacao", this.cpfEditText.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.nomeCompleto.getText() != null){
                parametros.add(new BasicNameValuePair("nome", this.nomeCompleto.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.telEditText.getText() != null){
                parametros.add(new BasicNameValuePair("telefone", this.telEditText.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.dataEditText.getText() != null){
                parametros.add(new BasicNameValuePair("data_nascimento", this.dataEditText.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.idLogradouro != null){
                parametros.add(new BasicNameValuePair("idLogradouro", this.idLogradouro.toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(this.complementoEndereco.getText() != null){
                parametros.add(new BasicNameValuePair("complemento", this.complementoEndereco.getText().toString()));
            }else{
                valoresPreenchidos = true;
            }

            if(valoresPreenchidos.equals(false)){
            if (this.tipoPerfilEmpregador.isChecked()) {
                parametros.add(new BasicNameValuePair("tipoUsuario", "EMPREGRADO"));
            } else if (this.tipoPerfilPrestador.isChecked()) {
                parametros.add(new BasicNameValuePair("tipoUsuario", "EMPREGADOR"));
            } else {
                Toast.makeText(this, "É necessário escolher o tipo de usuário!", Toast.LENGTH_LONG).show();
            }

            UsuarioService usuarioService = new UsuarioService();
            Boolean resultado = usuarioService.cadastrarUsuario(parametros);
            if(resultado.equals(true)){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), PaginaInicioActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Ocorreu um erro! Tente novamente.", Toast.LENGTH_LONG).show();
            }
            }else{
                Toast.makeText(this, "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
