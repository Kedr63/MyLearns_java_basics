package main;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
  @RequestMapping("/")
  public String index(){
    return (new Date().toString());
  }

  /**  @RequestMapping - это путь к тому запросу, по которому должен вызываться этот метод. Здесь метод
   *  будет вызываться в корне сайта. Если в браузере будем запрашивать первую страницу нашего
   *  сайта: http://localhost:8080/
   *  то будет вызываться этот метод и будет печататься дата текущая в браузере по ссылке http://localhost:8080 */

}
