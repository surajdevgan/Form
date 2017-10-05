package com.example.suraj.form.OnlineMode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.suraj.form.Bean;
import com.example.suraj.form.R;
import com.example.suraj.form.UserAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllUsersServer extends AppCompatActivity {

    RequestQueue requestQueue;
    ArrayList<Bean> UserList;
    StringRequest stringRequest;
    ListView ls;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users_server);
        requestQueue = Volley.newRequestQueue(this);
        ls = (ListView) findViewById(R.id.ListView);
        RetrieveFromServer();
    }

    void RetrieveFromServer()
    {
        UserList = new ArrayList<>();
stringRequest = new StringRequest(Request.Method.GET, Util.RETRIEVE, new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {

        try{
            JSONObject jsonObject = new JSONObject(response);
            int success = jsonObject.getInt("success");
            String message = jsonObject.getString("message");

            if(success==1)
            {
                JSONArray jsonArray = jsonObject.getJSONArray("students");
                int id = 0;
                String n = "", a = "";


                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jobj = jsonArray.getJSONObject(i);
                    id = jobj.getInt("ID");
                    n = jobj.getString("NAME");
                    a = jobj.getString("AGE");

                    Bean bean = new Bean(id,n,a);
                    UserList.add(bean);
                }

                userAdapter = new UserAdapter(AllUsersServer.this,R.layout.list_item,UserList);
                ls.setAdapter(userAdapter);


            }
            else {

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(AllUsersServer.this, "Some Exception", Toast.LENGTH_SHORT).show();
        }

    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(AllUsersServer.this, "Some Volley Error", Toast.LENGTH_SHORT).show();

    }

});
requestQueue.add(stringRequest);
    }
}
