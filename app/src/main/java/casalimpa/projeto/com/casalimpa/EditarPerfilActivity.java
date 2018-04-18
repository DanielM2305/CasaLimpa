package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class EditarPerfilActivity extends AppCompatActivity {


    private EditText telEditText;
    private EditText cpfEditText;
    private EditText dataEditText;
    private EditText cepEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);


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


    public void salvarEdicao(View view){
        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
}
