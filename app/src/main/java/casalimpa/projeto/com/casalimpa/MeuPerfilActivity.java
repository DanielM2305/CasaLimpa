package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;

public class MeuPerfilActivity extends MenusFuncionais {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);
    }

    public void editarPerfil(View view){
        Toast.makeText(this, "Editar Perfil", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), EditarPerfilActivity.class);
        startActivity(intent);
    }
    /* função só será incrementada quando houver comunicação com o banco de dados
    public void editarPerfilPrestadorServico(View view){
        Toast.makeText(this, "Editar Perfil Prestador Serviço", Toast.LENGTH_SHORT).show();

    }

    public void editarPerfilEmpregador(View view){
        Toast.makeText(this, "Editar Perfil Empregador", Toast.LENGTH_SHORT).show();

    }
*/


}
