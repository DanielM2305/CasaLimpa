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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
public class EditarPerfilPrincipalActivity extends AppCompatActivity {

    private EditText loginPerfilPrincipal;
    private EditText emailPerfilPrincipal;
    private EditText cpfPerfilPrincipal;
    private EditText cepPerfilPrincipal;

    private ImageView imagemPerfilPrincipal;
    private Button botaoAcessarGaleriaPerfilPrincipal;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSION_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_principal);

        //recuperando id

        loginPerfilPrincipal = (EditText) findViewById(R.id.loginPerfilPrincipal);
        emailPerfilPrincipal = (EditText) findViewById(R.id.loginPerfilPrincipal);
        cpfPerfilPrincipal = (EditText) findViewById(R.id.cpfPerfilPrincipal);
        cepPerfilPrincipal = (EditText) findViewById(R.id.cepEditTextId);


        //Mascara de Cpf
        SimpleMaskFormatter smfCpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtwCpf = new MaskTextWatcher(cpfPerfilPrincipal, smfCpf);
        cpfPerfilPrincipal.addTextChangedListener(mtwCpf);

        //Final da Mascara CPF

        //Criandao mascara para o campo de CEP
        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NN.NNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(cepPerfilPrincipal, smfCep);
        cepPerfilPrincipal.addTextChangedListener(mtwCep);

        //final da mascara de CEP


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

        imagemPerfilPrincipal = findViewById(R.id.imagemNovoServicoIdPerfilPrincipal);
        botaoAcessarGaleriaPerfilPrincipal = findViewById(R.id.botaoAcessarGaleriaPerfilPrincipalId);
        botaoAcessarGaleriaPerfilPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Toast.makeText(getApplicationContext(), "Selecione uma imagem da galeria", Toast.LENGTH_SHORT).show();
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
            imagemPerfilPrincipal.setImageBitmap(salvarImagemAddServico);
            botaoAcessarGaleriaPerfilPrincipal.setText("Alterar foto");//Alterar Texto do botão

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



    public void salvarEdicaoPerfilPrincipal(View view){
        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }

    public void cancelarEdicaoPerfilPrincipal(View view){
        Toast.makeText(this, "Cancelar Edição", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent);
    }
}

