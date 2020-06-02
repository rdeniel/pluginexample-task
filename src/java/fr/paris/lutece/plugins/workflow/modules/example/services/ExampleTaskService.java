package fr.paris.lutece.plugins.workflow.modules.example.services;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.workflow.modules.example.business.IWorkflowDAO;
import fr.paris.lutece.plugins.workflow.modules.example.business.Workflow;
import fr.paris.lutece.plugins.workflowcore.service.action.IActionService;
import fr.paris.lutece.plugins.workflowcore.service.state.IStateService;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.message.SiteMessageService;
import fr.paris.lutece.portal.service.plugin.PluginService;

public class ExampleTaskService implements IExampleTaskService
	{
	    public static final String BEAN_SERVICE = "workflow-example.workflowService";

	    // SERVICES
	    @Inject
	    private IStateService _stateService;
	    @Inject
	    private IActionService _actionService;

	    // DAO
	    @Inject
	    private IWorkflowDAO _workflowDAO;

	    // SET

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public void setSiteMessage( HttpServletRequest request, String strMessage, int nTypeMessage, String strUrlReturn ) throws SiteMessageException
	    {
	        if ( StringUtils.isNotBlank( strUrlReturn ) )
	        {
	            SiteMessageService.setMessage( request, strMessage, nTypeMessage, strUrlReturn );
	        }
	        else
	        {
	            SiteMessageService.setMessage( request, strMessage, nTypeMessage );
	        }
	    }

	    // CRUD

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    @Transactional( WorkflowPlugin.BEAN_TRANSACTION_MANAGER )
	    public void create( Workflow workflow )
	    {
	        if ( workflow != null )
	        {
	            _workflowDAO.insert( workflow, PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME ) );

	        }
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    @Transactional( WorkflowPlugin.BEAN_TRANSACTION_MANAGER )
	    public void update( Workflow workflow )
	    {
	        if ( workflow != null )
	        {
	            _workflowDAO.store( workflow, PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME ) );

	        }
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public Workflow find( int nIdTask )
	    {
	        Workflow workflow = _workflowDAO.load( nIdTask, PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME ) );

	        return workflow;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public List<Workflow> findByIdTask( int nIdTask )
	    {
	        return _workflowDAO.loadByIdTask( nIdTask, PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME ) );
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    @Transactional( WorkflowPlugin.BEAN_TRANSACTION_MANAGER )
	    public void removeByIdTask( int nIdTask )
	    {
	        _workflowDAO.deleteByIdTask( nIdTask, PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME ) );
	    }
}
