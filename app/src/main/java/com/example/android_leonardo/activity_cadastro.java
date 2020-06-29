package com.example.android_leonardo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class activity_cadastro extends AppCompatActivity {

    private EditText edtNomeTxt, edtFoneTxt, edtEmailTxt, edtDetalhesTxt, edtCepTxt, edtEnderecoTxt, edtCidadeUfTxt;
    private RadioButton rbMasc, rbFem;
    private CheckBox chkFisicaTxt, chkAuditivaTxt, chkVisualTxt, chkMentalTxt;
    private Button btnCadastrar;

    private static String URL_REGIST = "http://192.168.0.102/android/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNomeTxt = (EditText) findViewById(R.id.edtNome);
        edtFoneTxt = (EditText) findViewById(R.id.edtFone);
        edtEmailTxt = (EditText) findViewById(R.id.edtEmail);
        rbMasc = (RadioButton) findViewById(R.id.rdbMasc);
        rbFem = (RadioButton) findViewById(R.id.rdbFem);
        chkFisicaTxt = (CheckBox) findViewById(R.id.chkFisica);
        chkAuditivaTxt = (CheckBox) findViewById(R.id.chkAuditiva);
        chkVisualTxt = (CheckBox) findViewById(R.id.chkVisual);
        chkMentalTxt = (CheckBox) findViewById(R.id.chkMental);
        edtDetalhesTxt = (EditText) findViewById(R.id.edtDetalhes);
        edtCepTxt = (EditText) findViewById(R.id.edtCep);
        edtCidadeUfTxt = (EditText) findViewById(R.id.edtCidadeUf);
        edtEnderecoTxt = (EditText) findViewById(R.id.edtEndereco);
    }

    public void Buscar(View view) {
        edtCepTxt = (EditText) findViewById(R.id.edtCep);
        edtCidadeUfTxt = (EditText) findViewById(R.id.edtCidadeUf);
        edtEnderecoTxt = (EditText) findViewById(R.id.edtEndereco);

        if (edtCepTxt.getText().toString().length() > 0 &&
            !edtCepTxt.getText().toString().equals("") &&
            edtCepTxt.getText().toString().length() == 8) {
            HTTPWebServiceLeo serviceLeo = new HTTPWebServiceLeo(edtCepTxt.getText().toString());
            try {
                CEP retorno = serviceLeo.execute().get();
                edtEnderecoTxt.setText(retorno.getLogradouro());
                edtCidadeUfTxt.setText(retorno.getLocalidade() + "/" + retorno.getUf());
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
            }
        }
    }

    public void Cadastrar(View view) {
        if (!edtNomeTxt.getText().toString().equals("")){
            Regist();
        } else {
            Toast.makeText(activity_cadastro.this, "Informe todos os campos da tela", Toast.LENGTH_LONG).show();
        }
    }

    private void Regist(){
        final String var_nome = this.edtNomeTxt.getText().toString();
        final String var_fone = this.edtFoneTxt.getText().toString();
        final String var_email = this.edtEmailTxt.getText().toString();
        final String var_detalhes = this.edtDetalhesTxt.getText().toString();
        final String var_cep = this.edtCepTxt.getText().toString();
        final String var_cidadeUf = this.edtCidadeUfTxt.getText().toString();
        final String var_endereco = this.edtEnderecoTxt.getText().toString();

        final String var_sexo;
        if (rbMasc.isChecked()){
            var_sexo = "M";
        } else {
            var_sexo = "F";
        }

        final String var_fisica;
        if (chkFisicaTxt.isChecked()){
            var_fisica = "S";
        } else {
            var_fisica = "N";
        }

        final String var_auditiva;
        if (chkAuditivaTxt.isChecked()){
            var_auditiva = "S";
        } else {
            var_auditiva = "N";
        }

        final String var_visual;
        if (chkVisualTxt.isChecked()){
            var_visual = "S";
        } else {
            var_visual = "N";
        }

        final String var_mental;
        if (chkMentalTxt.isChecked()){
            var_mental = "S";
        } else {
            var_mental = "N";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(activity_cadastro.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    Toast.makeText(activity_cadastro.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_cadastro.this, "VOLEY ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            public Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put("nome",var_nome);
                params.put("fone",var_fone);
                params.put("email",var_email);
                params.put("detalhes",var_detalhes);
                params.put("cep",var_cep);
                params.put("cidadeUf",var_cidadeUf);
                params.put("endereco",var_endereco );
                params.put("sexo",var_sexo);
                params.put("fisica",var_fisica);
                params.put("auditiva",var_auditiva);
                params.put("visual",var_visual);
                params.put("mental",var_mental);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}