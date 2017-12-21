package medic.esy.es.security_project;

///////////////////DES ENCRIPTION AND DECRIPTION ACTIVITY//////

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Des_Activity extends AppCompatActivity {

    Button cipher_button;
    EditText des_messageq;
    TextView txt_plain,txt_cipher;

        //////Initalizing Static Data////////////////////

    //PC_1
    public static final int pc_1[] = {57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18, 10, 2,
            59, 51, 43, 35, 27, 19, 11,
            3, 60, 52, 44, 36, 63, 55, 47,
            39, 31, 23, 15, 7, 62, 54, 46, 38,
            30, 22, 14, 6, 61, 53, 45,
            37, 29, 21, 13, 5, 28, 20, 12, 4};
    //PC_2
    public static final int  pc_2_left[] = {14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23,
            19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2};

    //IP[]
    public static final int  ip[]={58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6
            ,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,
            61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7
    };

    //PC_2_Right
    public static final int pc_2_right[] = {41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44,
            49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
    //IIP
    public static final int[] IIP = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41,  9, 49, 17, 57, 25
    };

    //E-Bit Selection Table
    public static final int[] E = {
            32,  1, 2,   3,    4,  5,
            4,   5,  6,  7,    8,  9,
            8,   9,  10,  11, 12,13,
            12, 13, 14, 15, 16,  17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32,  1
    };


      //P
    public static final int[] P = {
            16,  7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2,  8, 24, 14,
            32, 27,  3,  9,
            19, 13, 30,  6,
            22, 11,  4, 25
    };

    //USING 3 DIMENSIONAL ARRAY

    public static  String[][][] s_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);

        cipher_button = (Button) findViewById(R.id.desyades);
        txt_plain = (TextView) findViewById(R.id.plain_des);
        txt_cipher = (TextView) findViewById(R.id.dec_cipher);
        des_messageq = (EditText) findViewById(R.id.des_message);
        //You Can letters or bits zero or one I handle all data

           // KEY
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
           //ITS OUT DATA
        String oo = "khaledkh";
           //CONVERTING IT TO BITS (0) OR (ONE)
        String data = srt2bit(oo).toString();

       final String encrpt =Encription(data, key);
       final String decrpt =Decription(encrpt,key);

        cipher_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_cipher.setText(encrpt);
                txt_plain.setText(decrpt);
            }
        });


    }

    //XOR FUNCTION
    public static String xor(String A,String B){
        String result="";
        for(int i=0;i<A.length();i++){
            result+=(B.charAt(i))^(A.charAt(i));


        }
        return result;
    }


    //STR2BIT
    public static String srt2bit(String name) {

        String key = "";
        int x;
        String y, z = "00000000";
        for (int i = 0; i < name.length(); i++) {
            x = (int) name.charAt(i);
            y = Integer.toBinaryString(x);
            key += z.substring(0, 8 - y.length()) + y;

        }
        return key;
    }

    //s_boxes
    public static String [][][] sbox(){

        int[] S1 = {
                14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7,
                0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8,
                4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0,
                15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13
        };
        int[] S2 = {
                15,  1,  8, 14,  6, 11,  3,  4,  9,  7,  2, 13, 12,  0,  5, 10,
                3, 13,  4,  7, 15,  2,  8, 14, 12,  0,  1, 10,  6,  9, 11,  5,
                0, 14,  7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15,
                13,  8, 10,  1,  3, 15,  4,  2, 11,  6,  7, 12,  0,  5, 14,  9
        };
        int[] S3 = {
                10,  0,  9, 14,  6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8,
                13,  7,  0,  9,  3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1,
                13,  6,  4,  9,  8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7,
                1, 10, 13,  0,  6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12
        };
        int[] S4 = {
                7, 13, 14,  3,  0,  6,  9, 10,  1,  2,  8,  5, 11, 12,  4, 15,
                13,  8, 11,  5,  6, 15,  0,  3,  4,  7,  2, 12,  1, 10, 14,  9,
                10,  6,  9,  0, 12, 11,  7, 13, 15,  1,  3, 14,  5,  2,  8,  4,
                3, 15,  0,  6, 10,  1, 13,  8,  9,  4,  5, 11, 12,  7,  2, 14
        };
        int[] S5 = {
                2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13,  0, 14,  9,
                14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3,  9,  8,  6,
                4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6,  3,  0, 14,
                11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10,  4,  5,  3
        };
        int[] S6 = {
                12,  1, 10, 15,  9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11,
                10, 15,  4,  2,  7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8,
                9, 14, 15,  5,  2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6,
                4,  3,  2, 12,  9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13
        };
        int[] S7 = {
                4, 11,  2, 14, 15,  0,  8, 13,  3, 12,  9,  7,  5, 10,  6,  1,
                13,  0, 11,  7,  4,  9,  1, 10, 14,  3,  5, 12,  2, 15,  8,  6,
                1,  4, 11, 13, 12,  3,  7, 14, 10, 15,  6,  8,  0,  5,  9,  2,
                6, 11, 13,  8,  1,  4, 10,  7,  9,  5,  0, 15, 14,  2,  3, 12
        };
        int[] S8 = {
                13,  2,  8,  4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7,
                1, 15, 13,  8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2,
                7, 11,  4,  1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8,
                2,  1, 14,  7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11
        };



        String S[][][]=new String[8][4][16];
        String y, z = "0000";

        for(int i=0;i<4;i++){
            for(int j=0;j<16;j++){
                y=Integer.toBinaryString(S1[i*16+j]);
                S[0][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S2[i*16+j]);
                S[1][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S3[i*16+j]);
                S[2][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S4[i*16+j]);
                S[3][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S5[i*16+j]);
                S[4][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S6[i*16+j]);
                S[5][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S7[i*16+j]);
                S[6][i][j]=z.substring(0,4-y.length())+y;
                y=Integer.toBinaryString(S8[i*16+j]);
                S[7][i][j]=z.substring(0,4-y.length())+y;
            }
        }
        return  S;
    }

            //For Making Permutation
    public static String Permutation(String bits, int[] table) {
        String output = "";
        for (int i = 0; i < table.length; i++) {
            output += bits.charAt(table[i] - 1);
        }
        return output;
    }


    //Take 32 bit expand to 48 bit
    public static String E_bit_selection_table(String data){
        data=Permutation(data,E);
        return data;
    }

    // KEY_GENERATION



          //DATA INITAL PERMUTATION
    public static String Inital_permutaion_data(String data){
        data=Permutation(data, ip);
        return data;

    }

         //SWAPPING FUNCTION
    public static String swap(String data){
        return data.substring(32,64)+data.substring(0,32);
    }


    public static String[] keyGeneration(String key) {

        key = Permutation(key, pc_1);
        String key_L = key.substring(0, 28);
        String key_R = key.substring(28, 56);
        String subkey[] = new String[16];
        String k_L = "";
        String k_R = "";
        String concate;

        for (int i = 0; i < 16; i++) {
            key_L = shift(key_L, i + 1);
            key_R = shift(key_R, i + 1);
            concate = key_L + key_R;
            k_L = Permutation(key_L, pc_2_left);
            k_R = Permutation(concate, pc_2_right);
            subkey[i] = k_L + k_R;

        }
        return subkey;

    }

         //INVERSE PERMUTATION
    public static String inverse_permutation(String data){
        return Permutation(data, IIP);
    }
          //SHIFTING
    public static String shift(String key, int round) {

        if (round == 1 || round == 2 || round == 9 || round == 16) {
            key = key.substring(1, 28) + key.charAt(0);
        } else {
            key = key.substring(1, 28) + key.charAt(0);
            key = key.substring(1, 28) + key.charAt(0);
        }

        return key;
    }



          //PERMUTATION_P FUNCTION
    public static String permutaion_p(String data){
        data=Permutation(data, P);
        return data;
    }


          //MANGULAR FUNCTION
    public static String MangularFunction(String right_data,String subkey){
        String r_data=E_bit_selection_table(right_data);
        s_box=sbox();
        String result=xor(r_data, subkey);
        String temp="";
        String data="";
        int row,column;
        String r;
        String c;
        for(int i=0;i<48;i+=6){
            temp=result.substring(i,i+6);
            r=""+temp.charAt(0);
            r+=temp.charAt(5);
            c=temp.substring(1,5);
            row = Integer.parseInt(r,2);
            column=Integer.parseInt(c,2);
            data+=s_box[i/6][row][column];
        }
        return permutaion_p(data);
    }

         //ROUND METHOD
    public static String round(String data,String subkey){
        String temp=data.substring(0,32);
        String L=data.substring(32,64);
        String R=MangularFunction(data.substring(32,64), subkey);
        R=xor(R,temp);
        return L+R;
    }

       //DECRPTION _ ROUND FOR INVERSING OPERATION
    public static String decription_round(String data,String subkey){
        String R=data.substring(0,32);
        String L=MangularFunction(R,subkey);
        L=xor(L,data.substring(32,64));
        return L+R;
    }

         //DECRIPTION METHOD
    public static String Decription(String cipher,String key){


        String plain=cipher;
        plain=Inital_permutaion_data(plain);

        plain=swap(plain);
        String[]subkey=keyGeneration(key);
        for(int i=15;i>=0;i--){
            plain=decription_round(plain, subkey[i]);
        }
        plain=inverse_permutation(plain);

        return plain;
    }

    //MAKING ENCRIPION

    public static String Encription(String data,String key) {
        String[] subkey = keyGeneration(key);
        data = Inital_permutaion_data(data);
        for (int i = 0; i < 16; i++) {
            data = round(data, subkey[i]);
        }
        data = swap(data);
        data = inverse_permutation(data);
        return data;
    }
}