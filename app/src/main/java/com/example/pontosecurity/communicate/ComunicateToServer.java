package com.example.pontosecurity.communicate;


import com.example.pontosecurity.callback.IFutureCallback;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Year;

public class ComunicateToServer extends Thread {
    private IFutureCallback callback;

    public void onFutureCallback(IFutureCallback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {


        HttpURLConnection connection = null;
        try {
            URL url = new URL("");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("container", "");

            String jsonInputString = body.toString();
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.close();
            }

            InputStream in = new BufferedInputStream(connection.getInputStream());
            String resultado = IOUtils.toString(in, "UTF-8");

            JSONObject result = new JSONObject(resultado);

            JSONArray responseFiles = new JSONArray(result.get("blobs").toString());
            for (int i = 0; i < responseFiles.length(); i++) {

                JSONObject file = new JSONObject(responseFiles.get(i).toString());
                String nomeArquivo = file.getString("name");
                String caminhoUrl = file.getString("url");
                //String caminhoUrl = file.getString("size");

            }

            JSONObject response = new JSONObject(resultado);

            int statusCode = connection.getResponseCode();

            System.out.println("Response code S3 Files Origins: " + statusCode);

            in.close();
            callback.onSuccess();
        } catch (IOException | JSONException e) {

            callback.onError(e);
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
