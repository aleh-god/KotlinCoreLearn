package StyleGuide

/*
Все исходные файлы должны иметь UTF-8 кодировку.

Все исходные файлы, которые содержат высокоуровневые определения классов, должны именоваться следующим образом: имя класса + расширение файла .kt

Kotlin файл .kt включает в себя:
    * Заголовок, в котором указана лицензия и авторские права (необязательно)
    * Аннотации, которые объявлены на уровне файла
    * package объявление
    * import выражения
    * высокоуровневые объявления (классы, интерфейсы, различные функции)

            !!! Заголовок !!!
Заголовок должен быть объявлен выше остальных определений с использованием многострочных комментариев.
//!Не используйте однострочные комментарии
/** !Не используйте KDoc комментарии */
        !!! Аннотации, которые объявлены на уровне файла !!!
Аннотация @file, которая является use-site target должна быть помещена между заголовком и package объявлением.
        !!! package объявление !!!
Оператор package и import никогда не переносятся и всегда размещаются на одной строке.
Выражения import группируются для классов, функций и свойств в сортированные списки.
Импорты с подстановочным знаком не разрешены:  import androidx.room.*  // так делать не нужно
        !!! высокоуровневые объявления (классы, интерфейсы, различные функции) !!!
Kotlin файл может содержать объявление одного или нескольких классов, функций, свойств или typealiasвыражений.
Контент файла должен относится к одной теме. Например у нас есть публичный класс и набор extension функций, которые выполняют некоторые операции.
Нет явного ограничения на количество и порядок содержимого файла
Файлы обычно читаются сверху вниз, поэтому верхние части кода должны помогать нам понять нижние.
Для членов класса применимы те же правила, что и для высокоуровневых определений.

!!! Специальные символы
В исходном коде используется только ASCII горизонтальный пробельный символ (0x20).
Это означает, что:
* Все другие пробельные символы в строчных и символьных литералах должны экранироваться
* Tab символы не используются для отступов
* Для любого символа, который имеет экранированную последовательность (\b, \r, \t, \\) используется эта последовательность, а не Unicode (например: \u000a).
* Для оставшихся символов, которые не принадлежат ASCII, используется либо Unicode символ (∞), либо Unicode последовательность (\u221e).

!!! Скобки
Скобки не требуются для when и if которые помещаются на одной строке (оператор if не имеет else ветки).
Скобки следуют стилю Кернигана и Ритчи для непустых блоков и блочных конструкций:
* Нельзя делать разрыв строки перед открывающей скобкой
* Разрыв строки после открывающей cкобки
* Разрыв строки перед закрывающей скобкой
* Разрыв строки после закрывающей скобкой только в том случае, если она заканчивает выражение или тело функции, конструктора, класса.

Пустые блоки тоже должны быть в стиле K&R.

 */


/*
 * Copyright 2021 MyCompany, Inc.
 */
