
Java Thread Example

### Example 1
Using three ways to create and run the Thread.
- extends from Thread class
- implements from Runnable interface
- crete it as anonymous class
- uses synchronized method call to sync for SharedResource object

### Example 2 : example2/SyncExample.java
- Use synchronized key word on method definition so, multi thread can use that method one at a time.

### Example 3 : example3/ThreadPoolMain.java
- Use `final ExecutorService executor = Executors.newFixedThreadPool(2);` to create Thread Pool of 2
- Alternatively, we can use commented out line `final ExecutorService executor = Executors.newCachedThreadPool();` to increase the Thread Pool size as needed to improve the performance
- Use `executor.submit(new Processor(i));` to start Thread class
- executor.shutdown(); to put the Thread in possible shutdown state
- `executor.awaitTermination(1, TimeUnit.DAYS);` This line will make the Main processor wait until Threads are all processed for given time.

### Example 4 :
- Use `BlockingQueue` to use two Thread as Provider and Consumer pattern
- Thread1 keep providing random number to queue until it reached to the limit
- Thread2 keep taking out the item from queue

### Example 5 :
- Use locking mechanism to guarantee the outcome result
- Overwritten locking mechanism with Java 1.5 `Lock` class from synchronized technique