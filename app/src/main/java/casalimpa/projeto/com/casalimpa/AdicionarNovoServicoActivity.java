package casalimpa.projeto.com.casalimpa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import casalimpa.projeto.com.casalimpa.service.ServicoService;


public class AdicionarNovoServicoActivity extends AppCompatActivity {

    private ImageView imagemAddNovoServico;
    private Button botaoAcessarGaleria;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSION_REQUEST = 2;
    private Spinner categoriaServico;
    private List<JSONObject> categoriasJson;
    private EditText prazoAtendimento, precoSugerido, obervacoesImportantes;
    private Bitmap imagemServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_novo_servico);

        try {
            List<String> categoriasString = new ArrayList<String>();
            categoriasJson = new ArrayList<JSONObject>();

            SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
            String idUsuarioLogado = preferences.getString("idUsuarioLogado", null);
            ServicoService servicoService = new ServicoService();
            JSONObject jsonObject = servicoService.getCategoriasServico(idUsuarioLogado);


            JSONArray jsonCategorias = null;
            if (jsonObject.get("result").toString().equals("true")) {
                jsonCategorias = new JSONArray(jsonObject.get("categoriasServico").toString());

                for (int i = 0; i < jsonCategorias.length(); i++) {
                    JSONObject categoria = jsonCategorias.getJSONObject(i);
                    categoriasJson.add(categoria);
                    categoriasString.add(categoria.get("nome") + " - NDF: " + categoria.get("nivel_dificuldade"));
                }
            }


            categoriaServico = (Spinner) findViewById(R.id.categoriaServicoSpinner);
            prazoAtendimento = (EditText) findViewById(R.id.prazoAtendimento);
            precoSugerido = (EditText) findViewById(R.id.precoSugerido);
            obervacoesImportantes = (EditText) findViewById(R.id.observacoesImportantes);

            categoriaServico.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoriasString));


            //Começa aqui acesso a imagem

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST);
                }
            }

            imagemAddNovoServico = findViewById(R.id.imagemNovoServicoId);
            botaoAcessarGaleria = findViewById(R.id.botaoAcessarGaleriaId);
            botaoAcessarGaleria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    Toast.makeText(AdicionarNovoServicoActivity.this, "Selecione uma imagem da galeria", Toast.LENGTH_SHORT).show();
                    startActivityForResult(intent, GALERIA_IMAGENS);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS) {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnindex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnindex);
            c.close();
            //A Imagem fica salva na varialvel do tipo Bitmap
            imagemServico = (BitmapFactory.decodeFile(picturePath));
            imagemAddNovoServico.setImageBitmap(imagemServico);
            botaoAcessarGaleria.setText("Alterar foto");//Alterar Texto do botão

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permission[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {

            }
            return;
        }
    }
//Termina aqui acesso a imagem


    public void uploadImagem(View view) {
        Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_SHORT).show();

    }


    public void salvarServico(View view) {

        try {
            Boolean valoresPreenchidos = false;

            if(this.prazoAtendimento.getText() != null  && this.prazoAtendimento.getText().length() > 0
                    && this.precoSugerido.getText() != null && this.precoSugerido.getText().length() > 0
                    && this.obervacoesImportantes.getText() != null && this.obervacoesImportantes.getText().length() > 0 ){
                valoresPreenchidos = true;
            }

            if(valoresPreenchidos == true) {
                SharedPreferences preferences = this.getSharedPreferences("casalimpa.projeto.com.casalimpa", Context.MODE_PRIVATE);
                String idUsuarioLogado = preferences.getString("idUsuarioLogado", null);

                List<NameValuePair> parametros = new ArrayList<NameValuePair>();
                parametros.add(new BasicNameValuePair("idCategoriaServico",
                        this.categoriasJson.get(this.categoriaServico.getSelectedItemPosition()).get("cod_categoria_servico").toString()));
                parametros.add(new BasicNameValuePair("idUsuario", idUsuarioLogado));

                parametros.add(new BasicNameValuePair("prazoServico", this.prazoAtendimento.getText().toString()));
                parametros.add(new BasicNameValuePair("preco", this.precoSugerido.getText().toString()));
                parametros.add(new BasicNameValuePair("observacoes", this.obervacoesImportantes.getText().toString()));

                if(imagemServico != null){
                     ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imagemServico.compress(Bitmap.CompressFormat.JPEG, 90, stream); //compress to which format you want.
                    byte [] byte_arr = stream.toByteArray();
                    String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                    parametros.add(new BasicNameValuePair("imagemServico",image_str));
                }

                ServicoService servicoService = new ServicoService();
                JSONObject result = servicoService.salvarServico(parametros);

                if (result.get("result").equals("true")) {
                    Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Ocorreu um erro ao salvar!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Ocorreu um erro tente novamente!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void cancelarCadastroServico(View view) {

        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }

}
