package com.clinnection.wf.parser;

import org.junit.Test;

import static com.clinnection.wf.matcher.ProducesToken.producesToken;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.clinnection.wf.parser.WfLexer.*;

public class LexerTest {

  @Test
  public void equalsTest() {
    assertThat("==", producesToken(EQUALS));
  }
}
