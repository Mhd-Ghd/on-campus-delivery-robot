package mhd.ghd.usergui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText text1 ;
    EditText text2 ;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    Button btn;
    TextView textView;
    TextView textView2;

    int counts=0;
    boolean locked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

    }


    public void send (View view){
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        btn = findViewById(R.id.button);

        if (selectedRadioButtonId != -1) {
            selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedRbText = selectedRadioButton.getText().toString();
            int msg=-1;
            switch(selectedRadioButtonId){
                case R.id.a1:
                    msg=0;
                    break;
                case R.id.a2:
                    msg=1;
                    break;
                case R.id.a3:
                    msg=2;
                    break;
            }
            textView.setText("Navigating to "+selectedRbText);
            UserTCP sender = new UserTCP();
            sender.execute(String.format("%d",msg));
            radioGroup.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            if(!locked) {
                btn.setText("UNLOCK");
                locked = true;
            }else{
                btn.setText("START");
                radioGroup.setVisibility(View.VISIBLE);
                textView.setText("");
                textView2.setVisibility(View.VISIBLE);
                locked = false;
            }
        } else {
            counts++;
            if(counts==3){
                textView.setText("Maintenance Mode is ON.");
                UserTCP sender = new UserTCP();
                sender.execute(String.format("%d",-1));
                radioGroup.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                btn.setText("CANCEL");
            }else if(counts>3){
                textView.setText("Maintenance Mode is OFF.");
                UserTCP sender = new UserTCP();
                sender.execute(String.format("%d",-1));
                counts=0;
                radioGroup.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                btn.setText("START");
            }else {
                textView.setText("Please select a destination");
            }
        }






    }

}

