import java.time.LocalDateTime;

public class User {

  private int id;

  private LocalDateTime timeRegistration;

  public User(int id, LocalDateTime timeRegistration) {
    this.id = id;
    this.timeRegistration = timeRegistration;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getTimeRegistration() {
    return timeRegistration;
  }

  public void setTimeRegistration(LocalDateTime timeRegistration) {
    this.timeRegistration = timeRegistration;
  }
}
