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
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Apo on 8.03.2017.
 */

public class anaSayfa extends AppCompatActivity {

    public static int zorluk=1;
    private int hedef1=50;
    private int hedef2=100;
    private int hedef3=150;
    TabHost tabHost;
    Context contex = this;
    public static TextView anasayfaLYS,anasayfaYGS;
    DataBase db=new DataBase(anaSayfa.this);

//////////////////////////////////////////////////YGS BÖLÜMÜ

    private ArrayList<dersEkle> dersListYgs;
    private anaSayfaExpadapter expand_adapterYGS;
    private HashMap<dersEkle, ArrayList<konuEkle>> konularListYGS;
    final ArrayList<konuEkle> konularMatematikYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularGeometriYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularFizikYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularKimyaYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularBiyolojiYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularTurkceYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularCografyaYGS = new ArrayList<konuEkle>();
    final ArrayList<konuEkle> konularTarihYGS = new ArrayList<konuEkle>();

    private ExpandableListView expandlist_viewYGS;
    /////////////////////////////////////////LYS bölümü

    private ArrayList<dersEkle> dersListLys;
    private anaSayfaExpadapter expand_adapterLYS;
    private HashMap<dersEkle, ArrayList<konuEkle>> konularListLYS;
    final public ArrayList<konuEkle> konularMatematikLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularGeometriLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularFizikLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularKimyaLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularBiyolojiLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularEdebiyatLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularCografyaLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularTarihLYS = new ArrayList<konuEkle>();
    final public ArrayList<konuEkle> konularFelsefeLYS = new ArrayList<konuEkle>();

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
                case R.id.test:
                    i = new Intent(".TEST");
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

            anasayfaYGS=(TextView)findViewById(R.id.anasayaYGSkalanView);
            anasayfaLYS =(TextView)findViewById(R.id.anasayfaLYSkalanView);
            try {
                anasayfaYGS.setText(tarihEkle.ygsTarih.getText());
                anasayfaLYS.setText(tarihEkle.lysTarih.getText());
            }
            catch (Exception ex)
            {}

            ImageView image=(ImageView)findViewById(R.id.ngIcon1);
            toplamBitemKonu();

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
            int counter=1;
            konularListYGS = new HashMap<>();
            for(int i=0;i<derslerYGS.length;i++)//dersler
            {
                dersListYgs.add(new dersEkle(derslerYGS[i],(i+1)));
            }

            for(int i=0;i<matYgsKonular.length;i++)///matematikYGs
            {
                konularMatematikYGS.add(new konuEkle(matYgsKonular[i],counter));
                counter++;
            }
            for(int i=0;i<geoYgsKonular.length;i++)//geometriYGS
            {
                konularGeometriYGS.add(new konuEkle(geoYgsKonular[i],counter));
                counter++;
            }
            for(int i=0;i<fizikYgsKonular.length;i++)//fizikYGs
            {
               konularFizikYGS.add(new konuEkle(fizikYgsKonular[i],counter));
                counter++;
            }
            for(int i=0;i<kimyaYgsKonular.length;i++)//KİMYAYGS
            {
               konularKimyaYGS.add(new konuEkle(kimyaYgsKonular[i],counter));
                counter++;
            }
            for(int i=0;i<biyolojiYgsKonular.length;i++)//BiyolojiYGs
            {
               konularBiyolojiYGS.add(new konuEkle(biyolojiYgsKonular[i],counter));
                counter++;
            }
            for(int i=0;i<turkceYgsKonular.length;i++)//TürkceYGs
            {
               konularTurkceYGS.add(new konuEkle(turkceYgsKonular[i],counter));
                counter++;
            }
            for(int i=0;i<cografyaYgsKonular.length;i++)//CografyaYGs
            {
               konularCografyaYGS.add(new konuEkle(cografyaYgsKonular[i],counter));
                counter++;
            }

            for(int i=0;i<tarihYgsKonular.length;i++)//TarihYGs
            {
                konularTarihYGS.add(new konuEkle(tarihYgsKonular[i],counter));
                counter++;
            }


