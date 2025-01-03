package com.learnxinyminutes.kotlin.coroutinesLearn

import kotlinx.coroutines.*

fun main() = runBlocking{
    println("")
    main30()

    println("Диспетчер корутины")
    main20()
    println("мы можем сами задать для корутины диспетчер, передав в функцию launch (а также async) соответствующее значение:")
    main30()
    println("Тип Dispatchers.Unconfined запускает корутину в текущем вызывающем потоке до первой приостановки.")
    main40()
    println("newSingleThreadContext вручную запускает поток с указанным именем:")
    main50()
    println("Для отмены выполнения корутины у объекта Job может применяться метод cancel():")
    main60()
    println("Также вместо двух методов cancel() и join() можно использовать один сборный метод cancelAndJoin():")
    main70()
    println("Обработка исключения CancellationException")
    main80()

}


// Контекст корутины включает себя такой элемент как диспетчер корутины.
// Диспетчер корутины определяет какой поток или какие потоки будут использоваться для выполнения корутины.
suspend fun main20() = coroutineScope{

    launch {
        // с помощью переменной Thread.currentThread().name мы можем получить имя потока.
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")       // применяет диспетчер Dispatcher.Default.
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")
}

suspend fun main30() = coroutineScope{

    launch(Dispatchers.Default) {   // явным образом определяем диспетчер Dispatcher.Default
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")
}

suspend fun main40() = coroutineScope{

    launch(Dispatchers.Unconfined) {
        println("Поток корутины (до остановки): ${Thread.currentThread().name}")
        delay(500L)
        println("Поток корутины (после остановки): ${Thread.currentThread().name}")
    }

    println("Поток функции main: ${Thread.currentThread().name}")
}

suspend fun main50() = coroutineScope{

    // В то же время выделенный поток является довольно затратным ресурсом.
    // И в реальном приложении подобый поток следует либо освобождать с помощью функции close(), если он больше не нужен,
    // либо хранить в глобальной переменной и использовать его повторно для подобных задач на протяжении работы приложения.
    launch(newSingleThreadContext("Custom Thread")) {
        println("Поток корутины: ${Thread.currentThread().name}")
    }

    println("Поток функции main: ${Thread.currentThread().name}")
}

suspend fun main60() = coroutineScope{

    // ссылка на корутину
    val downloader: Job = launch{
        println("Начинаем загрузку файлов")
        for(i in 1..5){
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)     // установим задержку, чтобы несколько файлов загрузились
    println("Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
    downloader.cancel()    // отменяем корутину
    downloader.join()      // ожидаем завершения корутины
    println("Работа программы завершена")
}

suspend fun main70() = coroutineScope{

    val downloader: Job = launch{
        println("Начинаем загрузку файлов")
        for(i in 1..5){
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)
    println("Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
    downloader.cancelAndJoin()    // отменяем корутину и ожидаем ее завершения
    println("Работа программы завершена")
}

suspend fun main80() = coroutineScope{

    val downloader: Job = launch{
        try {       // Здесь код выполнения корутины обернут в конструкцию try.
                    // Если корутина будет прервана извне, то с помощью блока catch и перехвата исключения CancellationException мы сможем обработать отмену корутины.
            println("Начинаем загрузку файлов")
            for(i in 1..5){
                println("Загружен файл $i")
                delay(500L)
            }
        }
        catch (e: CancellationException ){
            println("Загрузка файлов прервана")
        }
        finally{
            println("Загрузка завершена")
        }
    }
    delay(800L)
    println("Надоело ждать. Прерву-ка я загрузку...")
    downloader.cancelAndJoin()    // отменяем корутину и ожидаем ее завершения
    println("Работа программы завершена")
}

suspend fun main90() = coroutineScope{

    // создаем и запускаем корутину
    val message = async {
        getMessage()
    }
    // отмена корутины
    message.cancelAndJoin()

    // можно отменять выполнение и корутин, создаваемых с помощью функции async(). В этом случае обычно вызов метода await() помещается в блок try:
    try {
        // ожидаем получение результата
        println("message: ${message.await()}")
    }
    catch (e:CancellationException){
        println("Coroutine has been canceled")
    }
    println("Program has finished")
}