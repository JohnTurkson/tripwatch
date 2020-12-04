package com.johnturkson.tripwatch.common.responses

import com.johnturkson.tripwatch.common.data.User
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class Response {
    abstract val statusCode: Int
    
    @Serializable
    sealed class Success : Response() {
        @Serializable
        sealed class OK : Success() {
            @Transient
            override val statusCode: Int = 200
            
            @Serializable
            data class CreateUserResponse(val user: User) : OK()
        }
    }
    
    @Serializable
    sealed class Error : Response() {
        @Serializable
        sealed class BadRequest : Error() {
            @Transient
            override val statusCode: Int = 400
            
            @Serializable
            object InvalidRequestError : BadRequest()
            
            @Serializable
            data class EmailAlreadyTakenError(val email: String) : BadRequest()
            
            @Serializable
            data class UsernameAlreadyTakenError(val username: String) : BadRequest()
            
            @Serializable
            data class PasswordTooShortError(val password: String) : BadRequest()
            
            @Serializable
            data class BreachedPasswordError(val password: String) : BadRequest()
        }
        
        @Serializable
        sealed class Unauthorized : Error() {
            @Transient
            override val statusCode: Int = 401
            
        }
        
        @Serializable
        sealed class Forbidden : Error() {
            @Transient
            override val statusCode: Int = 403
        }
        
        @Serializable
        sealed class NotFound : Error() {
            @Transient
            override val statusCode: Int = 404
        }
        
        @Serializable
        sealed class TooManyRequests : Error() {
            @Transient
            override val statusCode: Int = 429
        }
    }
}
