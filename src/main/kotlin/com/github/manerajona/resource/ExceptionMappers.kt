package com.github.manerajona.resource

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.server.ServerExceptionMapper

@SuppressWarnings("unset")
class ExceptionMappers {
    @ServerExceptionMapper(BadRequestException::class)
    fun mapBadRequest(ex: BadRequestException): Response {
        return Response.status(400).entity(ex.message).build()
    }
}