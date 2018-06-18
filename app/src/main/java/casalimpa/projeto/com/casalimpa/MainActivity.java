package casalimpa.projeto.com.casalimpa;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.service.UsuarioService;


public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);



    }

    public void loginEntrar(View view) {
        Boolean valoresPreenchidos = false;

        List<NameValuePair> parametros = new ArrayList<NameValuePair>();
        if (this.login.getText() != null && this.login.getText().toString().length() > 0) {
            parametros.add(new BasicNameValuePair("login", this.login.getText().toString()));
        } else {
            valoresPreenchidos = true;
        }

        if (this.senha.getText() != null  && this.senha.getText().toString().length() > 0) {
            parametros.add(new BasicNameValuePair("senha", this.senha.getText().toString()));
        } else {
            valoresPreenchidos = true;
        }
        if (valoresPreenchidos.equals(false)) {
            UsuarioService usuarioService = new UsuarioService();
            Boolean resultado = usuarioService.logar(parametros, this.getApplicationContext());
            if (resultado.equals(true)) {
                Toast.makeText(this, "Logou com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplication(), Dashboard.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Ocorreu um erro! Tente novamente.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();
        }

    }

}









