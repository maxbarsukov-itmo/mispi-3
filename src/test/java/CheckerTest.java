import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import web.dto.AttemptDto;
import web.services.Checker;

public class CheckerTest {
  private Checker checker;

  @Before
  public void setUp() {
    checker = new Checker();
  }

  @Test
  public void testHitInArea() {
    assertTrue(checker.attemptIsInArea(new AttemptDto(0.1, 0.1, 1.0)));
    assertTrue(checker.attemptIsInArea(new AttemptDto(0.25, 1, 2.0)));
    assertFalse(checker.attemptIsInArea(new AttemptDto(0.1, -0.2, 1.0)));
  }

  @Test
  public void testHitInRectangle() {
    // Test cases where the attempt should hit within the rectangle
    assertTrue(checker.attemptIsInRect(new AttemptDto(-0.5, 0.25, 1.0)));
    assertTrue(checker.attemptIsInRect(new AttemptDto(-1.0, 0.5, 2.0)));

    // Test cases where the attempt should miss the rectangle
    assertFalse(checker.attemptIsInRect(new AttemptDto(-1.1, 0.6, 1.0)));
    assertFalse(checker.attemptIsInRect(new AttemptDto(0.1, 0.1, 1.0)));
  }

  @Test
  public void testHitInSector() {
    // Test cases where the attempt should hit within the sector
    assertTrue(checker.attemptIsInSector(new AttemptDto(-0.5, -0.5, 2.0)));
    assertTrue(checker.attemptIsInSector(new AttemptDto(-0.1, -0.1, 0.5)));

    // Test cases where the attempt should miss the sector
    assertFalse(checker.attemptIsInSector(new AttemptDto(-1.1, -1.1, 3.0)));
    assertFalse(checker.attemptIsInSector(new AttemptDto(0.1, -0.2, 1.0)));
  }

  @Test
  public void testHitInTriangle() {
    // Test cases where the attempt should hit within the triangle
    assertTrue(checker.attemptIsInTriangle(new AttemptDto(0.1, 0.1, 1.0)));
    assertTrue(checker.attemptIsInTriangle(new AttemptDto(0.25, 0.5, 1.0)));

    // Test cases where the attempt should miss the triangle
    assertFalse(checker.attemptIsInTriangle(new AttemptDto(0.6, 0.6, 1.0)));
    assertFalse(checker.attemptIsInTriangle(new AttemptDto(-0.1, 0.2, 1.0)));
  }
}
