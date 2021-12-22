public class Main {
    public static void main(String[] args) {
        // ❤️ Табличные файлы

        /** Есть к примеру у нас табличный файл - Exel, но парсить его напрямую через чтение файлов практически
         * невозможно (нужно подключать специальные библиотеки).
         * Но файл - Exel можно сохранить в нескольких форматах которые легко можно парсить самостоятельно:
         * 📍 Сохранить как...  -> далее
         * 📍 Выбираем формат сохранения: Текст с разделителями-символами табуляции(.txt)
         *  Если откроем этот файл в текстовом редакторе (sublime), то все столбцы все друг от друга отдалены
         *  символами табуляции. Соответственно мы его просто читаем построчно, разделяем символами табуляции, и
         *  получаем значения. Пример парсинга такого файла был в проекте LambdaExpression про сотрудников.
         *  ⬆ Это был первый формат.
         *
         *  ------
         *  Есть 2-ой формат файла (очень популярный):
         *  📍 Сохранить как...  -> далее
         *  📍 Выбираем формат сохранения: Текст с разделителями-запятыми(.csv)
         *    🖍P.S.🖍
         *    📌 CSV (comma-separated value) - это формат представления табличных данных
         *    (например, это могут быть данные из таблицы или данные из БД).
         *    В этом формате каждая строка файла - это строка таблицы.📌
         *    Если откроем этот файл в текстовом редакторе (sublime), то здесь все данные разделены между
         *    собой - /;/. Если в тексте встречается какой-нибудь лишний символ, та же /;/ например, то
         *    этот кусочек теста будет заключен в кавычки, чтобы можно было корректно этот файл распарсить.
         *    Есть специальные библиотеки с помощью которых эти файлы можно парсить, но если файлы простые
         *    (никаких кавычек нет например), то они в принципе парсятся очень легко, точно таким же образом.
         *
         *  */
    }
}
