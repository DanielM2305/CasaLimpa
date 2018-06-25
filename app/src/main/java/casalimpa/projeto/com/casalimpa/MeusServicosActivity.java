package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.ServicoService;


/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/
public class MeusServicosActivity extends MenusFuncionais {

    private ArrayList<Servicos> servicos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);
        try {
            servicos = new ArrayList<Servicos>();

            SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
            String idUsuarioLogado = preferences.getString("idUsuarioLogado", null);
            ServicoService servicoService = new ServicoService();
            JSONObject jsonObject = servicoService.getServicosUsuario(idUsuarioLogado);


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

            ListView meus_servicos_res = (ListView) findViewById(R.id.ServicosListViewId);
            ArrayAdapter adapter = new ServicosAdapter(this, servicos);
            meus_servicos_res.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void desativarServico(View view) {
        Toast.makeText(this, "Excluir Serviço", Toast.LENGTH_SHORT).show();
    }


    public void adicionarNovoServico(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarNovoServicoActivity.class);
        startActivity(intent);
    }
}