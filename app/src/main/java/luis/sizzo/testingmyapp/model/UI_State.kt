package luis.sizzo.testingmyapp.model

sealed class UI_State{
    object LOADING : UI_State()
    class SUCCESS<T>(val response : T) : UI_State()
    class ERROR(val error: Exception) : UI_State()
}
