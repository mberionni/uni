package UtilityObjectWrappers;

import com.inprise.vbroker.interceptor.UntypedObjectWrapper;
import com.inprise.vbroker.interceptor.Closure;

public class TimingUntypedObjectWrapper implements UntypedObjectWrapper
{  private long time;

   public void pre_method(String operation, org.omg.CORBA.Object target, Closure closure)
   {  System.out.println("Timing: " +  ((com.inprise.vbroker.CORBA.Object) target)._object_name() + "->" + operation + "()");
      time = System.currentTimeMillis();
   }

   public void post_method(String operation, org.omg.CORBA.Object target, org.omg.CORBA.Environment env, Closure closure)
   {  String objectName;
      long diff = System.currentTimeMillis() - time;
      objectName = ((com.inprise.vbroker.CORBA.Object) target)._object_name();
      System.out.println("Timing: Time for call \t" +  objectName + "->" + operation + "() = " + diff + " ms.");
      Reti.ApplicationGUI.setRequestTime(diff + "");
      Reti.ServerMonitorWindow mw = Reti.StartServer.getMonitorWindow();
      mw.appendText("Timing: Execution time for " + objectName + "->" + operation + "() = " + diff + " ms.");
   }
}
