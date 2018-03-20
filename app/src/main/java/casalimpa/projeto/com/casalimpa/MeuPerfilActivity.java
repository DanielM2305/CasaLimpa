package casalimpa.projeto.com.casalimpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MeuPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);
    }


    public void telaDashboard(View view){
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);

    }

    public void prestadorServicos(View view){
        Intent intent2 = new Intent(getApplicationContext(), MeuPerfilActivity.class);
        startActivity(intent2);
    }
}
