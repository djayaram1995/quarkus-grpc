package dev.dj;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class CalculatorGrpcService implements Calculator {

    @Override
    public Uni<Output> add(Input request) {
        return Uni.createFrom().item(request.getNum1()+ request.getNum2())
                .map(msg -> Output.newBuilder().setResult(msg).build());
    }

    @Override
    public Uni<Output> subtract(Input request) {
        return Uni.createFrom().item(request.getNum1()- request.getNum2())
                .map(msg -> Output.newBuilder().setResult(msg).build());
    }

    @Override
    public Uni<Output> divide(Input request) {
        return Uni.createFrom().item(request.getNum1()/ request.getNum2())
                .map(msg -> Output.newBuilder().setResult(msg).build());
    }

    @Override
    public Uni<Output> multiply(Input request) {
        return Uni.createFrom().item(request.getNum1()* request.getNum2())
                .map(msg -> Output.newBuilder().setResult(msg).build());
    }

}
