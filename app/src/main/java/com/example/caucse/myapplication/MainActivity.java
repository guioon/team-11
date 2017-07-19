package caucse.example.com;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.caucse.myapplication.R;

public class OneTo25Activity extends Activity implements OnClickListener {
         Button btn[];
         Button startBtn;
         int[] randomArr = new int[25];
         static int count = 1;
         TextView textView;
         Chronometer timer;
         FrameLayout frame;

                 // static int heightCnt = 1;
                 // int height = getLcdSIzeHeight()/100;
                 @Override
         public void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);

                 timer = (Chronometer) findViewById(R.id.chronometer1);
                 timer.setTextSize(getLcdSIzeHeight() / 15);
                 timer.setHeight(getLcdSIzeHeight() / 9);
                 frame = (FrameLayout) findViewById(R.id.frame);
                 // LinearLayout linear = (LinearLayout)findViewById(R.id.linear);
                 LinearLayout linear1 = (LinearLayout) findViewById(R.id.linear1);
                 LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
                 LinearLayout linear3 = (LinearLayout) findViewById(R.id.linear3);
                 LinearLayout linear4 = (LinearLayout) findViewById(R.id.linear4);
                 LinearLayout linear5 = (LinearLayout) findViewById(R.id.linear5);

                 LayoutParams parambtn = new LinearLayout.LayoutParams(
                                 LinearLayout.LayoutParams.FILL_PARENT,
                                 LinearLayout.LayoutParams.FILL_PARENT);
                 parambtn.weight = 1.0f;

                 btn = new Button[25];
                 startBtn = (Button) findViewById(R.id.startBtn);
                 startBtn.setHeight(getLcdSIzeHeight() / 9);
                 startBtn.setTextSize(getLcdSIzeHeight() / 15);

                 for (int i = 0; i < 25; i++) {
                         btn[i] = new Button(this);
                         btn[i].setText(" ");
                         btn[i].setTextSize(getLcdSIzeHeight() / 15);
                         btn[i].setId(i);
                         btn[i].setHeight(getLcdSIzeHeight() / 7);
                         btn[i].setOnClickListener(this);
                         btn[i].setEnabled(false);
                         if (i < 5)
                                 linear1.addView(btn[i], parambtn);
                         else if (i < 10)
                                 linear2.addView(btn[i], parambtn);
                         else if (i < 15)
                                 linear3.addView(btn[i], parambtn);
                         else if (i < 20)
                                 linear4.addView(btn[i], parambtn);
                         else if (i < 25)
                                 linear5.addView(btn[i], parambtn);
                     }
                 startBtn.setOnClickListener(new OnClickListener() {
                         @Override
                         public void onClick(View v) {
                                 randomArr = generate();
                                 startBtn.setEnabled(false);
                                 for (int i = 0; i < btn.length; i++) {
                                         btn[i].setEnabled(true);
                                         btn[i].setText("" + randomArr[i]);
                                         btn[i].setTag(btn[i].getText());
                                         btn[i].setAnimation(null);
                                     }
                                 timer.setBase(SystemClock.elapsedRealtime());
                                 timer.start();

                                 Animation anim = AnimationUtils.loadAnimation(
                                                 getApplicationContext(), R.anim.translate);
                                 frame.setAnimation(anim);
                             }
                     });
             }

                 public int[] generate() {
                 int[] result = new int[25];
                 int count = 0;

                 while (count != 25) {
                         boolean test = true;
                         int r = (int) (Math.random() * 25 + 1);
                         for (int i = 0; i < result.length; i++) {
                                 if (result[i] == r) {
                                         test = false;
                                         break;
                                     }
                             }
                         if (test) {
                                 result[count++] = r;
                             }
                     }
                 return result;
             }

                 @Override
         public void onClick(View v) {
                 Object tag = v.getTag();
                 if (tag.equals(count + "")) {

                         Animation anim = AnimationUtils.loadAnimation(
                                         getApplicationContext(), R.anim.scale);
                         v.startAnimation(anim);

                         count++;
                         if (count == 26) {
                                 timer.stop();
                                 count = 1;
                                 startBtn.setEnabled(true);
                                 startBtn.setText("다시시작");
                             }
                     }
             }

                 public int getLcdSIzeHeight() {
                 return ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                         .getDefaultDisplay().getHeight();
             }

                 public int getLcdSIzeWidth() {
                 return ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                         .getDefaultDisplay().getWidth();
             }
     }
