package e001.zahraa.net.file;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyActivity extends Activity implements View.OnClickListener {
    ProgressDialog dialog;
    EditText inDataText ;
    TextView outDataView;
    int i;
    FileOutputStream fos;
/////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button load =(Button)findViewById(R.id.load_b);
        Button save =(Button)findViewById(R.id.save_b);

        inDataText =(EditText) findViewById(R.id.text);
        outDataView = (TextView)findViewById(R.id.view);

        load.setOnClickListener(this);
        save.setOnClickListener(this);

    }
/////////////////////////// DialogDisiplay() //////////////////////////////////////////
    private void DialogDisiplay() {

        dialog =new ProgressDialog(MyActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.show();

       /* for (i = 0; i < 20; i++) {
            try {
                dialog.incrementProgressBy(5); Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
              */ new Thread() {
                    public void run() {
                        for (i = 0; i < 20; i++) {
                            try {
                                   dialog.incrementProgressBy(5);
                                   sleep(55);
                                 }
                            catch (Exception e) {}
                            }
                        dialog.dismiss();
                                  }
                                  }.start();
             }

/////////////////// onClick  ///////////////////////////////////
    @Override
    public void onClick(View view) {
        switch (view.getId())

        {
                     /////////save_b///////////////////
            case R.id.save_b:
                String data = inDataText.getText().toString();
                try
                {
                    fos= openFileOutput("newFile", Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                }

                 catch (IOException e) {
                    e.printStackTrace();
                }
                break;

                     ///////////////  load_b ///////////////

            case R.id.load_b :
                String collected=null ;
                FileInputStream fis=null;

                DialogDisiplay();

                try
                {
                    fis=openFileInput("newFile");
                    byte arry []=new byte[fis.available()];
                    while (fis.read(arry)!= -1)
                    {
                        collected=new String(arry);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 finally {
                    try {
                        fis.close();
                        outDataView.setText(collected);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    //////////////////////  END  //////////////////////////////////////



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
