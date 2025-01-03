package com.learnxinyminutes.kotlin.callBackLearn


fun main() {
    MainActivity().run()
}

class MainActivity {
    fun run() {

        val b = Button(ButtonClickHandler())

        b.click()

        val c = Button(object : EventHandler {
            override fun execute() {
                    // Здесь мы обрабатываем нажатие кнопки
                    println("Кнопка C нажата")
            }
        })

        c.click()

        Button(object : EventHandler {
            override fun execute() {
                // Здесь мы обрабатываем нажатие кнопки
                println("Кнопка нажата")
            }
        }).click()

        /*
          final Button button = findViewById(R.id.button_id);

          button.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
                  // Code here executes on main thread after user presses button
              }
          });
         */


    }
}



class ButtonClickHandler : EventHandler{

    override fun execute() {
        // Здесь мы обрабатываем нажатие кнопки
        println("Кнопка ButtonClickHandler нажата")
    }
}

class Button (private var handler:EventHandler) {

    fun click() {
        handler.execute()
    }
}

class Holder {

    val main = MainActivity()

    // Здесь нужно что-то сделать, что бы при нажатии кнопки в мэйне сработала функция
    fun steelCallBack() {

    }

}