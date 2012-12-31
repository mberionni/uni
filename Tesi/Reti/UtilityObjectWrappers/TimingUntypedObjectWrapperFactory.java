package UtilityObjectWrappers;

import com.inprise.vbroker.interceptor.*;

public class TimingUntypedObjectWrapperFactory implements UntypedObjectWrapperFactory
 {
  public UntypedObjectWrapper create(org.omg.CORBA.Object target, Location loc)
  {   return new TimingUntypedObjectWrapper();
  }

}
