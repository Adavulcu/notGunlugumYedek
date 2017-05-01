package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.ExpandableListActivity;

/**
 * Created by apo on 05.04.2017.
 */

public class rapor extends AppCompatActivity  {
    TabHost tabHost;
    //////////////////////////////////////////////////YGS BÖLÜMÜ
    private ArrayList<String> titleYGS;
    private raporExpadapter expand_adapterYGS;
    private HashMap<String, ArrayList<dersEkle>> derslerListYGS;
    final ArrayList<dersEkle> derslerYGS=new ArrayList<dersEkle>();
    private ExpandableListView expandlist_viewYGS;
    /////////////////////////////////////////LYS bölümü
    private ArrayList<String> titleLYS;
    private raporExpadapter expand_adapterLYS;
    private HashMap<String, ArrayList<dersEkle>> derslerListLYS;
    final ArrayList<dersEkle> derslerLYS=new ArrayList<dersEkle>();
    private ExpandableListView expandlist_viewLYS;

    public void onBackPressed() {
        try {
            Intent i=new Intent(".ANASAYFA");
            startActivity(i);
            super.onBackPressed();
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " onBack", durtion);
            toast.show();
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rapor);
        try {
        // tabHost = getTabHost();
        tabHost=(TabHost)findViewById(R.id.raporTab);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Tab One");
        spec.setContent(R.id.YGSrapor);
        spec.setIndicator("YGS");
        tabHost.addTab(spec);

        //Tab 2
        spec = tabHost.newTabSpec("Tab Two");
        spec.setContent(R.id.LYSrapor);
        spec.setIndicator("LYS");
        tabHost.addTab(spec);
        /////////////////////////////////YGS bülümü
        expandlist_viewYGS = (ExpandableListView)findViewById(R.id.expYGS);
        HazırlaYGS(); // expandablelistview içeriğini hazırlamak için

        // Adapter sınıfımızı oluşturmak için başlıklardan oluşan listimizi ve onlara bağlı olan elemanlarımızı oluşturmak için HaspMap türünü yolluyoruz
        expand_adapterYGS = new raporExpadapter(getApplicationContext(), titleYGS, derslerListYGS);
        expandlist_viewYGS.setAdapter(expand_adapterYGS);  // oluşturduğumuz adapter sınıfını set ediyoruz
        expandlist_viewYGS.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //get the group header

                return false;
            }
        });

        //////////////////////////////////LYS bölümü
        expandlist_viewLYS = (ExpandableListView)findViewById(R.id.expLYS);
        HazırlaLYS(); // expandablelistview içeriğini hazırlamak için

        // Adapter sınıfımızı oluşturmak için başlıklardan oluşan listimizi ve onlara bağlı olan elemanlarımızı oluşturmak için HaspMap türünü yolluyoruz
        expand_adapterLYS = new raporExpadapter(getApplicationContext(), titleLYS, derslerListLYS);
        expandlist_viewLYS.setAdapter(expand_adapterLYS);  // oluşturduğumuz adapter sınıfını set ediyoruz
    } catch (Exception ex) {
        int durtion = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, ex.getMessage() + " rapor", durtion);
        toast.show();
    }

    }
    private void HazırlaYGS() {


        // başlıklara bağlı elemenları tutmak için oluşturduk
        try {
            titleYGS = new ArrayList<String>();  // başlıklarımızı listemelek için oluşturduk
            derslerListYGS = new HashMap<String, ArrayList<dersEkle>>();
            titleYGS.add("DERS SEÇİMİ");


            derslerYGS.add(new dersEkle("FİZİK-I",1));
            derslerYGS.add(new dersEkle("KİMYA-I",2));
            derslerYGS.add(new dersEkle("MATEMATİK-I",3));
            derslerYGS.add(new dersEkle("TÜRKCE-I",4));

            derslerListYGS.put(titleYGS.get(0), derslerYGS);

        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage()+"raporYGs", durtion);
            toast.show();
        }
    }
    private void HazırlaLYS() {


        // başlıklara bağlı elemenları tutmak için oluşturduk
        try {
            titleLYS = new ArrayList<String>();  // başlıklarımızı listemelek için oluşturduk
            derslerListLYS=new  HashMap<String, ArrayList<dersEkle>>();
            titleLYS.add("DERS SEÇİMİ");


            derslerLYS.add(new dersEkle("FİZİK-II",5));
            derslerLYS.add(new dersEkle("KİMYA-II",6));
            derslerLYS.add(new dersEkle("MATEMATİK-II",7));
           derslerLYS.add(new dersEkle("TÜRKÇE",8));

           derslerListLYS.put(titleLYS.get(0), derslerLYS);


        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage()+"raporLys", durtion);
            toast.show();
        }
    }

     private class raporExpadapter extends BaseExpandableListAdapter{
         private ArrayList<String> list_parent;
         private HashMap<String, ArrayList<dersEkle>> list_child;
         private Context context;
         private TextView derssecimi;
         private CheckBox txt_child;
         private Button goruntule;
         private LayoutInflater inflater;

         public raporExpadapter(Context context, ArrayList<String> list_parent, HashMap<String, ArrayList<dersEkle>> list_child)
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

}
