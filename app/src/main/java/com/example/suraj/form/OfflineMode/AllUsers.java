package com.example.suraj.form.OfflineMode;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.suraj.form.Bean;
import com.example.suraj.form.MainActivity;
import com.example.suraj.form.R;
import com.example.suraj.form.UserAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllUsers extends AppCompatActivity {

ListView lv;
    ContentResolver resolver;
    ArrayList<Bean> UserList;
    RequestQueue requestQueue;
    UserAdapter adapter;
    Bean b;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        lv = (ListView) findViewById(R.id.listView);
        resolver = getContentResolver();
        requestQueue = Volley.newRequestQueue(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("upoading...");
        progressDialog.setCancelable(false);
        Retrieve();
    }

    void Retrieve()
    {
        UserList = new ArrayList<>();
        String [] projection = {Util.COL_NAME,Util.COL_AGE};
       Cursor cursor =  resolver.query(Util.USER_URI,projection,null,null,null); // this query will call query method of BeanContentProvider
if(cursor!=null)
{
    String n="", a="" ;
    while (cursor.moveToNext())
    {
        n = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
        a = cursor.getString(cursor.getColumnIndex(Util.COL_AGE));

        b = new Bean(n,a);
        UserList.add(b);
    }
    adapter = new UserAdapter(this,R.layout.list_item,UserList);
    lv.setAdapter(adapter);
}
    }

    void insertIntoCloud() {
        StringRequest request = new StringRequest(Request.Method.POST, com.example.suraj.form.OnlineMode.Util.INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AllUsers.this, "Response", Toast.LENGTH_LONG).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AllUsers.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                for( Bean b:UserList) {
                    map.put("fname", b.getName());
                    map.put("fage", b.getAge());
                }
                return map;
            }

        };
        requestQueue.add(request);


    }


    public void Send(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        insertIntoCloud();
    }
}
