package com.example.grpc.client

import com.example.grpc.HelloRequest
import com.example.grpc.HelloServiceGrpcKt
import io.grpc.ManagedChannelBuilder

class Client {

    private val stub = HelloServiceGrpcKt.HelloServiceCoroutineStub(
        ManagedChannelBuilder
            .forAddress("localhost", 8080)
            .usePlaintext()
            .build(),
    )

    suspend fun hello() {
        stub.hello(
            HelloRequest.newBuilder()
                .setMessage("request message!!!")
                .build()
        )
    }
}

suspend fun main() {
    Client().hello()
}
