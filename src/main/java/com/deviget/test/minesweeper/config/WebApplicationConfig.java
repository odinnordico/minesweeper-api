package com.deviget.test.minesweeper.config;

import java.util.EnumSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

@Configuration
public class WebApplicationConfig implements WebApplicationInitializer {

  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    servletContext.getSessionCookieConfig().setHttpOnly(true);
    servletContext.getSessionCookieConfig().setSecure(true);
  }
}
