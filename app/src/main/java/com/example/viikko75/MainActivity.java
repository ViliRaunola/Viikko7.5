package com.example.viikko75;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText syoteid;
    EditText nimit;
    EditText nimil;
    Context context = null;
    TextView takaisin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        syoteid = (EditText)findViewById(R.id.syoteid);
        nimit = (EditText)findViewById(R.id.nimiT);
        nimil = (EditText)findViewById(R.id.nimiL);
        takaisin = (TextView)findViewById(R.id.syoteid);

        System.out.println(context.getFilesDir());

    }

    public void tallenaTiedostoon(View v){
        String tiedosto = nimit.getText().toString();
        try{
            OutputStreamWriter kirjoittaja = new OutputStreamWriter(context.openFileOutput(tiedosto, Context.MODE_APPEND));
            String haettuTeksti = syoteid.getText().toString();
            kirjoittaja.write(haettuTeksti);
            kirjoittaja.close();
        }catch (IOException e){
            Log.e("IOException", "Virhe");
        } finally {
            System.out.println("Kirjoitettu");
        }
    }

    public void lueTiedostosta(View v){
        try{
            String tiedosto = nimil.getText().toString();
            InputStream arvo = context.openFileInput(tiedosto);
            BufferedReader lukija = new BufferedReader(new InputStreamReader(arvo));
            String rivi = "";

            while((rivi=lukija.readLine()) != null){
                takaisin.append(rivi + "\n");
            }

        }catch (IOException e){
            Log.e("IOException e", "virhe prkl");
        }finally {
            System.out.println("Luettu");
        }

    }

}

