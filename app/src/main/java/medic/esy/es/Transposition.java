package medic.esy.es.security_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Transposition extends AppCompatActivity {

    static String key="kk";
    static String plaintext;
    EditText key_input,plain;
    Button getcipher;
    TextView pl,ci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transposition);

        key_input=(EditText)findViewById(R.id.trans_plain);
        plain=(EditText)findViewById(R.id.trans_message);
        getcipher=(Button)findViewById(R.id.trans_button);
        pl=(TextView)findViewById(R.id.trans_cipher);
        ci=(TextView)findViewById(R.id.trans_palin);


        getcipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key=key_input.getText().toString();
                key="securit";
                if (!checkkey(key)) {
                    Toast.makeText(getApplicationContext(),"please try anthor key", Toast.LENGTH_SHORT).show();
                }else{
                    plaintext=plain.getText().toString();

                    int x = (plaintext.length() % key.length());
                    x = key.length() - x;
                    for (int i = 0; i < x; i++) {
                        plaintext += (char) (i + 65);
                    }

                    int row = plaintext.length() / key.length();
                    int column = key.length();
                    char plain[][] = new char[row][column];
//        String temp="";
                    for (int i = 0; i < row; i++) {
//           temp=plaintext.substring(i*column,(i+1)*column-1);
                        for (int j = 0; j < column; j++) {
//                plain[i][j]=temp.charAt(j);
                            plain[i][j] = plaintext.charAt((i * column) + j);
                            System.out.print(plain[i][j]);

                        }
                        System.out.println("");
                    }

                    char one_key[] = key.toCharArray();
                    char sortkey[] = bubbleSort(one_key);
                    String kkk = sortkey.toString();
                    int array[] = new int[one_key.length];

                    for (int i = 0; i < sortkey.length; i++) {

                        //index for keys value
                        array[i] = key.indexOf((int) one_key[i]);

                    }
                    String cipher = "";
                    for (int i = 0; i < column; i++) {
                        for (int j = 0; j < row; j++) {
                            cipher += plain[j][array[i]];
                        }
                    }

            System.out.println(cipher);
            pl.setText(cipher);
            ci.setText(plain(cipher,key));

            System.out.println(plain(cipher, key));
                }

            }
        });

    }

    public static String plain(String cipher, String key) {

        int row = cipher.length() / key.length();
        int column = key.length();
        char cipher_text[][] = new char[row][column];
        //Sorting key/////////////////////////////////////////////////
        char one_key[] = key.toCharArray();
        char sortkey[] = bubbleSort(one_key);
        String kkk = sortkey.toString();
        int array[] = new int[one_key.length];

        for (int i = 0; i < sortkey.length; i++) {

            //index for keys value
            array[i] = key.indexOf((int) one_key[i]);

        }
        //////////// ending sorting///////////////////////////////////////
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                cipher_text[j][array[i]] = cipher.charAt((row * i) + j);
            }
        }
        String plain = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                plain += cipher_text[i][j];
            }
        }
        return plain;
    }

    static char[] bubbleSort(char[] arr) {
        int n = arr.length;
        char temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static boolean checkkey(String key) {
        for (int i = 0; i < key.length() - 1; i++) {
            for (int j = i + 1; j < key.length(); j++) {
                if (key.charAt(i) == key.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}