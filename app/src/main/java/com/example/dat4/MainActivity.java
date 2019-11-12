package com.example.dat4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private TextView result;
    //gjkgbkg
    private RequestQueue rqueue;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //result = findViewById(R.id.view_parsed);
        lvItems = (ListView) findViewById(R.id.listitems);
        //Button button = findViewById(R.id.button);
        rqueue = Volley.newRequestQueue(this);
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        parseJSON();
        //    }
        //});
    }

    //THIS IS A MESSAGE TO TEST VERSION CONTROL

    public void parseJSON(View view) {
        String url = "http://anontech.info/courses/cse491/employees.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            items = new ArrayList<String>();
                            itemsAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, items);
                            if(lvItems!=null) {
                                lvItems.setAdapter(itemsAdapter);
                            }

                            for(int i=0;i<response.length();i++){
                            JSONObject jsonem = response.getJSONObject(i);
                            String name = jsonem.getString("name");
                            JSONObject loc = jsonem.getJSONObject("location");
                            String lat = loc.getString("latitude");
                            String lon = loc.getString("longitude");
                            itemsAdapter.add(name);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rqueue.add(request);

    }
}
