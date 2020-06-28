package com.example.android_leonardo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class activity_cadastro extends AppCompatActivity {

    private EditText edtCepTxt, edtEnderecoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void Buscar(View view) {
        edtCepTxt = (EditText) findViewById(R.id.edtCep);
        edtEnderecoTxt = (EditText) findViewById(R.id.email2);
        if (edtCepTxt.getText().toString().length() > 0 &&
            !edtCepTxt.getText().toString().equals("") &&
            edtCepTxt.getText().toString().length() == 8) {
//            edtEnderecoTxt.setText(edtCepTxt.getText().toString());
            HTTPWebServiceLeo serviceLeo = new HTTPWebServiceLeo(edtCepTxt.getText().toString());
            try {
                CEP retorno = serviceLeo.execute().get();
                edtEnderecoTxt.setText(retorno.getLocalidade());
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
            }
        }
    }
}