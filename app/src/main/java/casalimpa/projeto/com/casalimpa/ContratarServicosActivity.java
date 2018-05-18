package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;

public class ContratarServicosActivity extends MenusFuncionais {


    private Spinner cidades;
    private Spinner bairros;
    private Spinner categorias;
    private Button buscarServicos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_servicos);

        cidades = (Spinner) findViewById(R.id.idCidades);
        final ArrayAdapter adapterCidades = ArrayAdapter.createFromResource(this, R.array.cidades,
                android.R.layout.simple_spinner_dropdown_item);
        cidades.setAdapter(adapterCidades);

        bairros = (Spinner) findViewById(R.id.idBairros);
        final ArrayAdapter adapterBairros = ArrayAdapter.createFromResource(this, R.array.bairros,
                android.R.layout.simple_spinner_dropdown_item);
        bairros.setAdapter(adapterBairros);

        categorias = (Spinner) findViewById(R.id.idCategorias);
        final ArrayAdapter adapterCategorias = ArrayAdapter.createFromResource(this, R.array.categoria_servico,
                android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(adapterCategorias);

        buscarServicos = (Button) findViewById(R.id.idBuscarBotao);

        buscarServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContratarServicosActivity.this, "Buscar da base de dados...", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
