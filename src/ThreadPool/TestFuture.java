package ThreadPool;
import java.util.concurrent.Callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {
 
 public static void main(String args[]){
  System.out.println("hello world");
  
  
  ExecutorService es = Executors.newCachedThreadPool();
  Future fu = es.submit(new Callable(){

   @Override
   public String call() throws Exception {
    Thread.sleep(100000); // remove it if return directly , but future still works
    return "hello my  world";
   }
   
  });
  System.out.println("hello world1111111111");
  Object result = "";
  try {
   System.out.println("hello world222222");
   result = fu.get();
   System.out.println("hello world3333333");
  } catch (InterruptedException | ExecutionException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  System.out.println(result);
  es.shutdown(); // shutdown the executor
 }

}