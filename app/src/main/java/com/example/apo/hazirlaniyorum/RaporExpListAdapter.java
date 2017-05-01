package com.example.apo.hazirlaniyorum;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apo on 05.04.2017.
 */

public class RaporExpListAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> list_parent;
    private HashMap<String, ArrayList<dersEkle>> list_child;
    private Context context;
    private TextView derssecimi;
    private CheckBox txt_child;
    private Button goruntule;
    private LayoutInflater inflater;

    public RaporExpListAdapter(Context context, ArrayList<String> list_parent, HashMap<String, ArrayList<dersEkle>> list_child)
    {
        this.context = context;
        this.list_parent = list_parent;
       this.list_child=list_child;
    }
    @Override
    public int getGroupCount() {
        return list_parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list_child.get(list_parent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return list_parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return list_child.get(list_parent.get(groupPosition)).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }


    @Override
    public boolean hasStableIds() {

        return true;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View view, ViewGroup parent) {
        try {
            String title_name = (String)getGroup(groupPosition);

            if(view == null)
            {
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.derssecimi,null);
            }
            derssecimi=(TextView)view.findViewById(R.id.derssecimiView);
            derssecimi.setText(title_name);
            goruntule=(Button ) view.findViewById(R.id.goruntuleBtn);
           // goruntule.setText("GÖRÜNTÜLE");
        }
       catch (Exception ex)
       {
           int durtion= Toast.LENGTH_LONG;
           Toast toast= Toast.makeText(context,ex.getMessage()+" rapor parent",durtion);
           toast.show();
       }


        return view;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View view, ViewGroup parent) {
        try {


            // kaçıncı pozisyonda ise başlığımızın elemanı onun ismini alarak string e atıyoruz
           // String ders = (String) getChild(groupPosition, childPosition);
            dersEkle ders=(dersEkle) getChild(groupPosition, childPosition);
            if (view == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.rapordersler, null);
                // fonksiyon adından da anlaşılacağı gibi parent a bağlı elemanlarının layoutunu inflate ediyoruz böylece o görüntüye ulaşmış oluyoruz
            }

            // listview_child ulaştığımıza göre içindeki bileşeni de kullanabiliyoruz daha sonradan view olarak return ediyoruz
            txt_child = (CheckBox) view.findViewById(R.id.raporDersCheck);
            txt_child.setText(ders.getDersAd());


        }
        catch (Exception ex)
        {
            int durtion= Toast.LENGTH_LONG;
            Toast toast= Toast.makeText(context,ex.getMessage()+"child",durtion);
            toast.show();
        }
        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
