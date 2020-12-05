package com.bumptech.glide.load.engine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.cache.SafeKeyGenerator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.tests.KeyTester;
import com.bumptech.glide.tests.Util.WriteDigest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4.class)
public class DataCacheKeyTest {
  @Rule public final KeyTester keyTester = new KeyTester();

  @Mock private Key firstKey;
  @Mock private Key firstSignature;
  @Mock private Key secondKey;
  @Mock private Key secondSignature;

  @Before
  public void setUp() throws UnsupportedEncodingException {
    MockitoAnnotations.initMocks(this);
    doAnswer(new WriteDigest("firstKey"))
        .when(firstKey)
        .updateDiskCacheKey(any(MessageDigest.class));
    doAnswer(new WriteDigest("firstSignature"))
        .when(firstSignature)
        .updateDiskCacheKey(any(MessageDigest.class));
    doAnswer(new WriteDigest("secondKey"))
        .when(secondKey)
        .updateDiskCacheKey(any(MessageDigest.class));
    doAnswer(new WriteDigest("secondSignature"))
        .when(secondSignature)
        .updateDiskCacheKey(any(MessageDigest.class));
  }

  @Test
  public void testEqualsHashCodeDigest() throws NoSuchAlgorithmException {
    keyTester
        .addEquivalenceGroup(
            new DataCacheKey(firstKey, firstSignature), new DataCacheKey(firstKey, firstSignature))
        .addEquivalenceGroup(new DataCacheKey(firstKey, secondSignature))
        .addEquivalenceGroup(new DataCacheKey(secondKey, firstSignature))
        .addEquivalenceGroup(new DataCacheKey(secondKey, secondSignature))
        .addRegressionTest(
            new DataCacheKey(firstKey, firstSignature),
            "801d7440d65a0e7c9ad0097d417f346dac4d4c4d5630724110fa3f3fe66236d9")
        .test();
  }

  @Test
  public void test() {
    SafeKeyGenerator generator = new SafeKeyGenerator();
    String url = "http://static2.miniviapp.com/image/2020-02-15/thumb_5e47bda3626ff30001f1f7ff";
    String key = generator.getSafeKey(new DataCacheKey(new GlideUrl(url), EmptySignature.obtain()));
    System.out.println("Data cache key is >> "+key);
//    9f728add40778273e30efbe24b393d5a64e064bf3ece2c54feeb93bb6d55463f
  }
}
