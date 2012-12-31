package UtilityObjectWrappers;

import java.util.*;
import com.inprise.vbroker.orb.ORB;
import com.inprise.vbroker.properties.PropertyManager;
import com.inprise.vbroker.interceptor.*;

public class Init implements ServiceLoader {
  com.inprise.vbroker.orb.ORB _orb;

  public void init(final org.omg.CORBA.ORB orb) {
    _orb = (ORB) orb;
    PropertyManager pm= _orb.getPropertyManager();
    try {
      ChainUntypedObjectWrapperFactory factory =
	ChainUntypedObjectWrapperFactoryHelper.narrow
        (orb.resolve_initial_references("ChainUntypedObjectWrapperFactory"));
      // install my Timing ObjectWrapper
      String val = pm.getString("Timing", this.toString());
      if( !val.equals(this.toString())) {
	UntypedObjectWrapperFactory f = new TimingUntypedObjectWrapperFactory();
	if( val.equalsIgnoreCase("client") ){
	  factory.add(f, Location.CLIENT);
	} else if( val.equalsIgnoreCase("server") ) {
	  factory.add(f, Location.SERVER);
	} else {
	  factory.add(f, Location.BOTH);
	}
      }
      // install my Tracing ObjectWrapper
      val = pm.getString("Tracing", this.toString());
      if( !val.equals(this.toString())) {
	UntypedObjectWrapperFactory f= new TracingUntypedObjectWrapperFactory();
	if( val.equalsIgnoreCase("client") ){
	  factory.add(f, Location.CLIENT);
	} else if( val.equalsIgnoreCase("server") ) {
	  factory.add(f, Location.SERVER);
	} else {
	  factory.add(f, Location.BOTH);
	}
      }
    } catch( org.omg.CORBA.ORBPackage.InvalidName e ) {
      return;
    }
  }

  public void init_complete(org.omg.CORBA.ORB orb){}
  public void shutdown(org.omg.CORBA.ORB orb){}
}
