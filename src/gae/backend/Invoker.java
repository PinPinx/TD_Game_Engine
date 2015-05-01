// This entire file is part of my masterpiece.
// John Gilhuly

package gae.backend;

import engine.fieldsetting.Settable;
import gae.editor.EditingParser;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Abstract class that invokes the given settable method within the given class
 * 
 * @author JohnGilhuly
 *
 */

public abstract class Invoker {

    public void invokeSettableMethod (Class<?> klass, String methodName, Object ... parameters)
                                                                                               throws ClassNotFoundException,
                                                                                               IllegalAccessException,
                                                                                               IllegalArgumentException,
                                                                                               InvocationTargetException {
        for (Method m : EditingParser.getMethodsWithAnnotation(Class.forName(klass.getClass()
                .getName()), Settable.class)) {
            if (m.getName().equals(methodName)) {
                m.invoke(klass, parameters);
            }
        }
    }
}
