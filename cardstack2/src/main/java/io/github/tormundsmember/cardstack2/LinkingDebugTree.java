package io.github.tormundsmember.cardstack2;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

import timber.log.Timber;

public class LinkingDebugTree extends Timber.DebugTree {

  private static final int CALL_STACK_INDEX = 5;
  private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");

  @Override
  protected void log(int priority, String tag, @NonNull String message, Throwable t) {
    // DO NOT switch this to Thread.getCurrentThread().getStackTrace(). The test will pass
    // because Robolectric runs them on the JVM but on Android the elements are different.
    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
    if (stackTrace.length <= CALL_STACK_INDEX) {
      throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
    }
    String clazz = stackTrace[CALL_STACK_INDEX].getFileName();
    int lineNumber = stackTrace[CALL_STACK_INDEX].getLineNumber();
    message = ".(" + clazz + ":" + lineNumber + ")" + " - " + message;
    super.log(priority, tag, message, t);
  }
}