package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
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

    public void editarPerfilPrincipal(View view){
        Toast.makeText(this, "Editar Perfil Principal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditarPerfilPrincipalActivity.class);
        startActivity(intent);
    }

    public void editarPerfilPrestadorServico(View view){
        Toast.makeText(this, "Editar Perfil Prestador Serviço", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditarPerfilPrestadorServicoActivity.class);
        startActivity(intent);
    }


    public void editarPerfilEmpregador(View view){
        Toast.makeText(this, "Editar Perfil Empregador", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditarPerfilEmpregadorActivity.class);
        startActivity(intent);
    }


}
