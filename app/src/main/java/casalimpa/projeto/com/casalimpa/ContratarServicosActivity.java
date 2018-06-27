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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.entity.Endereco;
import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.EnderecoService;
import casalimpa.projeto.com.casalimpa.service.ServicoService;

public class ContratarServicosActivity extends MenusFuncionais {


    private Spinner cidades;
    private Spinner bairros;
    private Spinner categorias;
    private Button buscarServicos;
    private List<JSONObject> categoriasJson;
    private List<JSONObject> cidadesJson;
    private List<JSONObject> bairrosJson;
    private List<String> bairrosString;
    private String idUsuarioLogado;
    private ArrayList<Servicos> servicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_servicos);

        try {

            List<String> categoriasString = new ArrayList<String>();
            categoriasJson = new ArrayList<JSONObject>();
            categoriasString.add("Todas as categorias");
            JSONObject jsonCategoria = new JSONObject();
            jsonCategoria.put("cod_categoria_servico", "0");
            jsonCategoria.put("nome", "Todas as categorias");
            categoriasJson.add(jsonCategoria);


            List<String> cidadesString = new ArrayList<String>();
            cidadesJson = new ArrayList<JSONObject>();
            cidadesString.add("Todas as cidades");
            JSONObject jsonCidade = new JSONObject();
            jsonCidade.put("idCidade", "0");
            jsonCidade.put("nomeCidade", "Todas as cidades");
            cidadesJson.add(jsonCidade);

            bairrosString = new ArrayList<String>();
            bairrosJson = new ArrayList<JSONObject>();
            bairrosString.add("Todos os bairros");
            JSONObject jsonBairro = new JSONObject();
            jsonBairro.put("idBairro", "0");
            jsonBairro.put("nomeBairro", "Todos os bairros");
            bairrosJson.add(jsonBairro);


            SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
            idUsuarioLogado = preferences.getString("idUsuarioLogado", null);
            ServicoService servicoService = new ServicoService();
            JSONObject jsonObject = servicoService.getCategoriasServico(idUsuarioLogado);


            JSONArray jsonCategorias = null;
            if (jsonObject.get("result").toString().equals("true")) {
                jsonCategorias = new JSONArray(jsonObject.get("categoriasServico").toString());

                for (int i = 0; i < jsonCategorias.length(); i++) {
                    JSONObject categoria = jsonCategorias.getJSONObject(i);
                    categoriasJson.add(categoria);
                    categoriasString.add(categoria.get("nome") + " - NDF: " + categoria.get("nivel_dificuldade"));
                }
            }


            EnderecoService enderecoService = new EnderecoService();
            JSONObject jsonObjectCidades = enderecoService.getCidades(idUsuarioLogado);


            JSONArray jsonCidades = null;
            if (jsonObjectCidades.get("result").toString().equals("true")) {
                jsonCidades = new JSONArray(jsonObjectCidades.get("cidades").toString());

                for (int i = 0; i < jsonCategorias.length(); i++) {
                    JSONObject cidade = jsonCidades.getJSONObject(i);
                    cidadesJson.add(cidade);
                    cidadesString.add(cidade.get("nomeCidade").toString());
                }
            }


            categorias = (Spinner) findViewById(R.id.idCategorias);
            categorias.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoriasString));

            cidades = (Spinner) findViewById(R.id.idCidades);
            cidades.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cidadesString));

            bairros = (Spinner) findViewById(R.id.idBairros);
            bairros.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bairrosString));

            cidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    buscarBairros();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            buscarServicos = (Button) findViewById(R.id.idBuscarBotao);

            buscarServicos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        servicos = new ArrayList<Servicos>();

                        String idCategoria = categoriasJson.get(categorias.getSelectedItemPosition()).get("cod_categoria_servico").toString();
                        String idCidade = cidadesJson.get(cidades.getSelectedItemPosition()).get("idCidade").toString();
                        String idBairro = bairrosJson.get(bairros.getSelectedItemPosition()).get("idBairro").toString();

                        ServicoService servicoService = new ServicoService();
                        JSONObject jsonObject = servicoService.getServicosPorFiltro(idUsuarioLogado, idCategoria, idCidade, idBairro);


                        JSONArray jsonServicos = null;
                        if (jsonObject.get("result").toString().equals("true")) {
                            jsonServicos = new JSONArray(jsonObject.get("servicos").toString());
                            for (int i = 0; i < jsonServicos.length(); i++) {

                                Servicos servico = new Servicos(
                                        Integer.parseInt(jsonServicos.getJSONObject(i).get("codigoServico").toString()),
                                        jsonServicos.getJSONObject(i).get("nomeServico").toString(),
                                        jsonServicos.getJSONObject(i).get("prazoServico").toString(),
                                        jsonServicos.getJSONObject(i).get("precoServico").toString(),
                                        jsonServicos.getJSONObject(i).get("nome").toString(),
                                        jsonServicos.getJSONObject(i).get("registro_salarial").toString(),
                                        0,
                                        null,
                                        null
                                );


                                try {
                                    String base64String = jsonServicos.getJSONObject(i).get("imagemPrincipal").toString();

                                    if (base64String != null && base64String != "") {
                                        // String base64Image = base64String.split(",")[1];
                                        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
                                        Bitmap imagemFinal = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        servico.setBitmapServico(imagemFinal);
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                servicos.add(servico);

                            }
                        }

                        ListView meus_servicos_res = (ListView) findViewById(R.id.ServicosListViewId);
                        ArrayAdapter adapter = new ServicosAdapter(ContratarServicosActivity.this, servicos, "BUSCA_SERVICOS");
                        meus_servicos_res.setAdapter(adapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void buscarBairros() {
        try {

            bairrosString = new ArrayList<String>();
            bairrosJson = new ArrayList<JSONObject>();
            bairrosString.add("Todos os bairros");
            JSONObject jsonBairro = new JSONObject();
            jsonBairro.put("idBairro", "0");
            jsonBairro.put("nomeBairro", "Todas os bairros.");
            bairrosJson.add(jsonBairro);

            bairros = (Spinner) findViewById(R.id.idBairros);

            Long idCidade = new Long(this.cidadesJson.get(this.cidades.getSelectedItemPosition()).get("idCidade").toString());

            if (idCidade > 0) {
                EnderecoService enderecoService = new EnderecoService();
                JSONObject jsonObjectBairros = enderecoService.getBairros("0", idCidade.toString());


                JSONArray bairrosJsonArray = null;
                if (jsonObjectBairros.get("result").toString().equals("true")) {
                    bairrosJsonArray = new JSONArray(jsonObjectBairros.get("bairros").toString());

                    for (int i = 0; i < bairrosJsonArray.length(); i++) {
                        JSONObject bairro = bairrosJsonArray.getJSONObject(i);
                        bairrosJson.add(bairro);
                        bairrosString.add(bairro.get("nomeBairro").toString());
                    }
                    bairros.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bairrosString));

                }
            } else {
                Toast.makeText(ContratarServicosActivity.this, "Selecione uma cidade!", Toast.LENGTH_SHORT);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContratarServicosActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT);
        }
    }


}
