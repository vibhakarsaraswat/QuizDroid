package com.example.android.quizdroid;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    private LinearLayout Lilo;

    private RadioGroup radioGroupYer;
    private RadioGroup radioGroupDev;
    private RadioGroup radioGroupRel;

    private RadioButton rbYer;
    private RadioButton rbDev;
    private RadioButton rbRel;

    private CheckBox cbJava, cbC, cbChash, cbCpp;

    private EditText etKrnl;

    private Button sbm_btn;
    private Button rst_btn;

    // These int variables will be used to fetch the selected RadioButton from a RadioGroup
    int irb1, irb2, irb3;

    // These int variables will be used to select the Default Text Colors for RadioButtons
    int rbDefaultTextColor, cbDefaultTextColor, etDefaultTextColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding the Reset Button Id from UI and assigning it to Button Variable.
        rst_btn = (Button) findViewById(R.id.rst_button);

        // Disabling the RESET button until SUBMIT button is pressed.
        rst_btn.setClickable(false);
        rst_btn.setEnabled(false);

        // Finding the LinearLayout Id from UI and assigning it to Variable created above.
        Lilo = (LinearLayout) findViewById(R.id.activityLilo);

        // Setting the Focus to Lilo
        Lilo.setFocusable(true);
        Lilo.setFocusableInTouchMode(true);

        // Finding the Submit Button Id from UI and assigning it to Button Variable created above.
        sbm_btn = (Button) findViewById(R.id.sbm_button);

        // Finding the RadioGroup Id from UI and assigning it to RadioGroup Variable created above.
        radioGroupYer = (RadioGroup) findViewById(R.id.radioGroupYear);
        radioGroupDev = (RadioGroup) findViewById(R.id.radioGroupDeveloper);
        radioGroupRel = (RadioGroup) findViewById(R.id.radioGroupRelease);

        // getting the Default TextColor for radioButtons and Assigning it to a int Variable
        rbDefaultTextColor = ((RadioButton) radioGroupYer.findViewById(R.id.radioButton1)).getCurrentTextColor();

        // getting the Default TextColor for (any) CheckBox and Assigning it to a int Variable
        cbDefaultTextColor = ((CheckBox) findViewById(R.id.checkBox1)).getCurrentTextColor();

        /*
         * Finding the ID of CheckBoxes from UI and assigning it to Variables of type "CheckBox"
         */
        cbJava = (CheckBox) findViewById(R.id.checkBox1);
        cbC = (CheckBox) findViewById(R.id.checkBox2);
        cbChash = (CheckBox) findViewById(R.id.checkBox3);
        cbCpp = (CheckBox) findViewById(R.id.checkBox4);

        /*
         * Finding the EditText field from UI and assigning it to a Variable of "EditText" type and
         * getting the Default TextColor for EditText and assigning it to a Variable of "int"  type
         */
        etKrnl = (EditText) findViewById(R.id.editText);
        etDefaultTextColor = etKrnl.getCurrentTextColor();

        // Setting Events Listener for Submit Button
        sbm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * finding the View IDs of Selected RadioButtons from the RadioGroups
                 * Casting the found Views to RadioButtons and
                 * Assigning the ID Values to a RadioButtons created above
                 */
                rbYer = (RadioButton) radioGroupYer.findViewById(radioGroupYer.getCheckedRadioButtonId());
                rbDev = (RadioButton) radioGroupDev.findViewById(radioGroupDev.getCheckedRadioButtonId());
                rbRel = (RadioButton) radioGroupRel.findViewById(radioGroupRel.getCheckedRadioButtonId());

                // Checking that 1 RadioButton must be selected form each RadioGroup
                if ((radioGroupYer.indexOfChild(rbYer) == -1) ||
                        (radioGroupDev.indexOfChild(rbDev) == -1) ||
                        (radioGroupRel.indexOfChild(rbRel) == -1) ||
                        (!cbJava.isChecked() && !cbC.isChecked() && !cbCpp.isChecked() && !cbChash.isChecked()) ||
                        ((etKrnl.getText().toString().trim().length() == 0))) {
                    Toast.makeText(MainActivity.this, "You Must Select 1 Answer for each quiz \n and text box can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    /**
                     * Updating score if correct answer is selected
                     * otherwise highlighting the incorrect answer in YELLOW/RED and
                     * expected correct answer in GREEN
                     */
                    if (rbYer.getText().toString().equals("2003")) {
                        score++;
                    } else {
                        rbYer.setTextColor(Color.YELLOW);
                        rbYer.setBackgroundColor(Color.RED);
                        ((RadioButton) findViewById(R.id.radioButton2)).setBackgroundColor(Color.GREEN);
                    }
                    if (rbDev.getText().toString().equals("All of them")) {
                        score++;
                    } else {
                        rbDev.setTextColor(Color.YELLOW);
                        rbDev.setBackgroundColor(Color.RED);
                        ((RadioButton) findViewById(R.id.radioButton8)).setBackgroundColor(Color.GREEN);
                    }
                    if (rbRel.getText().toString().equals("Nougat")) {
                        score++;
                    } else {
                        rbRel.setTextColor(Color.YELLOW);
                        rbRel.setBackgroundColor(Color.RED);
                        ((RadioButton) findViewById(R.id.radioButton11)).setBackgroundColor(Color.GREEN);
                    }

                    /**
                     * Updating score if correct CheckBox/s (answer/s) are selected
                     * otherwise highlighting the incorrect answer in YELLOW/RED and
                     * expected correct answer in GREEN.
                     */
                    if (cbJava.isChecked() && cbC.isChecked() && cbCpp.isChecked() && !cbChash.isChecked()) {
                        score++;
                    }
                    if (!cbJava.isChecked()) {
                        cbJava.setBackgroundColor(Color.GREEN);
                    }
                    if (!cbC.isChecked()) {
                        cbC.setBackgroundColor(Color.GREEN);
                    }
                    if (!cbCpp.isChecked()) {
                        cbCpp.setBackgroundColor(Color.GREEN);
                    }
                    if (cbChash.isChecked()) {
                        cbChash.setTextColor(Color.YELLOW);
                        cbChash.setBackgroundColor(Color.RED);
                    }

                    /**
                     * Updating score if correct answers is entered in the EditText
                     * otherwise highlighting the incorrect answer in RED
                     */
                    if (etKrnl.getText().toString().trim().equalsIgnoreCase("linux")) {
                        score++;
                    } else {
                        etKrnl.setTextColor(Color.RED);
                        etKrnl.setTypeface(Typeface.DEFAULT_BOLD);
                    }

                    // Displaying the Final Quiz Score using a Toast message.
                    Toast.makeText(MainActivity.this, "Your Score is: " + score + "/5", Toast.LENGTH_LONG).show();

                    // Disabling the SUBMIT button once Final Score is displayed
                    sbm_btn.setClickable(false);
                    sbm_btn.setEnabled(false);
                    rst_btn.setClickable(true);
                    rst_btn.setEnabled(true);

                }
            }
        });

        // Resetting the Quiz
        // Unchecking all the RadioButtons in all the RadioGroups and Setting Score to Zero
        rst_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Fetching IDs of the selected RadioButtons from each RadioGroups
                irb1 = radioGroupYer.getCheckedRadioButtonId();
                irb2 = radioGroupDev.getCheckedRadioButtonId();
                irb3 = radioGroupRel.getCheckedRadioButtonId();

                // Unchecking the Checked RadioButtons from each radioGroups
                if (irb1 > 0) radioGroupYer.clearCheck();
                if (irb2 > 0) radioGroupDev.clearCheck();
                if (irb3 > 0) radioGroupRel.clearCheck();

                // Resetting the Default BackgroundColor for Incorrect RadioButton(answers)
                rbYer.setBackgroundColor(Color.WHITE);
                rbDev.setBackgroundColor(Color.WHITE);
                rbRel.setBackgroundColor(Color.WHITE);

                // Resetting the Default BackgroundColor for Correct (but not selected) RadioButton(answers)
                ((RadioButton) findViewById(R.id.radioButton2)).setBackgroundColor(Color.WHITE);
                ((RadioButton) findViewById(R.id.radioButton8)).setBackgroundColor(Color.WHITE);
                ((RadioButton) findViewById(R.id.radioButton11)).setBackgroundColor(Color.WHITE);

                // Resetting the Default TextColor for Incorrect RadioButton(answers)
                rbYer.setTextColor(rbDefaultTextColor);
                rbDev.setTextColor(rbDefaultTextColor);
                rbRel.setTextColor(rbDefaultTextColor);

                // Unchecking the Checked CheckBoxes
                if (cbJava.isChecked()) {
                    cbJava.setChecked(false);
                }
                if (cbC.isChecked()) {
                    cbC.setChecked(false);
                }
                if (cbChash.isChecked()) {
                    cbChash.setChecked(false);
                }
                if (cbCpp.isChecked()) {
                    cbCpp.setChecked(false);
                }

                // Resetting the Default BackgroundColor for Checkboxes
                cbJava.setBackgroundColor(Color.WHITE);
                cbC.setBackgroundColor(Color.WHITE);
                cbChash.setBackgroundColor(Color.WHITE);
                cbCpp.setBackgroundColor(Color.WHITE);

                // Resetting the Default TextColor for Incorrect CheckBox (answer)
                cbChash.setTextColor(cbDefaultTextColor);

                // Clearing the Text in EditTextBox
                etKrnl.setText(null);

                // Resetting the Default TextColor and TypeFace for EditText
                etKrnl.setTextColor(etDefaultTextColor);
                etKrnl.setTypeface(Typeface.DEFAULT);

                // Clearing the Focus from EditText
                etKrnl.clearFocus();

                // Resetting the score to zero
                score = 0;

                // Enabling the Submit Button
                sbm_btn.setClickable(true);
                sbm_btn.setEnabled(true);
                rst_btn.setClickable(false);
                rst_btn.setEnabled(false);
            }
        });
    }
}