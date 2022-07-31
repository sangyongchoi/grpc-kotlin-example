package com.example.grpc.server

import com.linecorp.armeria.common.SessionProtocol
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.grpc.GrpcService

fun main(args: Array<String>) {
    ServerConfig().start()
}

class ServerConfig {

    fun start() {
        val sb = Server.builder()

        sb.service(
            GrpcService.builder()
                .addService(com.example.grpc.server.HelloServer())
                .build()
        )

        sb.port(8080, SessionProtocol.HTTP)
        val server = sb.build()

        server.start()
    }
}
