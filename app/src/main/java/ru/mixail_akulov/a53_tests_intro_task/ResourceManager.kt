package ru.mixail_akulov.a53_tests_intro_task

import java.util.concurrent.Executor

/**
Менеджер ресурсов может одновременно хранить 1 экземпляр некоторого ресурса <R>.
  - Ресурс может быть назначен/заменен с помощью вызова [setResource].
  - Ресурс может быть отменен вызовом [clearResource].
  - Доступ к ресурсу можно получить через вызов [consumeResource].
  - Экземпляр [ResourceManager] может быть уничтожен вызовом [destroy].
Подробнее см. в комментариях к методам [setResource], [clearResource], [consumeResource] и [destroy].
 */
class ResourceManager<R>(

    /**
     * Исполнитель, который запускает потребителей ресурсов, передается вызову [consumeResource].
     */
    private val executor: Executor,

    /**
     * Обработчик ошибок, который перехватывает исключения, возникающие при потреблении ресурсов.
     */
    private val errorHandler: ErrorHandler<R>

) {

    /**
     * Назначить/заменить ресурс, удерживаемый менеджером ресурсов.
     *
     * Если через вызов [consumeResource] добавлены ожидающие потребители, то они вызываются
     * через [executor] в том же порядке, в котором они были добавлены к вызову [consumeResource].
     * Процесс потребления ресурса такой же, как и для вызова [consumeResource], когда ресурс доступен.
     *
     * Если менеджер уже уничтожен, этот метод ничего не делает.
     */
    fun setResource(resource: R) {
        executor.execute { resource }
    }

    /**
     * Удалите ресурс (если он существует) из этого диспетчера ресурсов.
     * Если менеджер уже уничтожен, этот метод ничего не делает.
     */
    fun clearResource() {
        TODO()
    }

    /**
     * Доступ к ресурсу по указанному [consumer].
     *
     * - Если ресурс доступен в момент вызова [consumeResource],
     *   то [consumer] вызывается немедленно через [executor],
     *   и ресурс отправляется в аргумент потребителя.
     * - Если ресурс НЕ доступен, [consumer] начинает ждать ресурс,
     *   пока он не станет доступным или пока менеджер не будет уничтожен.
     *
     * Потребители могут потерпеть неудачу с исключениями.
     * В этом случае диспетчер ресурсов должен вызвать [ErrorHandler.onError]
     * для экземпляра [errorHandler], переданного конструктору.
     *
     * Каждый вызов [consumeResource] дает возможность получить ресурс ТОЛЬКО ОДИН РАЗ.
     * Например, этот код выводит «Hello, R1» 1 раз:
     * ```
     *   resourceManager.consumeResource { resource ->
     *     println("Hello, $resource")
     *   }
     *   resourceManager.setResource("R1")
     *   resourceManager.setResource("R2")
     * ```
     *
     * Этот код печатает «Hello, R2» 1 раз:
     * ```
     *   resourceManager.setResource("R1")
     *   resourceManager.setResource("R2")
     *   resourceManager.consumeResource { resource ->
     *     println("Hello, $resource")
     *   }
     *   resourceManager.setResource("R3")
     * ```
     *
     * Но 2 потребителя могут получить доступ к одному и тому же ресурсу.
     * Например, этот код выводит «Hello, R1» 2 раза:
     * ```
     *   val consumer: Consumer<String> = { resource ->
     *     println("Hello, $resource")
     *   }
     *   resourceManager.setResource("R1")
     *   resourceManager.consumeResource(consumer) // "Hello, R1"
     *   resourceManager.consumeResource(consumer) // "Hello, R1" again
     * ```
     *
     * Если менеджер уже уничтожен, этот метод ничего не делает.
     */
    fun consumeResource(consumer: Consumer<R>) {
        TODO()
    }

    /**
     * Уничтожьте этого менеджера ресурсов.
     *
     * Все дальнейшие вызовы [setResource], [clearResource] и [consumeResource] после уничтожения
     * игнорируются. Все ожидающие потребители, переданные в [consumeResource], отбрасываются.
     * Если в момент уничтожения через вызов [setResource] назначен ресурс,
     * то он очищается так же, как и при вызове [clearResource].
     */
    fun destroy() {
        TODO()
    }


}
