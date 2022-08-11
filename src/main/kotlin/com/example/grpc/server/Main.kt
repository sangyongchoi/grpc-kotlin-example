package com.example.grpc.server

import com.linecorp.armeria.common.HttpRequest
import com.linecorp.armeria.server.HttpService
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.ServiceRequestContext
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.LoggingService

fun main(args: Array<String>) {
    ServerConfig().start()
}

class ServerConfig {

    fun start() {
        val sb = Server.builder()
        val grpcService = GrpcService.builder()
            .addService(HelloServer())
            .build()
        val loggingDecorator = LoggingService.newDecorator()
        sb.service(
            grpcService,
            loggingDecorator
        )
        sb.serviceUnder("/docs", DocService())

        // logging example
        sb.decorator { delegate: HttpService, ctx: ServiceRequestContext, req: HttpRequest ->
            ctx.log().whenComplete().thenApply { log ->
                val request = log.toStringRequestOnly()
                val response = log.toStringResponseOnly()

                println("request::$request")
                println("response::$response")
            }
            delegate.serve(ctx, req)
        }

        sb.http(8080)

        val server = sb.build()

        server.start()
    }
}
