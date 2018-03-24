package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PainelDeControleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_de_controle);
    }

    public void telaMeusServicos(View view){
        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }

    public void telaContratarServico(View view){

    }
    public void telaServicoPrestados(View view){

    }
    public void telaAvaliacoes(View view){


    }
    public void telaMeuPerfil(View view){
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
    public void telaBotaosair(View view){
        Intent intent = new Intent(getApplicationContext(), PaginaInicioActivity.class);
        startActivity(intent);
    }
    public void mudar(View view){
        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }


}
