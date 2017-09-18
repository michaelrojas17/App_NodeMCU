package michaelrojas.app_nodemcu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button btnEscanear, btnGuardar;
    TextView etSSID, etPass;
    static String IP_SERVER = "http://192.168.4.1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnEscanear = (Button)findViewById(R.id.btnEscanear);
        etPass = (EditText)findViewById(R.id.etPass);
        etSSID = (EditText)findViewById(R.id.etSSID);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String parametroSSID = etSSID.getText().toString();
                String parametroPasswd = etPass.getText().toString();

                ConnectivityManager conMngr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = conMngr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){

                    String url = IP_SERVER+"guardar_conf?ssid="+ parametroSSID +"&pass="+parametroPasswd;


                    new SolicitaDatos().execute(url);
                }else{
                    Toast.makeText(MainActivity.this, "Ninguna conexion detectada", Toast.LENGTH_LONG).show();

                }

            }
        });



        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ConnectivityManager conMngr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = conMngr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){

                    String url = IP_SERVER+"escanear";


                    new SolicitaDatos().execute(url);
                }else{
                    Toast.makeText(MainActivity.this, "Ninguna conexión detectada", Toast.LENGTH_LONG).show();

                }

            }
        });



    }

    private class SolicitaDatos extends AsyncTask<String, Void,String >{

        @Override
        protected String doInBackground(String... url) {
            return Conexion.getData(url[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {
             if(resultado != null){


                 if(resultado.contains("Configuracion Guardada")){
                     Toast.makeText(MainActivity.this, "Configuracion Guardada...", Toast.LENGTH_LONG).show();
                 }

             }else{
                 Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
             }
        }



    }
}
