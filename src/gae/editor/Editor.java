// This entire file is part of my masterpiece.
// ERIC SABA

package gae.editor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import engine.fieldsetting.Settable;


/**
 *
 * @author Eric Saba
 *
 * The base editor class that provides basic reflection and recursion methods for all subclasses.
 */
public abstract class Editor implements Edits {
    private Map<String, ArrayList<String>> myPropertiesMap;
    private final static String COLLECTIONS_TYPE = "java.util.Collection<engine.gameobject.units.Buff>";
    private final static String DOUBLE_EDITOR = "DoubleTextEditor";
    private final static String FILE_CHOOSER_EDITOR = "FileChooserEditor";
    private final static String TEXT_EDITOR = "TextEditor";
    private final static String COLLECTION_EDITOR = "CollectionComponentEditor";
    private final static String OBJECT_EDITOR = "ObjectComponentEditor";

    public Editor () {
        myPropertiesMap =
                EditingParser.getInterfaceClasses("engine.fieldsetting.implementing_classes");
    }

    abstract void setDefaults ();

    abstract void clearValues ();

    /**
     * Creates and returns the method tree of TreeNodes that holds the given class's set methods, parameter types and 
     * editor types.
     * 
     * @param klass     The class which is reflected over.
     * @param m         The set method for this class to be set. Used for recursively creating the tree.
     * @return          The base TreeNode that holds all of the other nodes.
     */
    public TreeNode getMethodsTree (Class<?> klass, Method m) {
        TreeNode root = new TreeNode(m, "null");
        List<Method> methods = EditingParser.getMethodsWithAnnotation(klass, Settable.class);
        for (Method method : methods) {
            Type parameterClass = method.getGenericParameterTypes()[0];
            if (parameterClass.equals(double.class)) root.addToNodes(new TreeNode(method, DOUBLE_EDITOR));
            else if (parameterClass.equals(String.class)) {
                if (getPropertyName(method.getName()).equals("Image Path")) root.addToNodes(new TreeNode(method, FILE_CHOOSER_EDITOR));
                else root.addToNodes(new TreeNode(method, TEXT_EDITOR));
            }
            else if (parameterClass.getTypeName().equals(COLLECTIONS_TYPE)) {
                root.addToNodes(new TreeNode(method, COLLECTION_EDITOR));
            }
            else root.addToNodes(new TreeNode(method, OBJECT_EDITOR));
        }
        return root;
    }

    /**
     * A quick method to get the basic property name by cutting up the method name. 
     * @param methodName        The name of the set method.
     * @return          The method name without the beginning "set" and with spaces after all capital letters.
     */
    public static String getPropertyName (String methodName) {
        String propertyName = methodName.substring(3, methodName.length());
        char[] chars = propertyName.toCharArray();
        int editCount = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                String temp1 = propertyName.substring(0, i + editCount);
                String temp2 = propertyName.substring(i + editCount, propertyName.length());
                propertyName = String.format("%s %s", temp1, temp2);
                editCount++;
            }
        }
        return propertyName;
    }

    protected Map<String, ArrayList<String>> getPropertiesMap () {
        return myPropertiesMap;
    }
}
