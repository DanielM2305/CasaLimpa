package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
    private String layoutInfo;

    public ServicosAdapter(Context context, ArrayList<Servicos> elementos) {
        super(context, R.layout.meus_servicos_res, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public ServicosAdapter(Context context, ArrayList<Servicos> elementos, String layoutInfo) {
        super(context, R.layout.meus_servicos_res, elementos);
        this.context = context;
        this.elementos = elementos;
        this.layoutInfo = layoutInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = null;


        if (layoutInfo != null && layoutInfo.equals("SERVICOS_PRESTADOS")) {
            rowView = inflater.inflate(R.layout.meus_servicos_prestados_res, parent, false);

        } else if (layoutInfo != null && layoutInfo.equals("BUSCA_SERVICOS")) {
            rowView = inflater.inflate(R.layout.meus_servicos_res_busca, parent, false);

        } else {
            rowView = inflater.inflate(R.layout.meus_servicos_res, parent, false);

        }


        TextView categoria = (TextView) rowView.findViewById(R.id.categoriaId);
        TextView prazoMedioSugerido = (TextView) rowView.findViewById(R.id.prazoMedioSugeridoId);
        TextView precoSugerido = (TextView) rowView.findViewById(R.id.precoSugeridoId);
        TextView prestadorServico = (TextView) rowView.findViewById(R.id.prestadorServicoId);
        TextView registroSalarial = (TextView) rowView.findViewById(R.id.registroSalarialId);
        TextView statusServico = (TextView) rowView.findViewById(R.id.statusServicoId);

        ImageView fotoServico = (ImageView) rowView.findViewById(R.id.fotoServicoId);

        categoria.setText(elementos.get(position).getCategoria());
        prazoMedioSugerido.setText(elementos.get(position).getPrazoMedioSugerido());
        precoSugerido.setText(elementos.get(position).getPrecoSugerido());
        prestadorServico.setText(elementos.get(position).getPrestadorServico());
        registroSalarial.setText(elementos.get(position).getRegistroSalarial());
        if (elementos.get(position).getBitmapServico() != null) {
            fotoServico.setImageBitmap(elementos.get(position).getBitmapServico());
        }

        if (elementos.get(position).getStatusAtual() != null) {
            statusServico.setText(elementos.get(position).getStatusAtual());
        }

        ImageButton chkDone = (ImageButton) rowView.findViewById(R.id.imageButtonSelection);
        if (chkDone != null) {

            chkDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View parentRow = (View) v.getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int position = listView.getPositionForView(parentRow);
                    System.out.println("I am in position " + position);
                    Integer idServico = elementos.get(position).getCodServico();
                    Intent intent = new Intent(v.getContext(), ContratarServicosFormActivity.class);
                    intent.putExtra("idServico", idServico);
                    context.startActivity(intent);
                }
            });
        }
        return rowView;
    }


}