            konularListYGS.put(dersListYgs.get(0),konularMatematikYGS);
            konularListYGS.put(dersListYgs.get(1),konularGeometriYGS);
            konularListYGS.put(dersListYgs.get(2),konularFizikYGS);
            konularListYGS.put(dersListYgs.get(3),konularKimyaYGS);
            konularListYGS.put(dersListYgs.get(4),konularBiyolojiYGS);
            konularListYGS.put(dersListYgs.get(5),konularTurkceYGS);
            konularListYGS.put(dersListYgs.get(6),konularCografyaYGS);
           konularListYGS.put(dersListYgs.get(7),konularTarihYGS);


        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage()+"hazılra lys", durtion);
            toast.show();
        }
    }

    private void HazırlaLYS() {


        // başlıklara bağlı elemenları tutmak için oluşturduk
        try {
            dersListLys = new ArrayList<dersEkle>();
            int counter=127;
            konularListLYS = new HashMap<>();
            for(int i=0;i<derslerLYS.length;i++)//derslerLYS
            {
                dersListLys.add(new dersEkle(derslerLYS[i],(i+9)));
            }

           for ( int i=0;i<matLysKonular.length;i++)//matematikLYS
           {
               konularMatematikLYS.add(new konuEkle(matLysKonular[i],counter));
               counter++;
           }
            for ( int i=0;i<geoLysKonular.length;i++)//geometriLYS
            {
                konularGeometriLYS.add(new konuEkle(geoLysKonular[i],counter));
                counter++;
            }
            for ( int i=0;i<fizikLysKonular.length;i++)//fizikLYs
            {
                konularFizikLYS.add(new konuEkle(fizikLysKonular[i],counter));
                counter++;
            }
            for ( int i=0;i<kimyaLYsKonular.length;i++)//kimyaLYS
            {
                konularKimyaLYS.add(new konuEkle(kimyaLYsKonular[i],counter));
                counter++;
            }
            for ( int i=0;i<biyolojiLysKonular.length;i++)//biyolojiLYS
            {
                konularBiyolojiLYS.add(new konuEkle(biyolojiLysKonular[i],counter));
                counter++;
            }
            for ( int i=0;i<edebiyatLysyKonular.length;i++)//edebiyatLys
            {
                konularEdebiyatLYS.add(new konuEkle(edebiyatLysyKonular[i],counter));
                counter++;
            }
            for ( int i=0;i<cografyaLysKonuları.length;i++)//cografyaLYs
            {
                konularCografyaLYS.add(new konuEkle(cografyaLysKonuları[i],counter));
                counter++;
            }
            for ( int i=0;i<tarihLysKonular.length;i++)//tarihLYS
            {
                konularTarihLYS.add(new konuEkle(tarihLysKonular[i],counter));
                counter++;
            }
            for ( int i=0;i<felsefeLysKonular.length;i++)//FelsefeLYS
            {
                konularFelsefeLYS.add(new konuEkle(felsefeLysKonular[i],counter));
                counter++;
            }




            konularListLYS.put(dersListLys.get(0), konularMatematikLYS);
            konularListLYS.put(dersListLys.get(1), konularGeometriLYS);
            konularListLYS.put(dersListLys.get(2), konularFizikLYS);
            konularListLYS.put(dersListLys.get(3), konularKimyaLYS);
            konularListLYS.put(dersListLys.get(4), konularBiyolojiLYS);
            konularListLYS.put(dersListLys.get(5), konularEdebiyatLYS);
            konularListLYS.put(dersListLys.get(6), konularCografyaLYS);
            konularListLYS.put(dersListLys.get(7),konularTarihLYS);
            konularListLYS.put(dersListLys.get(8), konularFelsefeLYS);




        } catch (Exception ex) {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage(), durtion);
            toast.show();
        }
    }

    public void onBackPressed() {


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

    public void soruTestGir(final int id, final String konuadi) {
        try {

              LayoutInflater inflater = getLayoutInflater();
            final View alertLayout = inflater.inflate(R.layout.dialoglayout, null);
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
                    TextView soruSayisi=(TextView) alertLayout.findViewById(R.id.soruSayisiView);
                    TextView testSayisi=(TextView) alertLayout.findViewById(R.id.testSayisiView);
                    final EditText soruSayisiGir=(EditText)alertLayout.findViewById(R.id.soruSayisitext);
                    final EditText testSayisiGir=(EditText)alertLayout.findViewById(R.id.testSayisitext);
                  final  String soru=soruSayisiGir.getText().toString();
                   final String test=testSayisiGir.getText().toString();

                 int sorusayisi=Integer.parseInt(soru);
                   int testsayisi=Integer.parseInt(test);
                    db.Open();
                  db.soruVeTestGir(id,sorusayisi,testsayisi);
                   db.Close();
                    String bitensoru=SorularSayisiBul(id);
                    int  bitenSorusayisi=Integer.valueOf(bitensoru);
                    if(bitenSorusayisi>=hedef3)
                    {
                        konuTik(id);
                        toplamBitemKonu();
                    }
                    expandlist_viewYGS.setAdapter(expand_adapterYGS);
                    expandlist_viewLYS.setAdapter(expand_adapterLYS);
                    // dersAyarlari ders=new dersAyarlari();
                  //  ders.onBackPressed();

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

    private void toplamBitemKonu()
    {
        try {
            TextView konuView=(TextView)findViewById(R.id.bitirilenToplamKonuView);
            db.Open();
            String bitenKonu=db.bitenKonu();
            konuView.setText(bitenKonu);
            db.Close();

        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " toplam", durtion);
            toast.show();
        }
    }

    private void konuTik(int ID)
    {
        try {
            db.Open();
            db.konuBitti(ID);

            db.Close();
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " konut bitti", durtion);
            toast.show();
        }

    }

    public void clickMe(View view) {
        Button ID = (Button) view;
        Toast.makeText(this, "Button " + ID.getText().toString(), Toast.LENGTH_LONG).show();
    }
    private String bitenKonular(int id)
    {
        try {
            db.Open();
            String bitenkonu=db.bitenKonu(id);
            db.Close();
            return bitenkonu;
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " biten konu", durtion);
            toast.show();
        }
       return "-1";
    }
    private String toplamSorular(int id)
    {
        try {
            db.Open();
            String toplamsoru=db.bitenSoru(id);
            db.Close();
            return  toplamsoru;
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " toplam soru", durtion);
            toast.show();
        }
        return "-1";

    }

    private String SorularSayisiBul(int ıd) {
        db.Open();
      String sayi=  db.sorsayisibul(ıd);
        db.Close();
        return sayi;
    }

    private void chechKontrol()
    {
        expandlist_viewYGS.setAdapter(expand_adapterYGS);
        expandlist_viewLYS.setAdapter(expand_adapterLYS);
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

            String bitensoru=toplamSorular(ders.getID());


            String bitenkonu=bitenKonular(ders.getID());
            bitenKonu=(TextView)view.findViewById(R.id.bitenKonu);
            bitenKonu.setText(bitenkonu);
            bitenSoru=(TextView)view.findViewById(R.id.bitenSoru);
            bitenSoru.setText(bitensoru);
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
    String [] derslerYGS= new String[]{"MATEMATİK","GEOMETRİ","FİZİK","KİMYA", "BİYOLOJİ","TÜRKCE","COĞRAFYA",
            "TARİH"};
    String [] derslerLYS=new String[]{"MATEMATİK","GEOMETRİ","FİZİK","KİMYA", "BİYOLOJİ","EDEBİYAT","COĞRAFYA","TARİH","FELSEFE"};
    //////////
    String [] matYgsKonular=new String[]{"Sayılar","Sayı Basamakları" , "Bölme ve Bölünebilme","OBEB-OKEK","Rasyonel Sayılar",
            "Basit Eşitsizlikler" , "Mutlak Değer", "Üslü Sayılar" , "Köklü Sayılar" , "Çarpanlara Ayırma", "Oran Orantı",
            "Denklem Çözme","Problemler","Kümeler", "Fonksiyonlar", "Permütasyon", "Kombinasyon", "Binom", "Olasılık",
            "İstatistik", "2. Dereceden Denklemler", "Karmaşık Sayılar", "Parabol", "Polinomlar"};
    String[] matLysKonular=new String[]{"Sayılar", "Sayı Basamakları", "Bölünebilme", "OBEB-OKEK", "Rasyonel Sayılar",
            "Basit Eşitsizlikler", "Mutlak Değer" , "Üslü Sayılar", "Köklü Sayılar", "Çarpanlara Ayırma", "Oran Orantı",
            "Denklem Çözme", "Kümeler", "Fonksiyonlar", "Permütasyon","Kombinasyon", "Binom", "Olasılık", "İstatistik",
            "İkinci Dereceden Denklemler", "Karmaşık Sayılar", "Parabol", "Polinomlar", "Mantık", "Modüler Aritmetik",
            "Eşitsizlikler", "Logaritma", "Diziler", "Seriler", "Limit ve Süreklilik", "Türev", "İntegral"};
    String [] geoYgsKonular=new String[]{"Doğruda ve Üçgende Açılar", "Dik ve Özel Üçgenler", "Dik Üçgende Trigonemetrik Bağıntılar",
            "İkizkenar ve Eşkenar Üçgen", "Üçgende Alanlar", "Üçgende Açıortay Bağıntıları", "Üçgende Kenarortay Bağıntıları",
            "Üçgende Eşlik ve Benzerlik", "Üçgende Açı-Kenar Bağıntıları", "Çokgenler", "Dörtgenler", "Yamuk",
            "Paralelkenar", "Eşkenar Dörtgen – Deltoid", "Dikdörtgen", "Çemberde Açılar", "Çemberde Uzunluk","Daire",
            "Prizmalar", "Piramitler", "Küre", "Koordinat Düzlemi ve Noktanın Analitiği", "Vektörler-1", "Doğrunun Analitiği",
            "Tekrar Eden, Dönen ve Yansıyan Şekiller"};
    String [] geoLysKonular=new String[]{"Doğruda ve Üçgende Açılar", "Dik ve Özel Üçgenler", "Dik Üçgende Trigonemetrik Bağıntılar",
            "İkizkenar ve Eşkenar Üçgen", "Üçgende Alanlar", "Üçgende Açıortay Bağıntıları", "Üçgende Kenarortay Bağıntıları",
            "Üçgende Eşlik ve Benzerlik","Üçgende Açı-Kenar Bağıntıları", "Çokgenler", "Dörtgenler", "Yamuk", "Paralelkenar",
            "Eşkenar Dörtgen – Deltoid", "Dikdörtgen", "Çemberde Açılar", "Çemberde Uzunluk", "Daire", "Prizmalar", "Piramitler",
            "Küre", "Koordinat Düzlemi ve Noktanın Analitiği", "Vektörler-1", "Doğrunun Analitiği", "Tekrar Eden, Dönen ve Yansıyan Şekiller",
            "Uzay Geometri", "Dönüşümlerle Geometri", "Trigonometri", "Çemberin Analitiği", "Genel Konik Tanımı (Dış Merkezlik)",
            "Parabol", "Elips", "Hiperbol"};
    String [] fizikYgsKonular=new String[]{"Fizik Bilimine Giriş", "Vektör-Kuvvet ve Kuvvet Dengesi", "Basit Makineler",
            "Madde ve Özellikleri","Sıvıların Kaldırma Kuvveti", "Basınç", "Isı ve Sıcaklık", "Genleşme", "Doğrusal Hareket",
            "Dinamik", "İş Enerji", "Işık ve Gölge", "Düzlem Ayna", "Küresel Aynalar", "Kırılma ve Renkler", "Merceler ve Aydınlanma",
            "Elektrostatik", "Elektrik Akımı ve Devreler", "Temel Dalga Bilgileri", "Yay Dalgaları", "Su Dalgaları", "Ses ve Deprem Dalgaları"};
    String [] fizikLysKonular=new String[]{"Fizik Bilimine Giriş", "Vektör-Kuvvet ve Kuvvet Dengesi", "Tork", "Ağırlık Merkezi",
            "Basit Makineler", "Madde ve Özellikleri", "Sıvıların Kaldırma Kuvveti", "Basınç", "Isı ve Sıcaklık", "Genleşme",
            "Doğrusal Hareket", "Bağıl Hareket", "Dinamik", "Atışlar", "İş Enerji", "Dönme Hareketi", "Basit Harmonik Hareket",
            "İtme-Momentum","Kütle Çekimi -Kepler Kanunu- Büyük Patlama", "Işık ve Gölge", "Düzlem Ayna", "Küresel Aynalar",
            "Kırılma ve Renkler", "Merceler ve Aydınlanma", "Elektrostatik", "Elektrik Alan- Elektriksel Potansiyel ve İş",
            "Yüklü Parçacıkların Hareketi", "Elektrik Akımı ve Devreler", "Temel Dalga Bilgileri","Yay Dalgaları", "Su Dalgaları",
            "Ses ve Deprem Dalgaları", "Manyetizma", "Elektromanyetik İndüksüyon", "Sığaçlar", "Alternatik Akım ve Transformatör",
            "Dalga Mekaniği (Kırınım-Girişim-Doopler)", "Elektromanyetik Dalgalar", "Fotoelektrik ve Comptpn Olayı", "Özel Görelilik",
            "Atom Modelleri", "Atom Altı Parçacıklar", "Radyoaktivite","Modern Fiziğin Teknolojideki Uygulamaları"};
    String [] kimyaYgsKonular=new String[]{"Kimya Bilimi", "Atom ve Yapısı", "Periyodik Sistem", "Kimyasal Türler Arası Etkileşimler",
            "Asitler-Bazlaar ve Tuzlar", "Bileşikler", "Kimyasal Tepkimeler", "Kimyanın Temel Yasaları", "Maddenin Halleri",
            "Karışımlar", "Endüstride ve Canlılarda Enerji", "Kimya Her Yerde"};
    String []kimyaLYsKonular=new String[]{"Modern Atom Teorisi","Kimyasal Hesaplamalar", "Gazlar", "Sıvı Çözeltiler", "Kimya ve Enerji",
            "Tepkimelerde Hız", "Kimyasal Denge", "Sıvı Çözeltilerde Denge", "Kimya ve Elektrik", "Karbon Kimyasına Giriş",
            "Organik Bileşikler", "Hayatımızdaki Kimya"};
    String [] biyolojiYgsKonular =new String[]{"Biyoloji Bilimi, İnorganik Bileşikler", "•Organik Bileşikler", "Hücre",
            "Madde Geçişleri", "DNA-RNA", "Protein Sentezi", "Enzimler", "Canlıların Sınıflandırılması", "Ekoloji", "Hücre Bölünmeleri",
            "Eşeysiz-Eşeyli Üreme", "İnsanda üreme ve Gelişme", "Mendel Genetiği", "Kan Grupları", "Cinsiyete Bağlı Kalıtım"};
    String [] biyolojiLysKonular=new String[]{"Biyoloji Bilimi, İnorganik Bileşikler", "Organik Bileşikler", "Hücre",
            "Madde Geçişleri", "DNA-RNA", "Protein Sentezi", "Enzimler", "Canlıların Sınıflandırılması", "Ekoloji", "Hücre Bölünmeleri",
            "Eşeysiz-Eşeyli Üreme", "İnsanda üreme ve Gelişme", "Mendel Genetiği", "Kan Grupları", "Cinsiyete Bağlı Kalıtım",
            "Biyoteknoloji, Evrim", "Solunum", "Fotosentez", "Kemosentez", "Sistemler", "Duyu Organları"};
    String [] turkceYgsKonular =new String[]{"Kelime Bilgisi","Cümle Bİlgisi","Paragraf Bilgisi","Sözcük Türleri",
            "Ses Bilgisi ve Telaffuz","Anlatım Türleri","Yazım Kuralları ve Noktalama İşaretleri","Ankatım Bozuklukları"};
    String [] edebiyatLysyKonular=new String[]{"Güzel Sanatlar ve Edebiyat","Coşku ve Heyecan Dile Getiren Metinler (Şiir)",
            "Olay Çevresinde Oluşan Edebi Mtinler","Öğretici Metinler (Edebiyat 9. Sınıf)", "Tarih İçinde Türk Edebiyatı",
            "Destan Dönemi Türk Edebiyatı", "İslam Uygarlığı Çevresinde Gelişen Türk Edebiyatı","Batı Tesirindeki Türk Edebiyatına Giriş ",
            "Tanzimat Dönemi Edebiyatı ","Servet-i Fünun Edebiyatı ","Milli Edebiyat Dönemi ","Cumhuriyet Dönemi Türk Edebiyatı ",
            "Cumhuriyet Döneminde Öğretici Metinler","Cumhuriyet Döneminde Coşku ve Heyecanı Dile Getiren Metinler (Şiir)","Cumhuriyet Döneminde Olay Çevresinde Oluşan Edebi Metinler"};
    String [] cografyaYgsKonular=new String[]{"Doğal Sistemler","Beşeri Sistemler","Türkiye Coğrafyası","Küresel Ortam","Çevre Ve Toplım"};
    String[] cografyaLysKonuları=new String[]{"Doğal Sistemler", "Beşeri Sistemler", "Mekansal Sentez Türkiye", "Küresel Ortam: Bölgeler ve Ülkeler",
            "•Çevre ve Toplum", "Ekonomik Faaliyetler"};
    String [] tarihYgsKonular=new String[]{"Tarih Bilimi","Uygarlığın Doğuşu ve İlk Uygarlıklar","İslam Tarihi ve Uygralığı",
            "Türk İslam Devletleri","Türkiye Tarihi:Türkiye Selçukluları","Beylikten Devlete (1300-1453)","Dünya Gücü: Osmanlı Devleti (1453-1600) ",
            "Arayış Yılları (17. Yüzyıl)","Avrupa ve Osmanlı Devleti (18. Yüzyıl)","En Uzun Yüzyıl (1800-1922)","Milli Mücadele’nin Hazırlık Dönemi",
            "Kurtuluş Savaşı’nda Cepheler","Türk İnkılabı","Atatürkçülük ve Atatürk İlkeleri","•  Atatürk Dönemi Türk Dış Politikası"};
    String [] tarihLysKonular=new String[]{"Tarih Bilimi", "Uygarlığın Doğuşu ve İlk Uygarlıklar", "İlk Türk Devletleri",
            "İslam Tarihi ve Uygarlığı", "Türk-İslam Devletleri", "Türkiye Tarihi", "Beylikten Devlete (1300-1453)",
            "Dünya Gücü: Osmanlı Devleti (1453-1600)", "Arayış Yılları (17. Yüzyıl)", "Avrupa ve Osmanlı Devleti (18. Yüzyıl)",
            "En Uzun Yüzyıl (1800-1922)", "1881’den 1919’a Mustafa Kemal", "Milli Mücadele’nin Hazırlık Dönemi", "Kurtuluş Savaşı’nda Cepheler",
            "Türk İnkılabı", "Atatürkçülük ve Atatürk İlkeleri", "Atatürk Dönemi Türk Dış Politikası", "Atatürk’ün Ölümü",
            "Yüzyılın Başlarında Dünya", "İkinci Dünya Savaşı", "Soğuk Savaş Dönemi", "Yumuşama Dönemi ve Sonrası", "Küreselleşen Dünya",
            "Türklerde Devlet Teşkilatı", "Türklerde Toplum Yapısı", "Türklerde Hukuk", "Türklerde Ekonomi",
            "Türklerde Eğitim", "Türklerde Sanat"};
    String [] felsefeLysKonular=new String[]{"Mantığa Giriş", "Klasik Mantık", "Mantık ve Dil", "Sembolik Mantık",
            "Psikoloji Bilimini Tanıyalım", "Psikolojinin Temel Süreçleri", "Öğrenme Bellek Düşünme", "Ruh Sağlığının Temelleri",
            "Sosyolojiye Giriş", "Birey ve Toplum", "Toplumsal Yapı", "Toplumsal Değişme ve Gelişme","Toplum ve Kültür", "Toplumsal Kurumlar"};

}


