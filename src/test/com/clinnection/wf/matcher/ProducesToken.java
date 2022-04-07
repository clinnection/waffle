package com.clinnection.wf.matcher;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import com.clinnection.wf.parser.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.clinnection.wf.parser.WfLexer.VOCABULARY;

public class ProducesToken extends TypeSafeMatcher<String> {

  private String source;
  private final Integer expected;
  private List<Integer> actual;

  private ProducesToken(Integer expected) {
    this.expected = expected;
  }

  @Override
  protected boolean matchesSafely(String source) {
    this.source = source;

    WfLexer lexer = new WfLexer(CharStreams.fromString(source));
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    tokenStream.fill();

    List<Token> tokens = tokenStream.getTokens().subList(0, tokenStream.size() - 1);

    this.actual = tokens.stream().map(Token::getType).collect(Collectors.toList());

    return tokens.size() == 1 && tokens.get(0).getType() == this.expected;
  }

  @Override
  public void describeTo(Description description) {
    String message = String.format("  %s", VOCABULARY.getSymbolicName(this.expected));

    description.appendText(message);
  }

  @Override
  protected void describeMismatchSafely(String item, Description description) {
    String message = String.format("  %s\nFor input   %s",
        this.actual.stream().map(VOCABULARY::getSymbolicName).collect(Collectors.toList()),
        this.source);

    description.appendText(message);
  }

  public static Matcher<String> producesToken(Integer actual) {
    return new ProducesToken(actual);
  }
}
