package com.zl.weilu.recyclerview.litho;

import android.os.Bundle;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 使用Litho实例
 * @Author: weilu
 * @Time: 2019/05/13 0013 16:44.
 */
public class LithoActivity extends AppCompatActivity {
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext context = new ComponentContext(this);

        final Component component = RecyclerCollectionComponent.create(context)
                        .disablePTR(true)
                        .section(ListSection.create(new SectionContext(context)).build())
                        .build();

        setContentView(LithoView.create(context, component));
    }

}
