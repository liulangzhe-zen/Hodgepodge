package com.basics.module_view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basics.module_view.widget.CircleButton;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-20 13:30
 * @Version: 1.0
 * @Description: java类作用描述
 */
public class ViewMainActivity extends AppCompatActivity {

    private CircleButton mCircleButtonGray;
    private CircleButton mCircleButtonRed;
    private static int number = 69;
    private float angle = 0;
    private float EachAngle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity_main);

        mCircleButtonRed = (CircleButton) findViewById(R.id.btn_circle_red);
        mCircleButtonRed.setText(number+"");

        //必须要将360*1.0 / number转为float或者double，这样可以避免因为取整的问题而导致没有完全平分360度
        EachAngle = Float.parseFloat(String.valueOf(360*1.0 / number));

        mCircleButtonRed.setOnCirclebuttonClickListener(new CircleButton.CirclebuttonClickListener() {
            @Override
            public void circleButtonClick() {
                mCircleButtonRed.setText(--number+"");
                angle += EachAngle;
                System.out.println(angle);
                mCircleButtonRed.setAngle(angle);
            }
        });

    }
}
