public class Logirovanie {
    // Логирование - записывае в файл информации об ошибках и о текущей работе программы

    // Упакуем проект в jar файл сначала, чтоб упаковался надо добавить плагин /maven-assembly-plugin/
    /*Текст для плагинов для вставки в pom.xml в тег <plugins>...</plugins>


<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
</plugin>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <archive>
            <manifest>
                <addClasspath>true</addClasspath>
                <mainClass>Main</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
</plugin>*/
    // в файле pom.xml в проекте SPB metro homework_12.2
    // далее нажимаем справа Maven -> выбираем 📁 spb-metro -> 📁 Lifecycle -> install
    // и в 📁target появится два jar файла
    // перенесем файл который с зависимостями на рабочий стол (для экспериментов)
    // и запускаем с терминала: java -jar /имя файла/
    // допустим при работе программы есть ошибка и куда ее можно записать?

    // терминал: java -jar /имя файла/ 2> errors.log  (2 означает что будут писать ошибки в файл errors.log,
    // который создадиться на рабочем столе, />/ - это перенаправление потока, если просто >,
    // то это перенаправление первого потока 1> (основного потока вывода программы, то что печатается
    // в консоль), если это второй поток 2> , то это поток ошибок)

    // каждый раз при запуске программы у нас log стирается и записывается снова и что не стирались
    // предыдущие ошибки нужно писать так /2>>/

    // Чем плох такой лог? Во первых тем, что это лог ошибок, если мы захотим получить информацию
    // помимо ошибок, то таким /2>> errors.log/ это не удасться сделать, т/к у нас здесь есть
    // только два потока (поток ошибок и поток System.out.println).
    // Если мы поток System.out.println отправим в другой файл />> info.log/, то мы в терминале при
    // запуске программы не увидим информацию которую выводит System.out.println, она будет уходить в лог
    // файл /info.log/, а это не удобно

    // Есть готовые решения которые позволяют делать все просто и быстро, одно из
    // них - это log4j
}
