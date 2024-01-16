package dev.dj;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {

    @GrpcClient("calculator")
    CalculatorGrpc.CalculatorBlockingStub calculatorGrpc;

    @GrpcClient("calculator")
    Calculator calculator;

    @GET
    @Path("/block/sum/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public OutputDto addBlocking(Integer num1, Integer num2) {
        return new OutputDto( calculatorGrpc.add(Input.newBuilder().setNum1(num1).setNum2(num2).build()).getResult());
    }
    @GET
    @Path("/nonblocking/sum/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<OutputDto> addNonBlocking(Integer num1, Integer num2) {
        return calculator.add(Input.newBuilder().setNum1(num1).setNum2(num2).build()).map(val -> val.getResult()).map(OutputDto::new);
    }
    @GET
    @Path("/block/subtract/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public OutputDto subtractBlocking(Integer num1, Integer num2) {
        return new OutputDto( calculatorGrpc.subtract(Input.newBuilder().setNum1(num1).setNum2(num2).build()).getResult());
    }
    @GET
    @Path("/nonblocking/subtract/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<OutputDto> subtractNonBlocking(Integer num1, Integer num2) {
        return calculator.subtract(Input.newBuilder().setNum1(num1).setNum2(num2).build()).map(val -> val.getResult()).map(OutputDto::new);
    }
    @GET
    @Path("/block/multiply/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public OutputDto multiplyBlocking(Integer num1, Integer num2) {
        return new OutputDto( calculatorGrpc.multiply(Input.newBuilder().setNum1(num1).setNum2(num2).build()).getResult());
    }
    @GET
    @Path("/nonblocking/multiply/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<OutputDto> multiplyNonBlocking(Integer num1, Integer num2) {
        return calculator.multiply(Input.newBuilder().setNum1(num1).setNum2(num2).build()).map(val -> val.getResult()).map(OutputDto::new);
    }
    @GET
    @Path("/block/divide/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public OutputDto divideBlocking(Integer num1, Integer num2) {
        return new OutputDto( calculatorGrpc.divide(Input.newBuilder().setNum1(num1).setNum2(num2).build()).getResult());
    }
    @GET
    @Path("/nonblocking/divide/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<OutputDto> divideNonBlocking(Integer num1, Integer num2) {
        return calculator.divide(Input.newBuilder().setNum1(num1).setNum2(num2).build()).map(val -> val.getResult()).map(OutputDto::new);
    }
}
