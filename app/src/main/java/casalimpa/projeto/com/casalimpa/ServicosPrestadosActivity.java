package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;


/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/
public class ServicosPrestadosActivity extends MenusFuncionais {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_prestados);

        ListView servicosPrestados = (ListView) findViewById(R.id.idServicosPrestadosListView);
        ArrayAdapter adapterServicosPrestados = new ServicosAdapter(this, retornarServicosPrestados(),"SERVICOS_PRESTADOS");
        servicosPrestados.setAdapter(adapterServicosPrestados);

/**/
    }

    public void maisInformacoesServico(View view){
        Toast.makeText(this, "Buscar da base de dados...", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Servicos> retornarServicosPrestados() {
        ArrayList<Servicos> servicos = new ArrayList<Servicos>();
        Servicos e;
        e = new Servicos("Alimentação 'Exemplo'",
                "56hr", "3,00R$",
                "Marcos", "1,00R$"
                , R.drawable.ico_casalimpa, "Aguardando Avaliação - Prestador");
        servicos.add(e);


        e = new Servicos("Instalação e Manuntenção de Equipamentos 'Exemplo'",
                "22hr", "7,00R$",
                "Daniel", "1.000,00R$"
                , R.drawable.ico_casalimpa, "Aguardando Realização");
        servicos.add(e);


        e = new Servicos("Regar os Couves 'Exemplo'",
                "4hr", "8.000,00R$",
                "João das Couves", "9.500,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);


        return servicos;
    }
}