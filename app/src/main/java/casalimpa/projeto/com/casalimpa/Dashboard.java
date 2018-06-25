package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.ServicoService;

public class Dashboard extends MenusFuncionais {

    private TextView CCLIAguardandoConfirmar;
    private TextView CPROFAguardandoConfirmar;
    private TextView CCLIAguardandoRealizacao;
    private TextView CPROFAguardandoRealizacao;
    private TextView CPROFAguardandoAvaliacao;
    private TextView CCLIAguardandoAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        this.CCLIAguardandoConfirmar = (TextView) findViewById(R.id.CCLIAguardandoConfirmar);
        this.CPROFAguardandoConfirmar = (TextView) findViewById(R.id.CPROFAguardandoConfirmar);
        this.CCLIAguardandoRealizacao = (TextView) findViewById(R.id.CCLIAguardandoRealizacao);
        this.CPROFAguardandoRealizacao = (TextView) findViewById(R.id.CPROFAguardandoRealizacao);
        this.CPROFAguardandoAvaliacao = (TextView) findViewById(R.id.CPROFAguardandoAvaliacao);
        this.CCLIAguardandoAvaliacao = (TextView) findViewById(R.id.CCLIAguardandoAvaliacao);

        SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
        String idUsuarioLogado = preferences.getString("idUsuarioLogado", null);
        ServicoService servicoService = new ServicoService();
        JSONObject jsonObject = servicoService.getContadoresServicosPorUsuario(idUsuarioLogado);

        try {
            if(jsonObject.get("CCLIAguardandoConfirmar") != null){
            this.CCLIAguardandoConfirmar.setText(jsonObject.get("CCLIAguardandoConfirmar").toString() + " Serviços aguardando a confirmação de Prestadores de Serviço.");
            }

            if(jsonObject.get("CPROFAguardandoConfirmar") != null){
                this.CPROFAguardandoConfirmar.setText(jsonObject.get("CPROFAguardandoConfirmar").toString()+ " Serviços aguardando sua confirmação como Prestador do Serviço.");
            }

            if(jsonObject.get("CCLIAguardandoRealizacao") != null){
                this.CCLIAguardandoRealizacao.setText(jsonObject.get("CCLIAguardandoRealizacao").toString()+ " Serviços aguardando realização dos Prestadores de Serviços.");
            }

            if(jsonObject.get("CPROFAguardandoRealizacao") != null){
                this.CPROFAguardandoRealizacao.setText(jsonObject.get("CPROFAguardandoRealizacao").toString()+ " Serviços aguardando sua realização.");
            }

            if(jsonObject.get("CPROFAguardandoAvaliacao") != null){
                this.CPROFAguardandoAvaliacao.setText(jsonObject.get("CPROFAguardandoAvaliacao").toString()+ " Serviços aguardando sua realização.");
            }

            if(jsonObject.get("CCLIAguardandoAvaliacao") != null){
                this.CCLIAguardandoAvaliacao.setText(jsonObject.get("CCLIAguardandoAvaliacao").toString()+ " Serviços aguardando sua Avaliação.");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
