# Лабораторная работа 3

## Вариант `1576`

<img alt="anime" src="./.resources/anime.gif" height="200">

> What do you call a really old ant? ~~An **ant**ique~~ Apache Ant

|.pdf|.docx|
|-|-|
| [report](./docs/report.pdf) | [report](./docs/report.docx) |

---

## Задание

Написать сценарий для утилиты [Apache Ant](http://ant.apache.org/), реализующий компиляцию, тестирование и упаковку в jar-архив кода проекта из [лабораторной работы №3](https://se.ifmo.ru/courses/web#lab3) по дисциплине __"Веб-программирование"__.

Каждый этап должен быть выделен в отдельный блок сценария; все переменные и константы, используемые в сценарии, должны быть вынесены в отдельный файл параметров; `MANIFEST.MF` должен содержать информацию о версии и о запускаемом классе.

### Сценарий должен реализовывать следующие цели (targets):

1. **compile** -- компиляция исходных кодов проекта.
2. **build** -- компиляция исходных кодов проекта и их упаковка в исполняемый jar-архив. Компиляцию исходных кодов реализовать посредством вызова цели **compile**.
3. **clean** -- удаление скомпилированных классов проекта и всех временных файлов (если они есть).
4. **test** -- запуск junit-тестов проекта. Перед запуском тестов необходимо осуществить сборку проекта (цель **build**).
5. **native2ascii** - преобразование [native2ascii](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/native2ascii.html) для копий файлов локализации (для тестирования сценария все строковые параметры необходимо вынести из классов в файлы локализации).
6. **diff** - осуществляет проверку состояния рабочей копии, и, если изменения касаются классов, указанных в файле параметров выполняет `commit` в репозиторий `git`.

### Вопросы к защите лабораторной работы:

1. **Тестирование ПО**. Цель тестирования, виды тестирования.
2. Модульное тестирование, основные принципы и используемые подходы.
3. Пакет **JUnit**, основные API.
4. Системы автоматической сборки. Назначение, принципы работы, примеры систем.
5. Утилита **make**. Make-файлы, цели и правила.
6. Утилита **Ant**. Сценарии сборки, цели и команды.


---

## Как запустить?

```bash
docker compose up # Setup PostgreSQL database
npm install # install webpack
npm run build # run webpack
./gradlew flywayMigrate # Database migrations
./gradlew flywayInfo # Check everything is OK
```

#### Gradle

```bash
./gradlew build # Build .war
```

После чего задеплоить `build/libs/web-3-1.0-SNAPSHOT.war` в WildFly.

#### Apache Ant

```bash
ant build # Build .war
```

После чего задеплоить `ant/build/web-3-1.0-SNAPSHOT.war` в WildFly.

## Полезные ссылки

| Ссылка | Описание |
| --- | --- |
| https://habr.com/ru/articles/323204/ | Гайд по Apache Ant |
| https://habr.com/ru/articles/120101/ | Гайд по JUnit 4 |
| https://github.com/VeraKasianenko/Fundamentals_of_SE/tree/main/lab3 | Пример ЛР3 |

## Лицензия <a name="license"></a>

Проект доступен с открытым исходным кодом на условиях [Лицензии MIT](https://opensource.org/license/mit/).

*Авторские права 2024 Max Barsukov*

**Поставьте звезду :star:, если вы нашли этот проект полезным.**
