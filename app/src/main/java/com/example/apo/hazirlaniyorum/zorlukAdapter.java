package com.example.apo.hazirlaniyorum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apo on 05.04.2017.
 */

public class zorlukAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> list_parent;
    private HashMap<String, ArrayList<String>> list_child;
    private Context context;
    private TextView zorluk;
    private TextView hedef;
    private TextView derece;
    private CheckBox txt_child;
    private LayoutInflater inflater;

    public zorlukAdapter(Context context, ArrayList<String> list_parent, HashMap<String, ArrayList<String>> list_child)
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
          //  String title_name = (String)getGroup(groupPosition);

            if(view == null)
            {
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.zorlukparent,null);
            }
            zorluk=(TextView)view.findViewById(R.id.zorlukView);
            zorluk.setText("Hedef Zorlukların Seçimi:");
            hedef=(TextView)view.findViewById(R.id.hedefView);
            hedef.setText("              Hedef-1      Hedef-2         Hedef-3");
            derece=(TextView)view.findViewById(R.id.dereceView);
            derece.setText("                  50               100                  150");
        }
        catch (Exception ex)
        {
            int durtion= Toast.LENGTH_LONG;
            Toast toast= Toast.makeText(context,ex.getMessage()+"parent",durtion);
            toast.show();
        }


        return view;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View view, ViewGroup parent) {
        try {


            // kaçıncı pozisyonda ise başlığımızın elemanı onun ismini alarak string e atıyoruz
            String derece = (String) getChild(groupPosition, childPosition);

            if (view == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.zorlukchild, null);
                // fonksiyon adından da anlaşılacağı gibi parent a bağlı elemanlarının layoutunu inflate ediyoruz böylece o görüntüye ulaşmış oluyoruz
            }

            // listview_child ulaştığımıza göre içindeki bileşeni de kullanabiliyoruz daha sonradan view olarak return ediyoruz
            txt_child = (CheckBox) view.findViewById(R.id.zorlukCheck);
            txt_child.setText(derece);


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
