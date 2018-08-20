package com.example.pethoalpar.zxingexample;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
public class QR_Generator extends AppCompatActivity {
    ///=============//This Activity Call from First Activity//======================//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
        final Activity activity = this;
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("QR_Generator", "Cancelled scan");
                Toast.makeText(this, "Your proccess Cancelled.....", Toast.LENGTH_LONG).show();
                    super.onBackPressed();
                    startActivity(new Intent(QR_Generator.this, NavigationDrawer.class));
                    finish();
            } else {
                Log.e("QR_Generator",data.toString());
              //  Toast.makeText(this, "Scanned: 1:" + result.getContents(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(this, "Scanned: 2:" + String.valueOf((result.getContents()).hashCode()), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
//===================//For Redirect Show QR DAta Activity//==============================//
        if((result.getContents())!=null){
            Intent intent=new Intent(QR_Generator.this,Show_QR_Data.class);
                intent.putExtra("QR_Result",result.getContents());
            startActivity(intent);
        }
    }

}
