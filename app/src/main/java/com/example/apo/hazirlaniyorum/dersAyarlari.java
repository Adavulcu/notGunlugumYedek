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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apo on 05.04.2017.
 */

public class dersAyarlari extends AppCompatActivity {
    private ArrayList<String> title;
    private zorlukExpadaper expand_adapter;
    private HashMap<String, ArrayList<String>> hedef;
    final ArrayList<String> derece = new ArrayList<String>();
    private ExpandableListView expandlist_viewYGS;


    private ArrayList<dersEkle> dersTitle;
    private dersAyarlariExpadapter dersAyarexpand_adapter;
    private HashMap<dersEkle,ogrtEkle> ogrt;
    final ArrayList<ogrtEkle> ogrtKayit= new ArrayList<ogrtEkle>();
    final ogrtEkle ogrtKayit1= new ogrtEkle("FİZİK"," hulya@gmail.com","Hülya ÜKTE",0,0);
    final ogrtEkle ogrtKayit2= new ogrtEkle("KİMYA"," mustafa@gmail.com","Mustafa DUt",1,2);
    private ExpandableListView dersAyarexpandlist;


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


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dersayarlari);
        try {

            expandlist_viewYGS = (ExpandableListView) findViewById(R.id.zorlukExp);
            Hazırla(); // expandablelistview içeriğini hazırlamak için
            // Adapter sınıfımızı oluşturmak için başlıklardan oluşan listimizi ve onlara bağlı olan elemanlarımızı oluşturmak için HaspMap türünü yolluyoruz
            expand_adapter = new zorlukExpadaper(getApplicationContext(), title, hedef);
            expandlist_viewYGS.setAdapter(expand_adapter);  // oluşturduğumuz adapter sınıfını set ediyoruz


            dersAyarexpandlist=(ExpandableListView) findViewById(R.id.ogrtKayitexp);
            hazitlaDersTitle();
            dersAyarexpand_adapter=new dersAyarlariExpadapter(getApplicationContext(),dersTitle,ogrt);

            dersAyarexpandlist.setAdapter(dersAyarexpand_adapter);

        }
        catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " dersAyarları", durtion);
            toast.show();
        }
    }

    private void hazitlaDersTitle() {

        try
        {


            dersTitle=new ArrayList<dersEkle>();
            ogrt=new HashMap<dersEkle,ogrtEkle>();
            dersTitle.add(new dersEkle("FİZİK",0));
            dersTitle.add(new dersEkle("KİMYA",2));

           // ogrtKayit.add(new ogrtEkle("FİZİK"," hulya@gmail.com","Hülya ÜKTE",0,0));
           // ogrtKayit.add(new ogrtEkle("KİMYA"," mustafa@gmail.com","Mustafa DUt",1,2));

            ogrt.put(dersTitle.get(0),ogrtKayit1);
            ogrt.put(dersTitle.get(1),ogrtKayit2);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage()+"zorluk", durtion);
            toast.show();
        }
    }

    private void Hazırla() {


        // başlıklara bağlı elemenları tutmak için oluşturduk
        try {
            title = new ArrayList<String>();  // başlıklarımızı listemelek için oluşturduk
            hedef = new HashMap<String, ArrayList<String>>();
            title.add("DERS SEÇİMİ");


            derece.add("                  50               100                  150");
            derece.add("                  75               150                  225");
            derece.add("                  10               200                  300");


           hedef.put(title.get(0), derece);

        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage()+"zorluk", durtion);
            toast.show();
        }
    }
    private class dersAyarlariExpadapter extends BaseExpandableListAdapter{
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

        public dersAyarlariExpadapter(Context context, ArrayList<dersEkle> list_parent, HashMap<dersEkle,ogrtEkle> list_child)
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
    private class zorlukExpadaper extends BaseExpandableListAdapter{
        private ArrayList<String> list_parent;
        private HashMap<String, ArrayList<String>> list_child;
        private Context context;
        private TextView zorluk;
        private TextView hedef;
        private TextView derece;
        private CheckBox txt_child;
        private LayoutInflater inflater;

        public zorlukExpadaper(Context context, ArrayList<String> list_parent, HashMap<String, ArrayList<String>> list_child)
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
}