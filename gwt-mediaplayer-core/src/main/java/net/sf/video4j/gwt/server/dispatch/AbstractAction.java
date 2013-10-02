package net.sf.video4j.gwt.server.dispatch;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.dispatch.shared.Result;

/**
 * @author gumatias
 */
public abstract class AbstractAction<A extends Action<R>, R extends Result> implements ActionHandler<A, R> {
    
    private final Class<A> mActionType;

    public AbstractAction(Class<A> pActionType) {
        this.mActionType = pActionType;
    }

    public Class<A> getActionType() {
        return mActionType;
    }

    @Override
    public void undo(A pAction, R pResult, ExecutionContext pContext) throws ActionException {
        // do nothing
    }
    
}