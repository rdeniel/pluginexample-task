package fr.paris.lutece.plugins.workflow.modules.example.services;

import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.workflow.modules.example.business.IWorkflowDAO;
import fr.paris.lutece.plugins.workflow.modules.example.business.Workflow;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.Task;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.i18n.I18nService;

public class ExampleTask extends Task{

    private static final String MESSAGE_REPLY = "module.workflow.thetask.task_thetask.labelTheTask";

    // PARAMETERS
    public static final String PARAMETER_USER_MESSAGE = "user_message";

    // Variables
    private AdminUser _user;

    @Inject
    private IExampleTaskService _thetaskService;
//    @Inject
//    private IWorkflowDAO _workflowDAO;
    @Inject
    @Named( "workflow-example.TheTaskConfigService" )
    private ITaskConfigService _taskTheTaskConfigService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( MESSAGE_REPLY, locale );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init( )
    {
    }

    /**
     * {@inheritDoc}
     */
    public void processTask( HttpServletRequest request, Locale locale )
    {
        String strMessage = request.getParameter( "reply_message_" + getId( ) );

        boolean bCreate = false;

        Workflow thetask = _thetaskService.find( getId( ) );

        if ( thetask == null )
        {
            thetask = new Workflow( );
            thetask.setId( getId( ) );
            bCreate = true;
        }

        thetask.setMessage( StringUtils.isNotBlank( strMessage ) ? strMessage : StringUtils.EMPTY );

        if ( bCreate )
        {
            _thetaskService.create( thetask );
        }
        else
        {
            _thetaskService.update( thetask );
        }
    }

    // GET

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getTaskFormEntries( Locale locale )
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveConfig( )
    {
        _thetaskService.removeByIdTask( getId( ) );
        _taskTheTaskConfigService.remove( getId( ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveTaskInformation( int nId )
    {
        _thetaskService.removeByIdTask( nId );
    }

	@Override
	public void processTask(int nIdResourceHistory, HttpServletRequest request, Locale locale) {
		// TODO Auto-generated method stub
		
	}

}
