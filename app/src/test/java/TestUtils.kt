import com.google.gson.reflect.TypeToken
import com.kohlwage.boschkraken.models.responses.KrakenResponse
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object TestUtils {

    fun getKrakenResponse(type: Type): ParameterizedType =
        TypeToken.getParameterized(
            KrakenResponse::class.java,
            type
        ).type as ParameterizedType

    fun getKrakenResponseWithMap(type: Type): ParameterizedType =
        getKrakenResponse(TypeToken.getParameterized(
            Map::class.java,
            type
        ).type as ParameterizedType)

}