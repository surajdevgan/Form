package com.example.suraj.form;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.suraj.form.OfflineMode.AllUsers;
import com.example.suraj.form.OfflineMode.Util;
import com.example.suraj.form.OnlineMode.AllUsersServer;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ename, eage;
    Bean bean;
    ContentResolver resolver;

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;


    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bean = new Bean();
        ename = (EditText) findViewById(R.id.name);
        eage = (EditText) findViewById(R.id.age);
        resolver = getContentResolver();
        requestQueue = Volley.newRequestQueue(this);

    }
    boolean isNetworkConnected()
    {
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }

    public void btn(View view) {

        bean.setName(ename.getText().toString().trim());
        bean.setAge(eage.getText().toString().trim());

        if (isNetworkConnected())
        {
            insertIntoCloud();

        }
        else {
            insertUser();

        }
    }

    void insertUser() {
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, bean.getName());
        values.put(Util.COL_AGE, bean.getAge());

        Uri uri = resolver.insert(Util.USER_URI, values); // this insert will call insert function of BeanCOntentProvider
        Toast.makeText(this, bean.getName() + "registered successfully with id" + uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
    }

    void insertIntoCloud() {
        StringRequest request = new StringRequest(Request.Method.POST, com.example.suraj.form.OnlineMode.Util.INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Response", Toast.LENGTH_LONG).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("fname", bean.getName());
                map.put("fage", bean.getAge());
                return map;
            }

        };
        requestQueue.add(request);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.offline)
        {
            Intent i = new Intent(this, AllUsers.class);
            startActivity(i);

        }
        if (id==R.id.online)
        {
            Intent i = new Intent(this, AllUsersServer.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

