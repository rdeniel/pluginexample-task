/*
 * Copyright (c) 2002-2020, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.example.web;

import fr.paris.lutece.plugins.workflow.modules.example.business.Workflow;
import fr.paris.lutece.plugins.workflow.modules.example.business.WorkflowConfig;
import fr.paris.lutece.plugins.workflow.modules.example.services.IExampleTaskService;
import fr.paris.lutece.plugins.workflow.modules.example.services.ExampleTaskService;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflow.web.task.AbstractTaskComponent;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.util.html.HtmlTemplate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * This class provides the user interface to manage Workflow features ( manage, create, modify, remove )
 */
public class WorkflowJspBean extends AbstractTaskComponent
{
    // Templates
    private static final String TEMPLATE_TASK_REPLY_CONFIG = "/admin/plugins/pluginexample/modules/thetask/task_workflow_config.html";
    private static final String TEMPLATE_TASK_REPLY_FORM = "/admin/plugins/pluginexample/modules/thetask/task_workflow_form.html";

    // PARAMETERS
    private static final String PARAMETER_REPLY_VALUE = "reply_message";

    // Properties for page titles
    private static final String MESSAGE_MANDATORY_FIELD = "module.workflow.example.task_config.message.mandatory.field";
    private static final String MESSAGE_NO_CONFIGURATION_FOR_TASK_REPLY = "module.workflow.example.message.no_configuration_for_task";

    // MARKERS
    private static final String MARK_CONFIG = "config";
    private static final String MARK_WEBAPP_URL = "webapp_url";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_MESSAGE = "message";
    
    @Override
    public String getDisplayTaskForm( int nIdResource, String strResourceType, HttpServletRequest request, Locale locale, ITask task )
    {
        Map<String, Object> model = new HashMap<>( );

        WorkflowConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );
        model.put( MARK_CONFIG, config );

        String strMessage = request.getParameter( PARAMETER_REPLY_VALUE + "_" + task.getId( ) );

        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        model.put( MARK_LOCALE, AdminUserService.getLocale( request ).getLanguage( ) );
        model.put( MARK_MESSAGE, strMessage );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_REPLY_FORM, locale, model );

        return template.getHtml( );
    }

    @Override
    public String getDisplayConfigForm( HttpServletRequest request, Locale locale, ITask task )
    {
        Map<String, Object> model = new HashMap<>( );

        WorkflowConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );
        model.put( MARK_CONFIG, config );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_REPLY_CONFIG, locale, model );

        return template.getHtml( );
    }

    @Override
    public String getDisplayTaskInformation( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        WorkflowConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );

        String strInfo = "";
        if ( config != null )
            strInfo = String.valueOf( config.getId( ) );

        IExampleTaskService workflowService = SpringContextService.getBean( ExampleTaskService.BEAN_SERVICE );
        Workflow workflow = workflowService.find( task.getId( ) );

        if ( workflow != null )
            strInfo += " : " + workflow.getMessage( );

        return strInfo;
    }

    @Override
    public String getTaskInformationXml( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        throw new UnsupportedOperationException( "Not supported yet." ); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String doValidateTask( int nIdResource, String strResourceType, HttpServletRequest request, Locale locale, ITask task )
    {
        String strError = WorkflowUtils.EMPTY_STRING;
        String strReplyMessageValue = request.getParameter( PARAMETER_REPLY_VALUE + "_" + task.getId( ) );
        WorkflowConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );

        if ( config == null )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_NO_CONFIGURATION_FOR_TASK_REPLY, AdminMessage.TYPE_STOP );
        }

        if ( config.isMandatory( ) && ( ( strReplyMessageValue == null ) || strReplyMessageValue.trim( ).equals( WorkflowUtils.EMPTY_STRING ) ) )
        {
            strError = String.valueOf( config.getId( ) );
        }

        if ( StringUtils.isNotBlank( strError ) )
        {
            Object [ ] tabRequiredFields = {
                strError
            };

            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELD, tabRequiredFields, AdminMessage.TYPE_STOP );
        }

        return null;
    }
}
