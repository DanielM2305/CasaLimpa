package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;

public class MeusServicosActivity extends MenusFuncionais {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);
    }

    public void adicionarNovoServico(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarNovoServicoActivity.class);
        startActivity(intent);

    }
}
