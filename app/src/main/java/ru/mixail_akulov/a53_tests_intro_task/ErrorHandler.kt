package ru.mixail_akulov.a53_tests_intro_task

interface ErrorHandler<R> {
    /**
     * Вызывается, когда какой-либо потребитель потерпел неудачу
     * с указанным [exception] при обработке [resource].
     */
    fun onError(exception: Exception, resource: R)
}
