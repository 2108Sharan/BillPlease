package sg.edu.rp.c346.id20011066.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Button splitBtn;
    Button resetBtn;
    EditText amount;
    EditText numOfPax;
    TextView discount;
    RadioGroup rgPayment;
    TextView total;
    TextView eachPays;
    ToggleButton service;
    ToggleButton gst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splitBtn = findViewById(R.id.buttonSplit);
        resetBtn =findViewById(R.id.buttonReset);
        amount = findViewById(R.id.editInputAmount);
        numOfPax = findViewById(R.id.editInputNumPax);
        discount = findViewById(R.id.textDiscount);
        rgPayment = findViewById(R.id.RadioGroupPayment);
        total = findViewById(R.id.textTotalBill);
        eachPays = findViewById(R.id.textSplitAmount);
        service = findViewById(R.id.tbService);
        gst = findViewById(R.id.tbGst);
        EditText editDiscount = findViewById(R.id.editTextDiscount);

        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && numOfPax.getText().toString().trim().length()!=0){
                    double newAmount = Double.parseDouble(amount.getText().toString());
                    if (service.isChecked() && !gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                    }
                    else if (!service.isChecked() && gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                    }
                    else if (service.isChecked() && gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    // Discount
                    if(discount.getText().toString().trim().length()!= 0) {
                        newAmount *= 1 - Double.parseDouble(editDiscount.getText().toString()) / 100;
                    }
                    total.setText(String.format("Total Bill: $%.2f", newAmount));
                    int numPerson = Integer.parseInt(numOfPax.getText().toString());

                    if(rgPayment.getCheckedRadioButtonId() == R.id.radioButtonCash){
                        eachPays.setText(String.format("Each pays: $%.2f in cash", newAmount/numPerson));
                    }
                    else{
                        eachPays.setText(String.format("Each pays: $%.2f via PayNow to 912345678", newAmount/numPerson));

                    }
                }
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numOfPax.setText("");
                service.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });
    }
}