package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class AdicionarNovoServicoActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_novo_servico);

        Spinner categoriaServico = (Spinner) findViewById(R.id.categoriaServicoSpinner);
        final ArrayAdapter adapterCategoriaServico = ArrayAdapter.createFromResource(this, R.array.categoria_servico,
                android.R.layout.simple_spinner_dropdown_item);
        categoriaServico.setAdapter(adapterCategoriaServico);

    }
    public void uploadImagem (View view){
        Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_SHORT).show();
    }



    public void salvarServico(View view){

        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }
    public void cancelarCadastroServico(View view){

        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }

}
