package com.higor.core

sealed class StateView<T>

class SucessState<T>(val data: T) : StateView<T>()
class ErrorState<T>(val error: Throwable) : StateView<T>()
class LoadingState<T> : StateView<T>()

