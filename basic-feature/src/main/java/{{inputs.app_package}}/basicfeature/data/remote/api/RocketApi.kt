package {{inputs.app_package}}.basicfeature.data.remote.api

import {{inputs.app_package}}.basicfeature.data.remote.model.RocketResponse
import retrofit2.http.GET

interface RocketApi {

    @GET("rockets")
    suspend fun getRockets(): List<RocketResponse>
}
