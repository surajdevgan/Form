package com.example.suraj.form;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suraj on 02-10-2017.
 */

public class UserAdapter extends ArrayAdapter<Bean>

{
    Context context;
    int resource;
    ArrayList<Bean> UserList;
    public UserAdapter(Context context,  int resource, ArrayList<Bean> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        UserList = objects;;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView txtName = (TextView) view.findViewById(R.id.namel);
        TextView txtAge = (TextView) view.findViewById(R.id.agel);

        Bean bean = UserList.get(position);
        txtName.setText(bean.getName());
        txtAge.setText(bean.getAge());


        return view;
    }
}
