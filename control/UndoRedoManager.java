
package control;
import adt.LinkedStack;

/**
 *
 * @author Tongkitming
 */

public class UndoRedoManager<T> {
    private LinkedStack<T> undoStack;
    private LinkedStack<T> redoStack;
    
    public UndoRedoManager() {
        undoStack = new LinkedStack<>();
        redoStack = new LinkedStack<>();
    }
    
    public void performAction(T action){
        undoStack.push(action);
        redoStack.clear();
    }
    
    public T undo() {
        if(!undoStack.isEmpty()){
            T undoneAction = undoStack.pop();
            redoStack.push(undoneAction);
            return undoneAction;
        }
        return null;
    }
    
    public T redo() {
        if(!redoStack.isEmpty()) {
            T redoneAction = redoStack.pop();
            undoStack.push(redoneAction);
            return redoneAction;
        }
        return null;
    }
    
}
