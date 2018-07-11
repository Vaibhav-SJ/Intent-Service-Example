package com.example.appmomos.intentserviceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.editText);



        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (editText.getText().toString().trim()) {
                    case "":
                        editText.setError("Mandatory");
                        break;
                    case "0":
                        editText.setError("Minimun value should be 1");
                        break;
                    default:
                        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                        intent.putExtra("count", editText.getText().toString().trim());
                        startService(intent);
                        break;
                }
            }
        });

    }
}
