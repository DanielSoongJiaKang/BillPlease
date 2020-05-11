package sg.edu.rp.c346.id19045346.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText enteramount;
    EditText enterpax;
    ToggleButton svs;
    ToggleButton gst;
    EditText enterdiscount;
    TextView totalbill;
    TextView splitpay;
    ToggleButton buttonsplit;
    Button buttonreset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteramount = findViewById(R.id.amount);
        enterpax = findViewById(R.id.pax);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        enterdiscount = findViewById(R.id.discount);
        totalbill = findViewById(R.id.totalbill);
        splitpay = findViewById(R.id.textsplit);
        buttonsplit = findViewById(R.id.bsplitpax);
        buttonreset = findViewById(R.id.reset);



    buttonsplit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double newamt = 0.0;

            if(enteramount.getText().toString().trim().length()!=0 && enterpax.getText().toString().trim().length()!=0) {
                if(!svs.isChecked() && !gst.isChecked()) {
                    newamt = Double.parseDouble(enteramount.getText().toString());
                }
                else if(svs.isChecked() && !gst.isChecked()) {
                    newamt = Double.parseDouble(enteramount.getText().toString()) * 1.1;
                }
                else if (!svs.isChecked() && gst.isChecked()) {
                    newamt = Double.parseDouble(enteramount.getText().toString()) * 1.07;
                }
                else {
                    newamt = Double.parseDouble(enteramount.getText().toString()) * 1.17;
                }

                //Discount
                if(enterdiscount.getText().toString().trim().length()!= 0) {
                    newamt *= 1 - Double.parseDouble(enterdiscount.getText().toString()) / 100;
                }
                else {
                    Toast.makeText(MainActivity.this,"Please fill in discount",Toast.LENGTH_SHORT).show();

                }

                totalbill.setText("$" + String.format("%.2f",newamt));
                int numPerson = Integer.parseInt(enterpax.getText().toString());
                if(numPerson!= 1) {
                    splitpay.setText("$" + String.format("%.2f",newamt/numPerson));

                }
                else {
                    splitpay.setText("$" + newamt);
                }



            }
            else {
                Toast.makeText(MainActivity.this,"Please fill in blank space",Toast.LENGTH_SHORT).show();
            }

            buttonreset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enteramount.setText("");
                    enterpax.setText("");
                    svs.setChecked(false);
                    gst.setChecked(false);
                    enterdiscount.setText("");
                }
            });


        }
    });















    }


}
