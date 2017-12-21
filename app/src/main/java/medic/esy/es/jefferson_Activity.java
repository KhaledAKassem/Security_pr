package medic.esy.es.security_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;



public class jefferson_Activity extends AppCompatActivity {

    EditText e1, e2;
    Button button_jeo;
    TextView decript_jeo;
    public int[][] get_cylinder = cylind(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jefferson_);
        e1 = (EditText) findViewById(R.id.jeofferson_palin);
        e2 = (EditText) findViewById(R.id.cipher_jeo);
        button_jeo = (Button) findViewById(R.id.jeobutton);
        decript_jeo=(TextView) findViewById(R.id.decript_jeo);
        button_jeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = e1.getText().toString();
                int plain[] = {3, 2, 3, 2};
                String oo = "";
                for (int i = 0; i < data.length(); i++) {
                    plain[i] = data.charAt(i) - 97;
                }
                jefferson_Activity jj = new jefferson_Activity();

                int get[] = jj.encription(plain);
                int plain2[] = jj.decription(get);
                String dataa="";
                for (int i = 0; i < plain2.length; i++) {
                    oo += (char) (plain2[i] + 97);
                    dataa+=get[i]+"";

                }
                decript_jeo.setText(oo);
                e2.setText(dataa);
            }
        });

    }

    public ArrayList ring(){

        ArrayList<Integer> arr=new ArrayList<>();
        int x=0;
        while(true){
            double sender= Math.random()*27;
            int value=(int) sender %26;
            if(!arr.contains(value)){
                arr.add(value);
                x++;
            }
            if(x==26){
                break;
            }
        }
        return arr;
    }

    public  int[][] cylind (int num){

        int  [][] arr=new int[num][26];
        for(int i=0;i<num;i++){
            ArrayList<Integer>ret_ring=ring();
            for(int j=0;j<26;j++){
                arr[i][j]=ret_ring.get(j);
            }

        }
        return arr;
    }
    public int [] encription (int get_plain[]){
        int [] ind=new int[4];
        for(int i=0;i<get_plain.length;i++){
            for(int j=0;j<26;j++){
                if(get_cylinder[i][j]==get_plain[i]){
                    ind[i]=get_cylinder[i][(j+5)%26];
                    continue;
                }
            }
        }
        return ind;
    }


    public int [] decription(int get_cipher[]){
        int [] plain=new int[4];

        for(int i=0;i<get_cipher.length;i++){
            for(int j=0;j<26;j++){
                if(get_cylinder[i][j]==get_cipher[i]){
                    plain[i]=get_cylinder[i][(j-5+26)%26];
                    continue;
                }
            }
        }
        return plain;
    }
}
