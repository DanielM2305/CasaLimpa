package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;



/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/
public class MeusServicosActivity extends MenusFuncionais {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);

        ListView meus_servicos_res = (ListView) findViewById(R.id.ServicosListViewId);
        ArrayAdapter adapter = new ServicosAdapter(this, adicionarServico());
        meus_servicos_res.setAdapter(adapter);

/**/
    }

    private ArrayList<Servicos> adicionarServico() {
        ArrayList<Servicos> servicos = new ArrayList<Servicos>();
        Servicos e;
        e = new Servicos("Alimentação 'Exemplo'",
                "1hr", "5,00R$",
                "Jackson", "10,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);


        e = new Servicos("Instalação e Manuntenção de Equipamentos 'Exemplo'",
                "10hr", "700,00R$",
                "Daniel", "3.000,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);

        e = new Servicos("Manutenção Preventiva 'Exemplo'",
                "15hr", "700,00R$",
                "Marcos", "3.000,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);

        e = new Servicos("Organizador do Lar 'Exemplo'",
                "25hr", "2.000,00R$",
                "Fabiana", "2.500,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);

        e = new Servicos("Limpeza 'Exemplo'",
                "5hr", "500,00R$",
                "Ari", "600,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);

        e = new Servicos("Pequenos Reparos 'Exemplo'",
                "2hr", "600,00R$",
                "Akson", "500,00R$"
                , R.drawable.ico_casalimpa);
        servicos.add(e);


        return servicos;
    }


    public void adicionarNovoServico(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarNovoServicoActivity.class);
        startActivity(intent);
    }
}