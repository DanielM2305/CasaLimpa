package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by danie on 17/04/2018.
 */

/*

Atenção: MeusServicosActivity, Servicos.java, ServicosAdapter.java e activity_meus_servicos.xml
                  Funcionam em conjunto qualquer alteração pode gerar erros pois elas juntas são o ListView de Serviços
*/

public class ServicosAdapter extends ArrayAdapter<Servicos> {

    private final Context context;
    private final ArrayList<Servicos> elementos;

    public ServicosAdapter(Context context, ArrayList<Servicos> elementos) {
        super(context, R.layout.meus_servicos_res, elementos);
        this.context = context;
        this.elementos = elementos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.meus_servicos_res, parent, false);


        TextView  categoria          = (TextView) rowView.findViewById(R.id.categoriaId);
        TextView  prazoMedioSugerido = (TextView) rowView.findViewById(R.id.prazoMedioSugeridoId);
        TextView  precoSugerido      = (TextView) rowView.findViewById(R.id.precoSugeridoId);
        TextView  prestadorServico   = (TextView) rowView.findViewById(R.id.prestadorServicoId);
        TextView  registroSalarial   = (TextView) rowView.findViewById(R.id.registroSalarialId);
        ImageView fotoServico        = (ImageView) rowView.findViewById(R.id.fotoServicoId);

                 categoria.setText(elementos.get(position).getCategoria());
                 prazoMedioSugerido.setText(elementos.get(position).getPrazoMedioSugerido());
                 precoSugerido.setText(elementos.get(position).getPrecoSugerido());
                 prestadorServico.setText(elementos.get(position).getPrestadorServico());
                 registroSalarial.setText(elementos.get(position).getRegistroSalarial());
                 fotoServico.setImageResource(elementos.get(position).getFotoServico());
        return rowView;
    }


}
