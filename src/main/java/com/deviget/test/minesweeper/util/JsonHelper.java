package com.deviget.test.minesweeper.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;

public class JsonHelper {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static String serialize(final Integer[] integers) {
    try {
      return MAPPER.writeValueAsString(integers);
    } catch (final JsonProcessingException e) {
      return "{}";
    }
  }

  public static Integer[] deserialize(final String json) {
    List<Integer> integers;
    try {
      integers = MAPPER.readValue(json, new TypeReference<List<Integer>>() {
      });
    } catch (final JsonProcessingException e) {
      integers = Collections.emptyList();
    }
    return integers.toArray(Integer[]::new);
  }
}
