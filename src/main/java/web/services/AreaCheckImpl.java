package web.services;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;

import web.models.Attempt;

@Named("areaCheck")
@SessionScoped
@AreaCheckQualifier
public class AreaCheckImpl implements AreaCheck, Serializable {
  @Override
  public void checkHit(Attempt attemptBean) {
    long startTime = System.nanoTime();
    attemptBean.setCreatedAt(new Date(System.currentTimeMillis()));

    boolean hit = attemptIsInArea(attemptBean);
    attemptBean.setResult(hit);
    attemptBean.setExecutionTime(System.nanoTime() - startTime);
  }

  private boolean attemptIsInArea(Attempt attemptBean) {
    var x = attemptBean.getX();
    var y = attemptBean.getY();
    var r = attemptBean.getR();
    return attemptIsInRect(x, y, r) || attemptIsInTriangle(x, y, r) || attemptIsInSector(x, y, r);
  }

  private boolean attemptIsInRect(double x, double y, double r) {
    return (x <= 0 && y >= 0) && (x >= -r && y <= r / 2);
  }

  private boolean attemptIsInSector(double x, double y, double r) {
    return (x <= 0 && y <= 0) && (x*x + y*y <= r*r / 4);
  }

  private boolean attemptIsInTriangle(double x, double y, double r) {
    return (x >= 0 && y >= 0) && (x <= r / 2) && (y <= r) && (2*x + y <= r);
  }
}
