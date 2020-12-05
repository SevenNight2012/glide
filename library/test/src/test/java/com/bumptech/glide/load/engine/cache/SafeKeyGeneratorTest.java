package com.bumptech.glide.load.engine.cache;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.model.GlideUrl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 18)
public class SafeKeyGeneratorTest {
  private SafeKeyGenerator keyGenerator;
  private int nextId;

  @Before
  public void setUp() {
    nextId = 0;
    keyGenerator = new SafeKeyGenerator();
  }

  @Test
  public void testKeysAreValidForDiskCache() {
    final Pattern diskCacheRegex = Pattern.compile("[a-z0-9_-]{64}");
    for (int i = 0; i < 1000; i++) {
      String key = getRandomKeyFromGenerator();
      Matcher matcher = diskCacheRegex.matcher(key);
      assertTrue(key, matcher.matches());
    }
  }

  private String getRandomKeyFromGenerator() {
    return keyGenerator.getSafeKey(new MockKey(getNextId()));
  }

  private String getNextId() {
    return String.valueOf(nextId++);
  }

  private static final class MockKey implements Key {
    private final String id;

    MockKey(String id) {
      this.id = id;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
      messageDigest.update(id.getBytes(CHARSET));
    }
  }

  @Test
  public void printKey() {
    String url = "http://static2.miniviapp.com/image/2020-02-15/thumb_5e47bda3626ff30001f1f7ff";
    String key = keyGenerator.getSafeKey(new GlideUrl(url));
    System.out.println("key is  >> " + key);
  }
}
