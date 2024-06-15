package ru.mixail_akulov.a53_tests_intro_task

/**
 * Resource consumer.
 * Usage example:
 * ```
 *     val consumer: Consumer<String> = { string ->
 *         println(string)
 *     }
 *     resourceManager.consumeResource(consumer)
 * ```
 */
typealias Consumer<R> = (R) -> Unit
