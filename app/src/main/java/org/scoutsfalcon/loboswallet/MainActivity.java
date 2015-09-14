package org.scoutsfalcon.loboswallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.R.layout;
import android.widget.TextView;
import android.widget.Toast;

import org.scoutsfalcon.loboswallet.utils.CustomAdapter;
import org.scoutsfalcon.loboswallet.utils.LobosEstacion;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private LobosEstacion lobos = LobosEstacion.getInstance();
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("WalletPreferences", Context.MODE_PRIVATE);
        String estation = pref.getString("estacion", getResources().getString(R.string.msg_nostation));
        Integer maximo = pref.getInt("maximo", 0);

        ImageButton btnAgregar = (ImageButton)findViewById(R.id.button_add);
        ListView lstView = (ListView)findViewById(R.id.lstView);

        TextView lblStation = (TextView)findViewById(R.id.txt_station);
        lblStation.setText(estation);
        lobos.setMaximo(maximo);

        adapter = new CustomAdapter(getApplicationContext(), lobos);

        btnAgregar.setOnClickListener(new ClickButtonListener());
        lstView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent config = new Intent(getApplicationContext(), ConfigurationActivity.class);
            startActivity(config);
            return true;
        } else if (id == R.id.action_buy) {
            ArrayList<String> array = lobos.getIds();
            for (String ids : array) {
                Log.i("HOO", ids);
            }
            return true;
        } else if(id == R.id.action_pay) {
            ArrayList<String> array = lobos.getIds();
            for (String ids : array) {
                Log.i("HOO", ids);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ClickButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (lobos.isFull()) {
                Toast.makeText(getApplicationContext(), R.string.msg_full, Toast.LENGTH_LONG).show();
                return;
            }
            Intent action = new Intent(getApplicationContext(), OperationActivity.class);
            startActivity(action);
        }
    }

}
