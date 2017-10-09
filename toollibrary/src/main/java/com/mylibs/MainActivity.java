package com.mylibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.toollibrary.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;


public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CAPTURE_CODE = 1;

    private TextView mTv;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {

        mTv = (TextView) findViewById(R.id.id_text);

        mBtn = (Button) findViewById(R.id.id_scan);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CAPTURE_CODE);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK){
            return;
        }
        switch (requestCode){
            case REQUEST_CAPTURE_CODE:
                String result = data.getExtras().getString(CaptureActivity.SCAN_RESULT);
                mTv.setText(result);
                break;
            default:
                break;
        }



    }
}
