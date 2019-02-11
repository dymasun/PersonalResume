package xyz.dymasun.ws.rule;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RulePool {
    private int corePoolSize;
    private int maximumPoolSize;
    private int keepAliveTime;
    private String timeUnit;
    private String workQueue;
    private int capacity;
    private String rejectedExecutionHandler;
    private ThreadPoolExecutor JOB_EXECUTE;
    public void init(){
        BlockingQueue<Runnable> blockingQueue = null;
		RejectedExecutionHandler executionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
		try {
			blockingQueue = (BlockingQueue<Runnable>) Class.forName("java.util.concurrent."+workQueue).getConstructor(new Class[]{int.class}).newInstance(capacity);
			executionHandler = (RejectedExecutionHandler) Class.forName("java.util.concurrent.ThreadPoolExecutor$" + rejectedExecutionHandler).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        JOB_EXECUTE = new ThreadPoolExecutor(
        		corePoolSize,
				maximumPoolSize,
				keepAliveTime,
				TimeUnit.valueOf(timeUnit),
                blockingQueue,
				executionHandler);
    }
    public void add(Runnable command){
        JOB_EXECUTE.execute(command);
    }
    public void add(RuleWorker worker){JOB_EXECUTE.execute(worker);}
    public void destory(){
 		JOB_EXECUTE.shutdown();
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setWorkQueue(String workQueue) {
        this.workQueue = workQueue;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRejectedExecutionHandler(String rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
    }
}
