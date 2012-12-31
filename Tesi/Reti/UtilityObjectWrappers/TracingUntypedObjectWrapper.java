package UtilityObjectWrappers;

import com.inprise.vbroker.interceptor.UntypedObjectWrapper;
import com.inprise.vbroker.interceptor.Closure;

public class TracingUntypedObjectWrapper implements UntypedObjectWrapper
{  public void pre_method(String operation, org.omg.CORBA.Object target, Closure closure)
   {  System.out.println("Tracing: Before Call to \t" + ((com.inprise.vbroker.CORBA.Object) target)._object_name() +
		       "->" + operation + "()");
   }

   public void post_method(String operation, org.omg.CORBA.Object target, org.omg.CORBA.Environment env, Closure closure)
  {  System.out.println("Tracing: After call to \t" + ((com.inprise.vbroker.CORBA.Object) target)._object_name() +
		       "->" + operation + "()");
  }
}
