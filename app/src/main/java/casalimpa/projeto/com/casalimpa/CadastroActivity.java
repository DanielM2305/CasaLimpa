package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastroActivity extends AppCompatActivity {

    private EditText telEditText;
    private EditText cpfEditText;
    private EditText dataEditText;
    private EditText cepEditText;

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

        //final da mascara de CEP







    }


    public void cadastrarUsuario(View view){
        Intent intent = new Intent(getApplicationContext(), PaginaInicioActivity.class);
        startActivity(intent);

    }




}
