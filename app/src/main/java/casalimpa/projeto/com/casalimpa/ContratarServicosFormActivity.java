package casalimpa.projeto.com.casalimpa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.funcoesExternas.MenusFuncionais;
import casalimpa.projeto.com.casalimpa.service.EnderecoService;
import casalimpa.projeto.com.casalimpa.service.ServicoService;

public class ContratarServicosFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_servico_form);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer someVariable = extras.getInt("idServico");
           System.out.print(someVariable);
        }


    }



}
