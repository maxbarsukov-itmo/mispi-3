package web.services;

import java.io.Serializable;

import web.models.Attempt;

public interface AreaCheck extends Serializable {
  void checkHit(Attempt attemptBean);
}
