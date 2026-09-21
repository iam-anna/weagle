package com.fiapos.weagle.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface WeagleApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("api/strategies")
    suspend fun getStrategies(): List<StrategyResponse>

    @GET("api/strategies/{id}")
    suspend fun getStrategy(@Path("id") id: String): StrategyResponse

    @POST("api/strategies")
    suspend fun createStrategy(@Body request: StrategyRequest): StrategyResponse

    @PUT("api/strategies/{id}")
    suspend fun updateStrategy(@Path("id") id: String, @Body request: StrategyRequest): StrategyResponse

    @GET("api/ideas")
    suspend fun getIdeas(): List<IdeaResponse>

    @GET("api/ideas/{id}")
    suspend fun getIdea(@Path("id") id: String): IdeaResponse

    @POST("api/ideas")
    suspend fun createIdea(@Body request: IdeaRequest): IdeaResponse

    @PUT("api/ideas/{id}")
    suspend fun updateIdea(@Path("id") id: String, @Body request: IdeaRequest): IdeaResponse

    @PATCH("api/ideas/{id}/approve")
    suspend fun approveIdea(@Path("id") id: String): IdeaResponse

    @PATCH("api/ideas/{id}/priority")
    suspend fun prioritizeIdea(@Path("id") id: String, @Query("highPriority") highPriority: Boolean): IdeaResponse

    @GET("api/projects")
    suspend fun getProjects(): List<ProjectResponse>

    @GET("api/projects/{id}")
    suspend fun getProject(@Path("id") id: String): ProjectResponse

    @POST("api/projects")
    suspend fun createProject(@Body request: ProjectRequest): ProjectResponse
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val id: String, val name: String, val email: String, val role: String)
data class StrategyRequest(val name: String, val description: String, val active: Boolean)
data class StrategyResponse(val id: String, val name: String, val description: String, val active: Boolean, val createdAt: String?)
data class IdeaRequest(val title: String, val description: String)
data class IdeaResponse(val id: String, val title: String, val description: String, val createdBy: String, val approved: Boolean, val highPriority: Boolean, val aiScore: Int?, val aiJustification: String?, val createdAt: String?)
data class ProjectRequest(val name: String, val description: String, val strategyId: String? = null, val ideaId: String? = null, val progress: Int = 0, val results: String? = null, val investment: Double = 0.0, val financialReturn: Double = 0.0, val productivityIncrease: Double = 0.0, val costReduction: Double = 0.0)
data class ProjectResponse(val id: String, val name: String, val description: String, val strategyId: String?, val ideaId: String?, val progress: Int, val results: String?, val investment: Double, val financialReturn: Double, val productivityIncrease: Double, val costReduction: Double, val createdAt: String?, val updatedAt: String?)
