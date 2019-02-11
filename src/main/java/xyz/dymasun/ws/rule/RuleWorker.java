package xyz.dymasun.ws.rule;

//线程及线程池所用的工具类
public abstract class RuleWorker extends Thread {
    private RuleMap param;
    public RuleWorker(){
    }
    //实现线程功能所需要实现的方法
	public abstract void doMethod(RuleMap param);
    @Override
    public void run() {
        this.doMethod(this.param);
    }

    public RuleWorker params(RuleMap param){
        this.param = param;
        return this;
    }
}
