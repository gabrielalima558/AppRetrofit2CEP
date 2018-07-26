package com.example.gabriela.appcep;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edit_digita_cep;
    TextView txt_cep, txt_logradouro, txt_complemento, txt_bairro, txt_localidade,
            txt_uf, txt_unidade, txt_ibge, txt_gia;
    String cep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_digita_cep = (EditText)findViewById(R.id.edit_digita_cep);
        txt_cep = (TextView)findViewById(R.id.txt_cep);
        txt_logradouro = (TextView)findViewById(R.id.txt_logradouro);
        txt_complemento = (TextView)findViewById(R.id.txt_complemento);
        txt_bairro = (TextView)findViewById(R.id.txt_bairro);
        txt_localidade = (TextView)findViewById(R.id.txt_localidade);
        txt_uf = (TextView)findViewById(R.id.txt_uf);
        txt_unidade = (TextView)findViewById(R.id.txt_unidade);
        txt_ibge = (TextView)findViewById(R.id.txt_ibge);
        txt_gia = (TextView)findViewById(R.id.txt_gia);
    }

    public void btnConsultar(View view) {

        cep = edit_digita_cep.getText().toString();

        Log.v("CEP: ", cep);

        IRetrofitCEP userCep = IRetrofitCEP.retrofit.create(IRetrofitCEP.class);
        final Call<Endereco> call = userCep.getCep(cep);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                int code = response.code();
                if(code == 200){
                    Log.v("CEP: ", cep);
                    Endereco endereco = response.body();
                    Toast.makeText(getBaseContext(),"CEP do usuário: " + endereco.cep,
                            Toast.LENGTH_SHORT).show();
                    txt_cep.setText("CEP:" + endereco.cep);
                    txt_logradouro.setText("Logradouro: " + endereco.logradouro);
                    txt_complemento.setText("Complemento" + endereco.complemento);
                    txt_bairro.setText("Bairro: " + endereco.bairro);
                    txt_uf.setText("Estado: " + endereco.uf);
                    txt_localidade.setText("Localidade: " + endereco.localidade);
                    txt_unidade.setText("Unidade: " + endereco.unidade);
                    txt_ibge.setText("IBGE: " + endereco.ibge);
                    txt_gia.setText("GIA: " + endereco.gia);

                }else{
                    Toast.makeText(getBaseContext(), "Deu Ruim" + String.valueOf(code),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
