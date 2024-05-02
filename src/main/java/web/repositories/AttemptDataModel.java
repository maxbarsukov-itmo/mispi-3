package web.repositories;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Map;

import web.models.Attempt;

@Named("attemptsList")
@SessionScoped
public class AttemptDataModel extends LazyDataModel<Attempt> {
  @Inject
  private AttemptRepository service;

  @Override
  public int count(Map<String, FilterMeta> map) {
    return service.getAttemptsCount();
  }

  @Override
  public List<Attempt> load(int first, int pageSize, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
    return service.getAttemptsList(first, pageSize);
  }
}
