package com.agnt45.revaplacement.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.agnt45.revaplacement.R;

public class IconScreen extends AppCompatActivity {

    int choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_screen);
        Intent intent = this.getIntent();
        choice = intent.getIntExtra("iconFilter:",10);
        Toast.makeText(IconScreen.this,String.valueOf(choice),Toast.LENGTH_SHORT).show();
        switch (choice){
            case 0:{
                
            }case 1:{

            }case 2:{

            }case 3:{

            }case 4:{

            }case 5:{

            }case 6:{

            }case 7:{

            }case 8:{

            }default:{

            }
        }
    }
}
