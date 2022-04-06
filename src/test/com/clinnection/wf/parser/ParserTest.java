package com.clinnection.wf.parser;

import org.junit.Test;

import static com.clinnection.wf.matcher.UsesAllTokens.usesAllTokens;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParserTest {

  @Test
  public void integerExprTest() {
    assertThat("1 * 2", usesAllTokens("integerExpr"));
  }
}
