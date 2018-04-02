package casalimpa.projeto.com.casalimpa.funcoesExternas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import casalimpa.projeto.com.casalimpa.MeuPerfilActivity;
import casalimpa.projeto.com.casalimpa.MeusServicosActivity;
import casalimpa.projeto.com.casalimpa.PaginaInicioActivity;


/**
 * Created by danie on 02/04/2018.
 */

public class MenusFuncionais extends AppCompatActivity {

    public void meusServicosBotao(View view){
        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }
/*
    public void contratarServicosBotao(View view){
        Intent intent = new Intent(getApplicationContext(), RedicerionamentoActivity.class);
        startActivity(intent);
    }
*/
/*
    public void servicosPrestadosBotao(View view){
        Intent intent = new Intent(getApplicationContext(), RedicerionamentoActivity.class);
        startActivity(intent);
    }
*/
/*
    public void avaliacoesBotao(View view){
        Intent intent = new Intent(getApplicationContext(), RedicerionamentoActivity.class);
        startActivity(intent);
    }
*/
    public void meuperfilBotao(View view){
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }

    public void sairBotao(View view){
        Intent intent = new Intent(getApplicationContext(), PaginaInicioActivity.class);
        startActivity(intent);
    }

}
