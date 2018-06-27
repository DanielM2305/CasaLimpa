package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.entity.Endereco;
import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.EnderecoService;
import casalimpa.projeto.com.casalimpa.service.ServicoService;
import casalimpa.projeto.com.casalimpa.service.UsuarioService;

public class ContratarServicosFormActivity extends AppCompatActivity {

    private Spinner turnos;
    private EditText complementoEndereco;
    private EditText dataInicio;
    private EditText dataFim;
    private EditText horario;
    private EditText cep;
    private EditText enderecoCompleto;
    private TextView observacaoPrestador;
    private TextView prazoMedio;
    private TextView categoriaServico;
    private TextView nomePrestador;
    private TextView cidadePrincipal;
    private String idServico;
    private String idUsuarioLogado;
    private String idLogradouro;
    private List<String> turnosString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_servico_form);

        turnos = (Spinner) findViewById(R.id.turnos);
        complementoEndereco = (EditText) findViewById(R.id.complementoEndereco);
        dataInicio = (EditText) findViewById(R.id.dataInicio);
        dataFim = (EditText) findViewById(R.id.dataFim);
        horario = (EditText) findViewById(R.id.horario);
        cep = (EditText) findViewById(R.id.cep);
        enderecoCompleto = (EditText) findViewById(R.id.enderecoCompleto);
        observacaoPrestador = (TextView) findViewById(R.id.observacaoPrestador);
        prazoMedio = (TextView) findViewById(R.id.prazoMedio);
        categoriaServico = (TextView) findViewById(R.id.categoriaServico);
        nomePrestador = (TextView) findViewById(R.id.nomePrestador);
        cidadePrincipal = (TextView) findViewById(R.id.cidadePrincipal);


        //Criando mascara para o campo de Data
        SimpleMaskFormatter smfData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwDataInicio = new MaskTextWatcher(dataInicio, smfData);
        dataInicio.addTextChangedListener(mtwDataInicio);

        MaskTextWatcher mtwDataFim = new MaskTextWatcher(dataFim, smfData);
        dataFim.addTextChangedListener(mtwDataFim);
        ;
        //final da mascara Data

        //Criandao mascara para o campo de CEP
        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NN.NNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(cep, smfCep);
        cep.addTextChangedListener(mtwCep);

        //Criandao mascara para o campo de Hora
        SimpleMaskFormatter smfHora = new SimpleMaskFormatter("NN:NN");
        MaskTextWatcher mtwHora = new MaskTextWatcher(horario, smfHora);
        horario.addTextChangedListener(mtwHora);


        turnosString = new ArrayList<String>();
        turnosString.add("MATUTINO");
        turnosString.add("VESPERTINO");
        turnosString.add("INTEGRAL");

        turnos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, turnosString));


        try {
            Bundle extras = getIntent().getExtras();
            SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
            idUsuarioLogado = preferences.getString("idUsuarioLogado", null);

            if (extras != null) {
                idServico = "" + extras.getInt("idServico");
                ServicoService servicoService = new ServicoService();
                JSONObject jsonServicos = servicoService.getServicoPorId(idUsuarioLogado, idServico.toString());


                if (jsonServicos != null && jsonServicos.get("result").equals("true")) {
                    JSONObject servico = new JSONObject(jsonServicos.getString("servico"));
                    observacaoPrestador.setText(servico.get("observacoesServico").toString());
                    prazoMedio.setText(servico.get("prazoServico").toString());
                    categoriaServico.setText(servico.get("nomeServico").toString());
                    nomePrestador.setText(servico.get("nome").toString());
                    cidadePrincipal.setText(servico.get("nomeCidade").toString());
                }
            }

            cep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {

                    if (!hasFocus) {
                        EnderecoService enderecoService = new EnderecoService();


                        Endereco endereco = enderecoService.pesquisarCep(cep.getText().toString());
                        if (endereco != null) {
                            idLogradouro = endereco.getIdLogradouro().toString();
                            enderecoCompleto.setText(endereco.getLogradouro() + " " + endereco.getCidade() + " " + endereco.getEstado());

                        } else {
                            Toast.makeText(getApplicationContext(), "CEP não encontrado!", Toast.LENGTH_LONG).show();
                        }

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void contratarServico(View view) throws IOException {

        try {
            Boolean valoresPreenchidos = false;

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("idUsuario", this.idUsuarioLogado));
            parametros.add(new BasicNameValuePair("codServico", this.idServico));
            parametros.add(new BasicNameValuePair("turnoPreferencia", this.turnosString.get(this.turnos.getSelectedItemPosition())));


            if (this.dataInicio.getText() != null) {
                parametros.add(new BasicNameValuePair("dataInicio", this.dataInicio.getText().toString()));
            } else {
                valoresPreenchidos = true;
            }

            if (this.dataFim.getText() != null) {
                parametros.add(new BasicNameValuePair("dataFim", this.dataFim.getText().toString()));
            } else {
                valoresPreenchidos = true;
            }

            if (this.horario.getText() != null) {
                parametros.add(new BasicNameValuePair("horario", this.horario.getText().toString()));
            } else {
                valoresPreenchidos = true;
            }

            if (this.idLogradouro != null) {
                parametros.add(new BasicNameValuePair("idLogradouro", this.idLogradouro.toString()));
            } else {
                valoresPreenchidos = true;
            }

            if (this.complementoEndereco.getText() != null) {
                parametros.add(new BasicNameValuePair("complemento", this.complementoEndereco.getText().toString()));
            } else {
                valoresPreenchidos = true;
            }

            if (valoresPreenchidos.equals(false)) {

              ServicoService servicoService = new ServicoService();
                Boolean resultado = servicoService.contratarServico(parametros);
                if (resultado.equals(true)) {
                    Toast.makeText(this, "Serviço solicitado com sucesso!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(view.getContext(), Dashboard.class);
                    view.getContext().startActivity(intent);
                } else {
                    Toast.makeText(this, "Ocorreu um erro! Tente novamente.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
