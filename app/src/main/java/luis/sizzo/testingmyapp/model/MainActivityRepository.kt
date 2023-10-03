package luis.sizzo.testingmyapp.model

import android.R.attr.name
import kotlinx.coroutines.flow.*
import luis.sizzo.testingmyapp.api.MainActivityApi


class MainActivityRepository: MainActivityApi {
    override fun getStringEncripted(descryptString: String) = flow {
        emit(UI_State.LOADING)
        try {
            emit(UI_State.SUCCESS(returningString(descryptString)))
        } catch (e: Exception) {
            emit(UI_State.ERROR(e))
        }
    }

    override fun getFlipStringEncripted(descryptString: String) = flow {
        emit(UI_State.LOADING)
        try {
            emit(UI_State.SUCCESS(flipString(descryptString)))
        } catch (e: Exception) {
            emit(UI_State.ERROR(e))
        }
    }

    override fun returningString(descryptString: String): String {
        var encryptedData = ""
        for (i in 0..descryptString.length-1) {
            if(descryptString[i] == 'R') encryptedData += 'S'
            else if(descryptString[i] == 'r') encryptedData +=  's'
            else if(descryptString[i] == 'e') encryptedData += 'f'
            else if(descryptString[i] == 'l') encryptedData += 'm'
            else if(descryptString[i] == 'o') encryptedData += 'p'
            else if(descryptString[i] == 'c') encryptedData += 'd'
            else if(descryptString[i] == 'a') encryptedData += 'b'
            else if(descryptString[i] == 't') encryptedData += 'u'
            else if(descryptString[i] == 'e') encryptedData += 'f'
            else if(descryptString[i] == 'F') encryptedData += 'G'
            else if(descryptString[i] == 'm') encryptedData += 'n'
            else if(descryptString[i] == 'n') encryptedData += 'o'
            else encryptedData += descryptString[i]
        }

        return encryptedData
    }
    override fun flipString(descryptString: String): String {
        val arr = descryptString.split(" ")
        var flipString = ""
        for (i in 0 ..arr.size-1) {
            flipString += " ${arr[i].reversed()}"
        }
        return  flipString
    }
}