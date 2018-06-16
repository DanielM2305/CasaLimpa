package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class EditarPerfilPrestadorServicoActivity extends AppCompatActivity {


    private EditText nomePerfilPrestadorServico;
    private EditText telPerfilPrestadorServico;
    private EditText registroSalarialPerfilPrestadorServico;
    private EditText areaAtuacaoPerfilPrestadorServico;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_prestador_servico);


        //recuperando id
        nomePerfilPrestadorServico = (EditText) findViewById(R.id.nomePerfilPrestadorServico);
        telPerfilPrestadorServico = (EditText) findViewById(R.id.telPerfilPrestadorServico);
        registroSalarialPerfilPrestadorServico = (EditText) findViewById(R.id.registroSalarialPerfilPrestadorServico);
        areaAtuacaoPerfilPrestadorServico = (EditText) findViewById(R.id.areaAtuacaoPerfilPrestadorServico);


        //mascara telefone

        SimpleMaskFormatter smfTel = new SimpleMaskFormatter("(NN)NNNN-NNNNN");
        MaskTextWatcher mtwTel = new MaskTextWatcher(telPerfilPrestadorServico, smfTel);
        telPerfilPrestadorServico.addTextChangedListener(mtwTel);

        //final da mascara Telefone

    }

    public void salvarEdicaoPerfilPrestadorServico(View view){
        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
    public void cancelarEdicaoPerfilPrestadorServico(View view){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
}
