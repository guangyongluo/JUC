package com.vilin.concurrent.chapter18;

public class MakeStringRequest extends MethodRequest{

    private final int count;

    private final char fillChar;

    public MakeStringRequest(final Servant servant, final FutureResult futureResult, final int count, final char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result result = this.servant.makeString(count, fillChar);
        this.futureResult.setResult(result);
    }
}
