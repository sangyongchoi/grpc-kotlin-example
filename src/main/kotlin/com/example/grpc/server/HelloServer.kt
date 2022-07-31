package com.example.grpc.server

import com.example.grpc.HelloRequest
import com.example.grpc.HelloResponse
import com.example.grpc.HelloServiceGrpcKt

class HelloServer : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {

    override suspend fun hello(request: HelloRequest): HelloResponse {
        println(request.message)

        return HelloResponse.newBuilder()
            .setMessage("success!!")
            .build()
    }
}
