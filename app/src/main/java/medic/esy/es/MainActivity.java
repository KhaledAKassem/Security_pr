package medic.esy.es.security_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView header1,header2;
    Button rsa,affine,transposition,jeofferson,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        header1=(TextView) findViewById(R.id.header1);
        header2=(TextView) findViewById(R.id.header2);
        rsa=(Button) findViewById(R.id.button);
        affine=(Button) findViewById(R.id.button2);
        transposition=(Button) findViewById(R.id.button3);
        jeofferson=(Button) findViewById(R.id.button4);
        des=(Button) findViewById(R.id.button5);
        rsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),RSA_Activity.class);
                startActivity(i);
            }

        });
        affine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AFFINE.class);
                startActivity(i);
            }
        });

        transposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Transposition.class);
                startActivity(i);
            }
        });
        jeofferson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),jefferson_Activity.class);
                startActivity(i);
            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Des_Activity.class);
                startActivity(i);
            }
        });
        Typeface mytype= Typeface.createFromAsset(getAssets(),"fonts/fff.otf");
        Typeface mytype2= Typeface.createFromAsset(getAssets(),"fonts/foont.ttf");
        header1.setTypeface(mytype);
        header2.setTypeface(mytype);

    }
}
