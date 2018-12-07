package io.github.tormundsmember.a2048;

import android.content.res.Resources;

/**
 * Copyright (c) 2015 Schneider Holding Wirtschaftsprüfungsgesellschaft mbH - All rights reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Dominik Gudic on 02.11.2016.
 */

public class DipConversionUtil {

  public static int pxToDp(int px) {
    return (int) (px / Resources.getSystem().getDisplayMetrics().density);
  }

  public static int dpToPx(int dp) {
    return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
  }

  public static float dpToPx(float dp) {
    return dp * Resources.getSystem().getDisplayMetrics().density;
  }

  public static float spToPx(float sp) {
    return sp * Resources.getSystem().getDisplayMetrics().scaledDensity;
  }
}
