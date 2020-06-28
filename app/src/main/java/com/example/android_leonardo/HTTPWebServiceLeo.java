package com.example.android_leonardo;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HTTPWebServiceLeo extends AsyncTask<Void, Void, CEP> {
        private final String cep;

        public HTTPWebServiceLeo(String cep) {
            this.cep = cep;
        }


        @Override
        protected CEP doInBackground(Void... voids){
            StringBuilder resposta = new StringBuilder();
            try {
                URL minhaUrl = new URL("https://viacep.com.br/ws/" + this.cep + "/json/");
                HttpURLConnection connection = (HttpURLConnection) minhaUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(minhaUrl.openStream());
                while (scanner.hasNext()){
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException | ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Gson().fromJson(resposta.toString(), CEP.class);
        }
    }
