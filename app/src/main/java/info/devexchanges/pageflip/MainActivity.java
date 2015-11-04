package info.devexchanges.pageflip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.aphidmobile.flip.FlipViewController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FlipViewController flipViewController;
    private FlipperAdapter adapter;
    private ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipViewController = (FlipViewController)findViewById(R.id.flip_view);
        stringArrayList = new ArrayList<>();
        readDataFromAssets();

        //create and attach adapter to flipper view
        adapter = new FlipperAdapter(this, stringArrayList);
        flipViewController.setAdapter(adapter);
    }

    private void readDataFromAssets() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("loremipsum.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                stringArrayList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
