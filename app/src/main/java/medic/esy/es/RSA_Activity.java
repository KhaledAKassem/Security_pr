package medic.esy.es.security_project;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RSA_Activity extends AppCompatActivity {

    TextView header,header2,ppp,qqq,plainagain,khaled,khaledq;
    EditText plain,cipher;
    Button getcipher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        Typeface mytype= Typeface.createFromAsset(getAssets(),"fonts/fff.otf");
        header=(TextView)findViewById(R.id.textview);
        plainagain=(TextView)findViewById(R.id.plain_again);
        getcipher=(Button) findViewById(R.id.getcipher);
        header2=(TextView)findViewById(R.id.textView3);
        plain=(EditText) findViewById(R.id.rsa_palin);
        cipher=(EditText) findViewById(R.id.rsa_cipher);
        ppp=(TextView) findViewById(R.id.ppp);
        qqq=(TextView) findViewById(R.id.qq);

        header.setTypeface(mytype);
        header2.setTypeface(mytype);
        cipher.setTypeface(mytype);
        ppp.setTypeface(mytype);
        qqq.setTypeface(mytype);

        getcipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Plain
                String palin=plain.getText().toString();
                RSA_Activity ss=new RSA_Activity();
               //Get P
                int p=ss.ferma();
                String pp=p+"";
                ppp.setText(pp);
               //Get Q
                int q=ss.ferma();
                String qq=q+"";
                qqq.setText(qq);
                int n=p*q;
                int z=((p-1)*(q-1));
                int e=ss.getE(z, n);

                int d=ss.getd(e, z);
//                Condition is Equality of Data
                if(d==e){
                    d+=z;
                }

                int [] show=ss.cipher(palin, e, n);
                String temp="";
                char []khaled=new char[palin.length()];
                for(int i=0;i<show.length;i++){
                    temp+=(char) (show[i]+97);
//                    temp+=show[i];
            }
                cipher.setText(temp);
                String l=ss.plain(show, n, d);
                plainagain.setText(l);
            }
        });

    }

    public int ferma(){
        // step 1:  Get P & Q
        // Ferma equation
        // f= m^r mod r =m //   2 3 5 7
        int x=1;
        while(true){
            //Generate Rnadom Prime Number
            x=(int) (Math.random()*100)+1;
            if((Math.pow(2, x))%x==2){
                if((Math.pow(3, x))%x==3){
                    if((Math.pow(5, x))%x==5){
                        return x;
                    }           }    }
        }  }
    public int GCD(int a, int b) {
        if (b==0) {
            return a;
        }
        return GCD(b,a%b);
    }
    public int getE(int z,int n){

        int e=(int)Math.random()*n;
        e++;
        while(GCD(e, z)!=1){
            System.out.println(z+"---"+e+"-----"+n);
            e=(int)Math.random()*n;
        }
        return e;

    }
    public int getd(int e,int z){

        for(int i=0;i<z;i++){
            if(e*i%z==1){
                return i;
            }
        }
        return 0;
    }

    public int[] cipher(String data,int e,int n){
        data=data.toLowerCase();
        int c[]=new int[data.length()];
        int m;
        char []khaled=new char[data.length()];
        for(int i=0;i<data.length();i++){
            m=data.charAt(i)-97;
            c[i]=(m^e)%n;
            khaled[i]=(char) (c[i]+97);
            System.out.print(khaled[i]);
        }
        return c;
    }

    public String plain(int [] cipher_data,int n,int d){
        String m="";
        char mn;
        int c;
        int v;
        for(int i=0;i<cipher_data.length;i++){
            c=cipher_data[i];
            v=(c^d)%n;
            mn=(char) (v+97);
            m+=mn;
        }
        return m;
    }

}
