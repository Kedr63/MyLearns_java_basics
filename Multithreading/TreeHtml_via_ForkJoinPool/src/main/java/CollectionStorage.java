import java.util.HashSet;
import java.util.Set;

public class CollectionStorage {

  protected static Set<String> setString = new HashSet<>();
  protected static Set<String> setTitle = new HashSet<>();

  public static Set<String> getSetString() {
    return setString;
  }

  public static Set<String> getSetTitle() {
    return setTitle;
  }

  protected static void checkingAndAddToSetTitle(String titleString) {
    if (setTitle.contains(titleString)) {
      throw new IllegalStateException(
          "Такая страница с тэгом title уже обработана: " + titleString);
    } else {
      setTitle.add(titleString);
    }
  }
}
