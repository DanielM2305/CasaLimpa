package casalimpa.projeto.com.casalimpa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class AdicionarNovoServicoActivity extends AppCompatActivity {

    private ImageView imagemAddNovoServico;
    private Button botaoAcessarGaleria;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSION_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_novo_servico);

        Spinner categoriaServico = (Spinner) findViewById(R.id.categoriaServicoSpinner);
        final ArrayAdapter adapterCategoriaServico = ArrayAdapter.createFromResource(this, R.array.categoria_servico,
                android.R.layout.simple_spinner_dropdown_item);
        categoriaServico.setAdapter(adapterCategoriaServico);

        //Começa aqui acesso a imagem

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
            } else{
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
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS){
            Uri selectedImage = data.getData();
            String[]filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnindex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnindex);
            c.close();
            //A Imagem fica salva na varialvel do tipo Bitmap
            Bitmap salvarImagemAddServico = (BitmapFactory.decodeFile(picturePath) );
            imagemAddNovoServico.setImageBitmap(salvarImagemAddServico);
            botaoAcessarGaleria.setText("Alterar foto");//Alterar Texto do botão

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permission[], int[] grantResults){
        if (requestCode == PERMISSION_REQUEST){
            if (grantResults.length > 0
                    && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            }else{

            }
            return;
        }
    }
//Termina aqui acesso a imagem




    public void uploadImagem (View view){
        Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_SHORT).show();

    }



    public void salvarServico(View view){

        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }
    public void cancelarCadastroServico(View view){

        Intent intent = new Intent(getApplicationContext(), MeusServicosActivity.class);
        startActivity(intent);
    }

}
