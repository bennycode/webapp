package com.welovecoding.data.base;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;

public class SlugifyTest {

  @Rule
  public TestName name = new TestName();

  private String stripRandom(String withRandom) {
    return withRandom.subSequence(0, withRandom.length() - 7).toString();
  }

  @Test
  public void testBasic() {
    System.out.println(name.getMethodName());
    String s = "Hello world";
    assertEquals("Hello-world", stripRandom(Slugify.slugify(s)));
  }

  @Test
  public void testSpaces() {
    System.out.println(name.getMethodName());
    String s = "\tHello  \t world ";
    assertEquals("Hello-world", stripRandom(Slugify.slugify(s)));
  }

  @Test
  public void testPrintableASCII() {
    System.out.println(name.getMethodName());
    String s = " !\"#$%&'()*+,-./0123456789:;<=>?@"
      + "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`"
      + "abcdefghijklmnopqrstuvwxyz{|}~";

    String expected = "sharp+-0123456789"
      + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      + "abcdefghijklmnopqrstuvwxyz";

    assertEquals(expected, stripRandom(Slugify.slugify(s)));
  }

  @Test
  public void testExtendedASCII() {
    System.out.println(name.getMethodName());
    String s = "€‚ƒ„…†‡ˆ‰Š‹ŒŽ‘”•–—˜™š›œžŸ¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶"
      + "·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæç"
      + "èéêëìíîïðñòóôõö÷øùúûüýþÿ";

    String expected = "SZszYAAAAAeACEEEEIIIINOOOOOeUUUUeYssaaaaaeaceeeeiiiinoooooeuuuueyy";

    assertEquals(expected, stripRandom(Slugify.slugify(s)));
  }

  @Test
  public void testReplacements() {
    System.out.println(name.getMethodName());
    String s = "ÄÖÜäöüß";
    assertEquals("AeOeUeaeoeuess", stripRandom(Slugify.slugify(s)));
  }

}
