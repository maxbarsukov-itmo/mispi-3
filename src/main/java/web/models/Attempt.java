package web.models;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Named("attemptBean")
@SessionScoped
@Table(name="attempts")
public class Attempt implements Serializable {
  @Getter
  @ToString
  @AllArgsConstructor
  public static class Coordinates {
    private final double x;
    private final double y;
    private final double r;
    private final boolean result;
  }

  public Attempt(double x, double y, double r) {
    this.x = x;
    this.y = y;
    this.r = r;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", nullable=false, unique=true)
  private int id;

  @NotNull
  @Column(name="x", nullable=false)
  private double x;

  @NotNull
  @Column(name="y", nullable=false)
  private double y;

  @NotNull
  @Column(name="r", nullable=false)
  private double r;

  @NotNull
  @Column(name="result", nullable=false)
  private boolean result;

  @NotNull
  @Column(name="created_at", nullable=false)
  private Date createdAt;

  @NotNull
  @Column(name="execution_time", nullable=false)
  private Long executionTime;

  public Coordinates getCoordinates() {
    return new Coordinates(x, y, r, result);
  }
}
