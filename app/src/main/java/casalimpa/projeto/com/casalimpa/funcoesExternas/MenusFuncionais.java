package casalimpa.projeto.com.casalimpa.funcoesExternas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import casalimpa.projeto.com.casalimpa.AvaliacoesActivity;
import casalimpa.projeto.com.casalimpa.ContratarServicosActivity;
import casalimpa.projeto.com.casalimpa.MeuPerfilActivity;
import casalimpa.projeto.com.casalimpa.MeusServicosActivity;
import casalimpa.projeto.com.casalimpa.PaginaInicioActivity;
import casalimpa.projeto.com.casalimpa.ServicosPrestadosActivity;


/**
 * Created by danie on 02/04/2018.
 */

public class MenusFuncionais extends AppCompatActivity {

    public void meusServicosBotao(View view){
        Toast.makeText(this, "Meus Serviços", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }

    public void contratarServicosBotao(View view){
        Toast.makeText(this, "Contratar Serviços", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ContratarServicosActivity.class);
        startActivity(intent);
    }

    public void servicosPrestadosBotao(View view){
        Toast.makeText(this, "Serviços Prestados", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ServicosPrestadosActivity.class);
        startActivity(intent);
    }


    public void avaliacoesBotao(View view){
        Toast.makeText(this, "Avaliações", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AvaliacoesActivity.class);
        startActivity(intent);
    }

    public void meuperfilBotao(View view){
        Toast.makeText(this, "Meu Perfil", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }

    public void sairBotao(View view){
        Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), PaginaInicioActivity.class);
        startActivity(intent);
    }

}
