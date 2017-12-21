package medic.esy.es.security_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AFFINE extends AppCompatActivity {

    EditText e1,e2;
    Button affine_cipher;
    final static String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static int m =7; //m
    public static int key=10;
    public static int n=26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine);
        e1=(EditText)findViewById(R.id.affine_plain);
        e2=(EditText)findViewById(R.id.affine_cipher);
        affine_cipher=(Button)findViewById(R.id.getcipheraffien);

        affine_cipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GCD(m, n)==1){
                    String message = e1.getText().toString();
                    message=message.toUpperCase();

                    String encription=Encription(message);
                    e2.setText(encription);
                }
                else{
                    Toast.makeText(getApplication(),"GCD BETWEEN M AND N MUST BE RELATIVE PRIME",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public static int GCD(int a, int b) {
        if (b==0) {
            return a;
        }
        return GCD(b,a%b);
    }

    public static String Encription(String Msg)
    {
        String Cipher_Text = "";

        for (int i = 0; i < Msg.length(); i++)
        {
            int in=alphabet.indexOf(Msg.charAt(i));
            System.out.println(in);
            Cipher_Text = Cipher_Text + (char) (((in*m+ key) % n)+65);
//            System.out.println((char)((((22*m)+key)%n)+65));
        }
        return Cipher_Text;
    }
    public static String Decription(String CTxt)
    {
        String Message = "";
        int Multiplicative_inverse = 0;
        int flag = 0;
        for (int i = 0; i < n; i++)
        {
            flag = (m * i) % n;
            if (flag == 1)
            {
                Multiplicative_inverse = i;
                System.out.println("MODULO MULTIPLICATIVE INVERSE "+" "+i);
            }
        }
        for (int i = 0; i < CTxt.length(); i++)
        {
            int in=alphabet.indexOf(CTxt.charAt(i));

            //M(C-S)
            int p_1=((Multiplicative_inverse*(in-key))+n*10);
            Message = Message + (char) ((p_1%n)+ 65);
        }
        return Message;
    }

}
