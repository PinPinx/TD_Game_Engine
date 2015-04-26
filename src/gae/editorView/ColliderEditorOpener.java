package gae.editorView;

import java.util.List;
import java.util.function.BiConsumer;
import gae.editor.ComponentEditor;
import gae.editor.SimpleEditor;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ColliderEditorOpener extends EditorOpener {
    private static final String TITLE = "Collider Editor";

    public ColliderEditorOpener (BiConsumer<Class<?>, Object> biconsumer, Class<?> klass) {
        super(biconsumer, klass);
    }

    @Override
    public Parent setUpParent (SimpleEditor editor) {
        BorderPane border = new BorderPane();
        List<ComponentEditor> simpleList = editor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);
        VBox top = (VBox) simpleEditorView.getObject();
        border.setTop(top);
        return border;
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return TITLE;
    }

}