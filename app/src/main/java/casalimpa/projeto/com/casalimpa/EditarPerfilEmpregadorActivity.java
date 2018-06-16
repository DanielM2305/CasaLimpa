package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class EditarPerfilEmpregadorActivity extends AppCompatActivity {

    private EditText nomePerfilEmpregador;
    private EditText telPerfilEmpregador;
    private EditText dataPerfilEmpregador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_empregador);
        //recuperando id
        nomePerfilEmpregador = (EditText) findViewById(R.id.nomePerfilEmpregador);
        telPerfilEmpregador = (EditText) findViewById(R.id.telPerfilEmpregador);
        dataPerfilEmpregador = (EditText) findViewById(R.id.dataPerfilEmpregador);

        //mascara telefone

        SimpleMaskFormatter smfTel = new SimpleMaskFormatter("(NN)NNNN-NNNNN");
        MaskTextWatcher mtwTel = new MaskTextWatcher(telPerfilEmpregador, smfTel);
        telPerfilEmpregador.addTextChangedListener(mtwTel);

        //final da mascara Telefone

        //Criando mascara para o campo de Data
        SimpleMaskFormatter smfData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwData = new MaskTextWatcher(dataPerfilEmpregador, smfData);
        dataPerfilEmpregador.addTextChangedListener(mtwData);
        //final da mascara Data

    }

    public void salvarEdicaoPerfilEmpregador(View view){
        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
    public void cancelarEdicaoPerfilEmpregador(View view){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
}

