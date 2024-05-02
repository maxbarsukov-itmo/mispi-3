package web.repositories;

import web.models.Attempt;
import web.services.*;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named("attemptRepository")
@SessionScoped
public class AttemptRepository implements Serializable {
  private static final int LATEST_ATTEMPTS_COUNT = 10;

  @PersistenceContext
  private EntityManager entityManager;

  @Inject
  @AreaCheckQualifier
  private AreaCheck areaCheck;

  public List<Attempt> getAttemptsList(int start, int count) {
    return entityManager.createQuery("select attempt from Attempt attempt", Attempt.class)
      .setFirstResult(start).setMaxResults(count).getResultList();
  }

  public List<Attempt> getLatestAttemptsList() {
    int attemptsCount = getAttemptsCount();
    int firstResultIndex = Math.max(attemptsCount - LATEST_ATTEMPTS_COUNT, 0);
    return  entityManager.createQuery("select attempt From Attempt attempt", Attempt.class)
      .setFirstResult(firstResultIndex).setMaxResults(LATEST_ATTEMPTS_COUNT).getResultList();
  }

  @Transactional
  public Attempt addAttempt(Attempt attempt) {
    areaCheck.checkHit(attempt);
    entityManager.merge(attempt);
    entityManager.flush();
    return attempt;
  }

  public int getAttemptsCount() {
    return entityManager.createQuery("select count(*) from Attempt", Number.class).getSingleResult().intValue();
  }

  @Transactional
  public void clearAttempts() {
    entityManager.createQuery("delete from Attempt").executeUpdate();
  }

  @Transactional
  public void addAttemptFromJsParams(int currentR) {
    final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

    try {
      var x = Double.parseDouble(params.get("x"));
      var y = Double.parseDouble(params.get("y"));
      var graphR = Double.parseDouble(params.get("r"));

      final Attempt attemptBean = new Attempt(
        x / graphR * currentR,
        y / graphR * currentR,
        currentR
      );
      addAttempt(attemptBean);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  public String collectToJson(Function<? super Attempt, Double> getter) {
    return new Gson().toJson(getLatestAttemptsList().stream().map(getter).collect(Collectors.toList()));
  }

  public String getX() {
    return collectToJson(Attempt::getX);
  }

  public String getY() {
    return collectToJson(Attempt::getY);
  }

  public String getR() {
    return collectToJson(Attempt::getR);
  }

  public String getHit() {
    return new Gson().toJson(getLatestAttemptsList().stream().map(Attempt::isResult).collect(Collectors.toList()));
  }

  public String getPointsCoordinates() {
    return new Gson().toJson(
      getLatestAttemptsList().stream().map(Attempt::getCoordinates).collect(Collectors.toList())
    );
  }
}
