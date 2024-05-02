package web.services;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;

import web.dto.AttemptDto;
import web.models.Attempt;

@Named("areaCheck")
@SessionScoped
@AreaCheckQualifier
public class AreaCheckImpl implements AreaCheck, Serializable {
  @Override
  public void checkHit(Attempt attemptBean) {
    long startTime = System.nanoTime();
    attemptBean.setCreatedAt(new Date(System.currentTimeMillis()));

    boolean hit = new Checker()
      .attemptIsInArea(
        new AttemptDto(attemptBean.getX(), attemptBean.getY(), attemptBean.getR()
      )
    );

    attemptBean.setResult(hit);
    attemptBean.setExecutionTime(System.nanoTime() - startTime);
  }
}
