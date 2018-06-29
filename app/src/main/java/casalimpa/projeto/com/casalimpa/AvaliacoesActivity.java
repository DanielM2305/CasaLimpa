package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.ServicoService;

public class AvaliacoesActivity extends MenusFuncionais {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacoes);

        try {
            SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
            String idUsuarioLogado = preferences.getString("idUsuarioLogado", null);
            ServicoService servicoService = new ServicoService();
            JSONObject jsonObject = servicoService.getAvaliacoesUsuario(idUsuarioLogado);


            if (jsonObject.get("resultProfissional").toString().equals("true")) {
                JSONObject avaliacaoProfissional = new JSONObject(jsonObject.get("avaliacaoProfissional").toString());

                TextView textViewClientesSatisfeitosId = (TextView) findViewById(R.id.clientesSatisfeitosId);
                textViewClientesSatisfeitosId.setText(avaliacaoProfissional.get("total_satisfeitos").toString());


                TextView textViewclientesInsatisfeitosIdId = (TextView) findViewById(R.id.clientesInsatisfeitosIdId);
                textViewclientesInsatisfeitosIdId.setText(avaliacaoProfissional.get("total_reclamacoes").toString());

                TextView textViewnotaQualificacao = (TextView) findViewById(R.id.mediaId);
                textViewnotaQualificacao.setText(avaliacaoProfissional.get("media_atendimento").toString());


            }


            if (jsonObject.get("resultCliente").toString().equals("true")) {
                JSONObject avaliacaoCliente = new JSONObject(jsonObject.get("avaliacaoCliente").toString());

                TextView textViewClientesSatisfeitosId = (TextView) findViewById(R.id.percentualAvaliacoesId);
                textViewClientesSatisfeitosId.setText(avaliacaoCliente.get("totalSatisfacao").toString());


                TextView textViewclientesInsatisfeitosIdId = (TextView) findViewById(R.id.qualificacoesRealizadasId);
                textViewclientesInsatisfeitosIdId.setText(avaliacaoCliente.get("totalQualificacoes").toString());

                TextView textViewnotaQualificacao = (TextView) findViewById(R.id.notaFinalId);
                textViewnotaQualificacao.setText(avaliacaoCliente.get("notaQualificacao").toString());


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}