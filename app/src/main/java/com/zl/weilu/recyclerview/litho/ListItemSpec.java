package com.zl.weilu.recyclerview.litho;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;

import static com.facebook.yoga.YogaEdge.ALL;

@LayoutSpec
public class ListItemSpec {

  @OnCreateLayout
  static Component onCreateLayout(
          ComponentContext c,
          @Prop float size,
          @Prop String title) {

    return Column.create(c)
        .paddingDip(ALL, 10)
        .backgroundColor(Color.WHITE)
        .child(
            Text.create(c)
                .text(title)
                .textColor(Color.parseColor("#999999"))
                .textSizeSp(size))
        .build();
  }
}