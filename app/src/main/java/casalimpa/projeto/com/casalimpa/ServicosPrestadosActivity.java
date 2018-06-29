package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.ServicoService;


/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/
public class ServicosPrestadosActivity extends MenusFuncionais {

    private ArrayList<Servicos> servicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_prestados);
        try {
            servicos = new ArrayList<Servicos>();

            SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
            String idUsuarioLogado = preferences.getString("idUsuarioLogado", null);
            ServicoService servicoService = new ServicoService();
            JSONObject jsonObject = servicoService.getServicosRealizadosPorUsuario(idUsuarioLogado);


            JSONArray jsonServicos = null;
            if (jsonObject.get("result").toString().equals("true")) {
                jsonServicos = new JSONArray(jsonObject.get("servicos").toString());
                for (int i = 0; i < jsonServicos.length(); i++) {

                    Servicos servico = new Servicos(
                            Integer.parseInt(jsonServicos.getJSONObject(i).get("id_os").toString()),
                            jsonServicos.getJSONObject(i).get("dataInicio").toString(),
                            jsonServicos.getJSONObject(i).get("dataFim").toString(),
                            jsonServicos.getJSONObject(i).get("nomeProfissional").toString(),
                            jsonServicos.getJSONObject(i).get("nomeServico").toString(),
                            jsonServicos.getJSONObject(i).get("descricaoSituacaoOS").toString(),
                            0,
                            null,
                            null
                    );
/*
                    {\"id_os\":\"27\",\"" +
                            "numero_os\":\"12270618224454\",\"" +
                            "dt_inicio\":\"2018-08-20\",\"" +
                            "dt_fim\":\"2018-08-30\",\"" +
                            "turno_pref\":\"MATUTINO\",\"" +
                            "horario\":\"08:00\",\"" +
                            "id_cliente\":\"2\",\"" +
                            "contrato_prestacao_servico_numero_contrato\":null,\"" +
                            "id_situacao_os\":\"1\",\"" +
                            "id_profissional\":\"5\",\"" +
                            "id_logradouro\":\"34881\",\"" +
                            "cod_servico\":\"26\",\"" +
                            "complemento_endereco\":\"40\",\"" +
                            "dataInicio\":\"20\\\/08\\\/2018\",\"" +
                            "dataFim\":\"30\\\/08\\\/2018\",\"" +
                            "nomeProfissional\":\"MARCOS CORPORATION SA\",\"" +
                            "nomeCliente\":\"MARCOS VASCONCELOS\",\"" +
                            "descricaoSituacaoOS\":\"AGUARDANDO CONFIRMA\\u00c7\\u00c3O DO PRESTADOR DE SERVI\\u00c7OS\",\"" +
                            "codigoServico\":\"26\",\"" +
                            "prazoServico\":\"23h\",\"" +
                            "precoServico\":\"231\",\"" +
                            "nomeServico\":\"Alimenta\\u00e7\\u00e3o\",\"" +
                            "nome\":\"MARCOS CORPORATION SA\",\"
                        imagemPrincipal*/

                    try {
                        String base64String = jsonServicos.getJSONObject(i).get("imagemPrincipal").toString();

                        if (base64String != null && base64String != ""){
                            // String base64Image = base64String.split(",")[1];
                            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
                            Bitmap imagemFinal = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            servico.setBitmapServico(imagemFinal);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    servicos.add(servico);

                }
            }

            ListView meus_servicos_res = (ListView) findViewById(R.id.idServicosPrestadosListView);
            ArrayAdapter adapter = new ServicosAdapter(this, servicos, "SERVICOS_PRESTADOS");
            meus_servicos_res.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void maisInformacoesServico(View view){
        Toast.makeText(this, "Buscar da base de dados...", Toast.LENGTH_SHORT).show();
    }

}