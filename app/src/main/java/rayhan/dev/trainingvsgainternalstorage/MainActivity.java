package rayhan.dev.trainingvsgainternalstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "datafile.txt";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textHasil);
    }

    public void onClickCreate(View v){
        File file = new File(getFilesDir(),FILENAME);
        String isiFile = "INI DATA ISINYA TULISAN";


        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            Log.e("ERROR","FILE BERMASALAH");
        }
    }

    public void onClickRead(View v){

        File file = new File(getFilesDir(),FILENAME);

        if (file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }

            } catch (FileNotFoundException e) {
                Log.e("ERROR","Error baca file");
            } catch (IOException e) {
                Log.e("ERROR", "Error baca bufer");
            }
            String dataText = text.toString();
            textView.setText(dataText);


        }
    }

    public void onClickCreateExternal(View v){

        File file = new File(getFilesDir(),FILENAME);

        if (file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }

            } catch (FileNotFoundException e) {
                Log.e("ERROR","Error baca file");
            } catch (IOException e) {
                Log.e("ERROR", "Error baca bufer");
            }
            String dataText = text.toString();
            textView.setText(dataText);


        }
    }

    public void onClickReadExternal(View v){

        if (Build.VERSION.SDK_INT >=23 ){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }
        }

        String state = Environment.getExternalStorageState();

        if(!state.equals(Environment.MEDIA_MOUNTED)){
            Log.i("INFO STATUS", "SELESAI");
            return;
        }
        Log.i("INFO STATUS", "LANJUT");
        File file = new File(Environment.getExternalStorageState(),FILENAME);


        if (file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }

            } catch (FileNotFoundException e) {
                Log.e("ERROR","Error baca file");
            } catch (IOException e) {
                Log.e("ERROR", "Error baca bufer");
            }
            String dataText = text.toString();
            textView.setText(dataText);


        }
    }

}
