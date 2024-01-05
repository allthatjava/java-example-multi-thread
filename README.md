
Java Thread Example

### Example 1
Using three ways to create and run the Thread.
- extends from Thread class
- implements from Runnable interface
- crete it as anonymous class

### Example 2 : example2/SyncExample.java
- Use synchronized key word on method definition so, multi thread can use that method one at a time.

### Example 3 : example3/ThreadPoolMain.java
- Use `ExecutorService executor = Executors.newFixedThreadPool(2);` to create Thread Pool of 2
- Use `executor.submit(new Processor(i));` to start Thread class
- executor.shutdown(); to put the Thread in possible shutdown state
- `executor.awaitTermination(1, TimeUnit.DAYS);` This line will make the Main processor wait until Threads are all processed for given time.

### Example 4 :