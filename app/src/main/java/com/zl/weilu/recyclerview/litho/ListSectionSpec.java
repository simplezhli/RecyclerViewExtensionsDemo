package com.zl.weilu.recyclerview.litho;

import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.SingleComponentSection;

import java.util.Random;

@GroupSectionSpec
public class ListSectionSpec {

  @OnCreateChildren
  static Children onCreateChildren(final SectionContext c) {
    Children.Builder builder = Children.create();
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz1234567890.".toCharArray();
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder();
    float size;
    for (int i = 0; i < 1000; i++) {
      size = 10f;
      if (i % 2 == 0){
        size = 12f;
      }
      if (i % 3 == 0){
        size = 14f;
      }
      if (i % 5 == 0){
        size = 16f;
      }
      stringBuilder.delete(0, stringBuilder.length());
      for (int j = 0; j < 800; j++){
        stringBuilder.append(alphabet[random.nextInt(37)]);
      }
      builder.child(SingleComponentSection.create(c)
                      .key(String.valueOf(i))
                      .component(ListItem.create(c)
                         .size(size)
                         .title(stringBuilder.toString())
                         .build()));
    }
    return builder.build();
  }
}