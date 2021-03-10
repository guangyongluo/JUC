package com.vilin.concurrent.chapter11;

public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.actionContext;
    }

    public Context getContext(){
        return threadLocal.get();
    }
}
