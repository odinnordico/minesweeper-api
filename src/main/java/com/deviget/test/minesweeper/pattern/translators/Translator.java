package com.deviget.test.minesweeper.pattern.translators;

public interface Translator<I, O> {

  O to(I input);

  I from(O output);
}
