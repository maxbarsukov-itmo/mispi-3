package web.services;

import web.dto.AttemptDto;

public class Checker {
  public boolean attemptIsInArea(AttemptDto dto) {
    return attemptIsInRect(dto) || attemptIsInTriangle(dto) || attemptIsInSector(dto);
  }

  public boolean attemptIsInRect(AttemptDto dto) {
    return (dto.getX() <= 0 && dto.getY() >= 0)
      && (dto.getX() >= -dto.getR() && dto.getY() <= dto.getR() / 2);
  }

  public boolean attemptIsInSector(AttemptDto dto) {
    return (dto.getX() <= 0 && dto.getY() <= 0)
      && (dto.getX()*dto.getX() + dto.getY()*dto.getY() <= dto.getR()*dto.getR() / 4);
  }

  public boolean attemptIsInTriangle(AttemptDto dto) {
    return (dto.getX() >= 0 && dto.getY() >= 0)
      && (dto.getX() <= dto.getR() / 2)
      && (dto.getY() <= dto.getR()) && (2*dto.getX() + dto.getY() <= dto.getR());
  }
}
