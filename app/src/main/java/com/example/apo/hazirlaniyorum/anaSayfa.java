package com.example.apo.hazirlaniyorum;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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
 * Created by Apo on 8.03.2017.
 */

public class anaSayfa extends AppCompatActivity {

    TabHost tabHost;
    Context contex = this;
//////////////////////////////////////////////////YGS BÖLÜMÜ

    private ArrayList<dersEkle> dersListYgs;
    private anaSayfaExpadapter expand_adapterYGS;
    private HashMap<dersEkle, ArrayList<konuEkle>> konularListYGS;
    final ArrayList<konuEkle> konularFizikYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularKimyaYGS = new ArrayList<konuEkle>();
    private ExpandableListView expandlist_viewYGS;
    /////////////////////////////////////////LYS bölümü

    private ArrayList<dersEkle> dersListLys;
    private anaSayfaExpadapter expand_adapterLYS;
    private HashMap<dersEkle, ArrayList<konuEkle>> konularListLYS;
    final public ArrayList<konuEkle> konularFizikLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularKimyaLYS = new ArrayList<konuEkle>();
    private ExpandableListView expandlist_viewLYS;

    ///////////////////////// menu bölümü
    public boolean onCreateOptionsMenu(android.view.Menu anaSayfa) {
        super.onCreateOptionsMenu(anaSayfa);
        MenuInflater myMenu = getMenuInflater();
        myMenu.inflate(R.menu.menu, anaSayfa);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            Intent i;
            switch (item.getItemId()) {
                case R.id.kayit:
                    i = new Intent(".KAYIT");
                    startActivity(i);
                    break;
                case R.id.tarih:
                    i = new Intent(".TARIHEKLE");
                    startActivity(i);
                    break;
                case R.id.veriYedekle:
                    i = new Intent(".VERIYEDEKLE");
                    startActivity(i);
                    break;
                case R.id.hata:
                    i = new Intent(".HATABILDIRIMI");
                    startActivity(i);
                    break;
                case R.id.rapor:
                    i = new Intent(".RAPOR");
                    startActivity(i);
                    break;
                case R.id.dersAyar:
                    i = new Intent(".DERSAYARLARI");
                    startActivity(i);
                    break;
                default:
                    return super.onOptionsItemSelected(item);

            }
            return true;
        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " anasayfa", durtion);
            toast.show();
        } finally {
            return true;
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anasayfa);
        try {


            // tabHost = getTabHost();
            tabHost = (TabHost) findViewById(R.id.anatab);
            tabHost.setup();

            TabHost.TabSpec spec = tabHost.newTabSpec("Tab One");
            spec.setContent(R.id.YGS);
            spec.setIndicator("YGS");
            tabHost.addTab(spec);

            //Tab 2
            spec = tabHost.newTabSpec("Tab Two");
            spec.setContent(R.id.LYS);
            spec.setIndicator("LYS");
            tabHost.addTab(spec);
            /////////////////////////////////YGS bülümü
            expandlist_viewYGS = (ExpandableListView) findViewById(R.id.exp_listYGS);
            HazırlaYGS(); // expandablelistview içeriğini hazırlamak için

            // Adapter sınıfımızı oluşturmak için başlıklardan oluşan listimizi ve onlara bağlı olan elemanlarımızı oluşturmak için HaspMap türünü yolluyoruz
            expand_adapterYGS = new anaSayfaExpadapter(getApplicationContext(), dersListYgs, konularListYGS);
            expandlist_viewYGS.setAdapter(expand_adapterYGS);  // oluşturduğumuz adapter sınıfını set ediyoruz

            //////////////////////////////////LYS bölümü
            expandlist_viewLYS = (ExpandableListView) findViewById(R.id.exp_listLYS);
            HazırlaLYS(); // expandablelistview içeriğini hazırlamak için

            // Adapter sınıfımızı oluşturmak için başlıklardan oluşan listimizi ve onlara bağlı olan elemanlarımızı oluşturmak için HaspMap türünü yolluyoruz
            expand_adapterLYS = new anaSayfaExpadapter(getApplicationContext(), dersListLys, konularListLYS);
            expandlist_viewLYS.setAdapter(expand_adapterLYS);  // oluşturduğumuz adapter sınıfını set ediyoruz



            ////////////////////////////////////////////////////////////////////diger yönterm
            // Get TabHost Refference

            // Set TabChangeListener called when tab changed
            // tabHost.setOnTabChangedListener((TabHost.OnTabChangeListener) this);


            ////////////////////////////////////////////////////////////////////
            // TabHost.TabSpec spec;
            // Intent intent;

            /************* TAB1 ************/
            // Create  Intents to launch an Activity for the tab (to be reused)
            //intent = new Intent().setClass(this, YGStab.class);
            //spec = tabHost.newTabSpec("First").setIndicator("YGS")
            //       .setContent(intent);

            //Add intent to tab
            // tabHost.addTab(spec);

            /************* TAB2 ************/
            //intent = new Intent().setClass(this, LYStab.class);
            // spec = tabHost.newTabSpec("Second").setIndicator("LYS")
            //         .setContent(intent);
            // tabHost.addTab(spec);


        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " anasayfa", durtion);
            toast.show();
        }

    }

    private void HazırlaYGS() {


        // başlıklara bağlı elemenları tutmak için oluşturduk
        try {
            dersListYgs = new ArrayList<dersEkle>();

            konularListYGS = new HashMap<>();

            dersListYgs.add(new dersEkle("FİZİK-I", 1));
            dersListYgs.add(new dersEkle("KİMYA-I", 2));

            konularFizikYGS.add(new konuEkle("kaldıracYgs", 1));
            konularFizikYGS.add(new konuEkle("vektorYgs", 2));
            konularKimyaYGS.add(new konuEkle("gazlarYgs", 3));
            konularKimyaYGS.add(new konuEkle("molekulYgs", 4));

            konularListYGS.put(dersListYgs.get(0), konularFizikYGS);
            konularListYGS.put(dersListYgs.get(1), konularKimyaYGS);

        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage(), durtion);
            toast.show();
        }
    }

    private void HazırlaLYS() {


        // başlıklara bağlı elemenları tutmak için oluşturduk
        try {
            dersListLys = new ArrayList<dersEkle>();

            konularListLYS = new HashMap<>();

            dersListLys.add(new dersEkle("FİZİK-II", 3));
            dersListLys.add(new dersEkle("KİMYA-II", 4));

            konularFizikLYS.add(new konuEkle("kaldırac", 1));
            konularFizikLYS.add(new konuEkle("vektor", 2));
            konularKimyaLYS.add(new konuEkle("gazlar", 3));
            konularKimyaLYS.add(new konuEkle("molekul", 4));

            konularListLYS.put(dersListLys.get(0), konularFizikLYS);
            konularListLYS.put(dersListLys.get(1), konularKimyaLYS);

        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage(), durtion);
            toast.show();
        }
    }

    public void onBackPressed() {
        try {
            //  backButtonHandler();
            //return;
            //super.onBackPressed();
        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " onBack", durtion);
            toast.show();
        }

    }

    public void backButtonHandler() {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    anaSayfa.this);
            alertDialog.setTitle("Uygulamadan Çıkmak?");
            alertDialog.setMessage("Uygulamadan cıkmak istediginizden emin misiniz");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setPositiveButton("EVET",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), anaSayfa.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                            finish();
                            System.exit(0);
                        }
                    });
            alertDialog.setNegativeButton("HAYIR",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " onBack", durtion);
            toast.show();
        }

    }

    public void soruTestGir(int id, final String konuadi) {
        try {

              LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.dialoglayout, null);

            TextView soruSayisi=(TextView)findViewById(R.id.soruSayisiView);
            TextView testSayisi=(TextView)findViewById(R.id.testSayisiView);
            final EditText soruSayisiGir=(EditText)findViewById(R.id.soruSayisitext);
            final EditText testSayisiGir=(EditText)findViewById(R.id.testSayisitext);


            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(konuadi+" ID: "+id);
            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            // disallow cancel of AlertDialog on click of back button and outside touch
            alert.setCancelable(false);
            alert.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alert.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();


        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " onBack", durtion);
            toast.show();
        }


    }


    public void clickMe(View view) {
        Button ID = (Button) view;
        Toast.makeText(this, "Button " + ID.getText().toString(), Toast.LENGTH_LONG).show();
    }

    private class anaSayfaExpadapter extends BaseExpandableListAdapter {
        private ArrayList<dersEkle>ListParent;
        private HashMap<dersEkle, ArrayList<konuEkle>> list_child;
        private Context context;

        private TextView dersAd;
        private TextView toplamKonu;
        private TextView toplamSoru;
        private TextView bitenSoru;
        private TextView bitenKonu;
        private Button ID;
        private CheckBox txt_child;
        private LayoutInflater inflater;
        @Override
        public int getGroupCount() {

            return ListParent.size();
        }

        public anaSayfaExpadapter(Context context, ArrayList<dersEkle> list_parent, HashMap<dersEkle, ArrayList<konuEkle>> list_child)
        {

            this.context = context;
            this.ListParent = list_parent;
            this.list_child = list_child;
        }

        @Override
        public int getChildrenCount(int groupPosition) {

            return list_child.get(ListParent.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {

            return ListParent.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {

            return list_child.get(ListParent.get(groupPosition)).get(childPosition);

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

            dersEkle ders=(dersEkle)getGroup(groupPosition);
            if(view == null)
            {
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.dersler,null);
            }
            bitenKonu=(TextView)view.findViewById(R.id.bitenKonu);
            bitenKonu.setText("15");
            bitenSoru=(TextView)view.findViewById(R.id.bitenSoru);
            bitenSoru.setText("150");
            toplamKonu=(TextView)view.findViewById(R.id.toplamkonu);
            toplamSoru=(TextView)view.findViewById(R.id.toplamSoru);
            dersAd = (TextView)view.findViewById(R.id.dersAd);
            dersAd.setText(ders.getDersAd());

            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View view, ViewGroup parent) {
            try {


                // kaçıncı pozisyonda ise başlığımızın elemanı onun ismini alarak string e atıyoruz
                final konuEkle konu = (konuEkle) getChild(groupPosition, childPosition);

                if (view == null) {
                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.konular, null);
                    // fonksiyon adından da anlaşılacağı gibi parent a bağlı elemanlarının layoutunu inflate ediyoruz böylece o görüntüye ulaşmış oluyoruz
                }

                // listview_child ulaştığımıza göre içindeki bileşeni de kullanabiliyoruz daha sonradan view olarak return ediyoruz
                txt_child = (CheckBox) view.findViewById(R.id.konuAd);
                txt_child.setText(konu.getKonuAd());
                txt_child.setFocusable(false);
                ID=(Button) view.findViewById(R.id.ID);
                ID.setText("soru gir");


            ID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soruTestGir(konu.getID(),konu.getKonuAd());


                }
            });


            }
            catch (Exception ex)
            {
                int durtion= Toast.LENGTH_LONG;
                Toast toast= Toast.makeText(context,ex.getMessage()+"exp",durtion);
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


