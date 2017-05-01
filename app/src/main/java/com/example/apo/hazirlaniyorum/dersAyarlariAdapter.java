package com.example.apo.hazirlaniyorum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apo on 05.04.2017.
 */

public class dersAyarlariAdapter extends BaseExpandableListAdapter {
    private ArrayList<dersEkle> list_parent;
    private HashMap<dersEkle, ogrtEkle> list_child;
    private Context context;
    private TextView dersAdParent;
    private TextView postaParent;
    private TextView dersAdChild;
    private TextView ogrtAD;
    private TextView postaChild;
    private EditText adSoyadtext;
    private EditText postaText;
    private Button gonder;
    private LayoutInflater inflater;

    public dersAyarlariAdapter(Context context, ArrayList<dersEkle> list_parent, HashMap<dersEkle,ogrtEkle> list_child)
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
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return list_parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return list_child.get(list_parent.get(groupPosition));

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
            dersEkle ders=(dersEkle) getGroup(groupPosition);

            if(view == null)
            {
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.ogretmenkayitparent,null);
            }

            dersAdParent=(TextView) view.findViewById(R.id.ogrtdersAdViewParent);
            dersAdParent.setText(ders.getDersAd());
            postaParent=(TextView) view.findViewById(R.id.ogrtPostaViewParent);
            postaParent.setText("abc@gmail.com");

        }
        catch (Exception ex)
        {
            int durtion= Toast.LENGTH_LONG;
            Toast toast= Toast.makeText(context,ex.getMessage()+"dersayar parent",durtion);
            toast.show();
        }


        return view;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View view, ViewGroup parent) {
        try {


            // kaçıncı pozisyonda ise başlığımızın elemanı onun ismini alarak string e atıyoruz

            ogrtEkle ogrt=(ogrtEkle) getChild(groupPosition,childPosition);

            if (view == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.ogretmenkayitchild, null);
                // fonksiyon adından da anlaşılacağı gibi parent a bağlı elemanlarının layoutunu inflate ediyoruz böylece o görüntüye ulaşmış oluyoruz
            }

            // listview_child ulaştığımıza göre içindeki bileşeni de kullanabiliyoruz daha sonradan view olarak return ediyoruz



          //  public EditText postaText;
          //  public Button gonder;
            dersAdChild=(TextView) view.findViewById(R.id.ogrtDersAdViewChild);
            dersAdChild.setText(ogrt.getDersAd());
            ogrtAD =(TextView ) view.findViewById(R.id.ogrtAdSoyadView);
            ogrtAD.setText("Ad Soyad : ");
            adSoyadtext=(EditText) view.findViewById(R.id.ogrtAdSoyadText);
            adSoyadtext.setText(ogrt.getAdSoyad());

            postaChild=(TextView) view.findViewById(R.id.ogrtPostaViewChild);
            postaChild.setText("E-posta");
            postaText=(EditText) view.findViewById(R.id.ogrtPostaTExt);
            postaText.setText(ogrt.getPosta());

            gonder=(Button) view.findViewById(R.id.gonderbtn);
          //  gonder.setText("DEĞİŞİKLİKLERİ KAYDET");


        }
        catch (Exception ex)
        {
            int durtion= Toast.LENGTH_LONG;
            Toast toast= Toast.makeText(context,ex.getMessage()+" dersAyar child",durtion);
            toast.show();
        }
        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
