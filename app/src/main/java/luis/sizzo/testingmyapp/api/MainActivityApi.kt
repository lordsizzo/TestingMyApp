package luis.sizzo.testingmyapp.api

import kotlinx.coroutines.flow.Flow
import luis.sizzo.testingmyapp.model.UI_State

interface MainActivityApi {
    fun getStringEncripted(descryptString: String): Flow<UI_State>
    fun getFlipStringEncripted(descryptString: String): Flow<UI_State>
    fun returningString(descryptString: String): String
    fun flipString(descryptString: String): String
}