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
 * SUBSTITUTE GOODS OR SERVICES LOSS OF USE, DATA, OR PROFITS OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.pluginexample.modules.thetask.web;

import java.util.List;
import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.portal.web.LocalVariables;
import fr.paris.lutece.portal.service.security.SecurityTokenService;
import java.io.IOException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import fr.paris.lutece.plugins.workflow.modules.example.business.Workflow;
import fr.paris.lutece.plugins.workflow.modules.example.business.WorkflowHome;
import fr.paris.lutece.plugins.workflow.modules.example.web.WorkflowJspBean;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.admin.AdminAuthenticationService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
/**
 * This is the business class test for the object Workflow
 */
public class WorkflowJspBeanTest extends LuteceTestCase
{
    private static final String MESSAGE1 = "Message1";
    private static final String MESSAGE2 = "Message2";

public void testJspBeans(  ) throws AccessDeniedException
	{	
     	MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockServletConfig config = new MockServletConfig();

		//display admin Workflow management JSP
		WorkflowJspBean jspbean = new WorkflowJspBean();
		String html = jspbean.getManageWorkflows( request );
		assertNotNull(html);

		//display admin Workflow creation JSP
		html = jspbean.getCreateWorkflow( request );
		assertNotNull(html);

		//action create Workflow
		request = new MockHttpServletRequest();

        request.addParameter( "message" , MESSAGE1 );
		request.addParameter("action","createWorkflow");
        request.addParameter( "token", SecurityTokenService.getInstance( ).getToken( request, "createWorkflow" ));
		request.setMethod( "POST" );
		response = new MockHttpServletResponse( );
		AdminUser adminUser = new AdminUser( );
		adminUser.setAccessCode( "admin" );

		try 
		{
			AdminAuthenticationService.getInstance( ).registerUser(request, adminUser);
			html = jspbean.processController( request, response ); 
			
			// MockResponse object does not redirect, result is always null
			assertNull( html );
		}
		catch (AccessDeniedException e)
		{
			fail("access denied");
		}
		catch (UserNotSignedException e) 
		{
			fail("user not signed in");
		}

		//display modify Workflow JSP
		request = new MockHttpServletRequest();
        request.addParameter( "message" , MESSAGE1 );
		List<Integer> listIds = WorkflowHome.getIdWorkflowsList();
        assertTrue( !listIds.isEmpty( ) );
        request.addParameter( "id", String.valueOf( listIds.get( 0 ) ) );
		jspbean = new WorkflowJspBean();

		assertNotNull( jspbean.getModifyWorkflow( request ) );

		//action modify Workflow
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
        request.addParameter( "message" , MESSAGE2 );
		request.setRequestURI("jsp/admin/plugins/example/ManageWorkflows.jsp");
		//important pour que MVCController sache quelle action effectuer, sinon, il redirigera vers createWorkflow, qui est l'action par défaut
		request.addParameter("action","modifyWorkflow");
		request.addParameter( "token", SecurityTokenService.getInstance( ).getToken( request, "modifyWorkflow" ));
		adminUser = new AdminUser();
		adminUser.setAccessCode("admin");

		try 
		{
			AdminAuthenticationService.getInstance( ).registerUser(request, adminUser);
			html = jspbean.processController( request, response ); 

			// MockResponse object does not redirect, result is always null
			assertNull( html );
		}
		catch (AccessDeniedException e)
		{
			fail("access denied");
		}
		catch (UserNotSignedException e) 
		{
			fail("user not signed in");
		}
		
		//get remove Workflow
		request = new MockHttpServletRequest();
        //request.setRequestURI("jsp/admin/plugins/example/ManageWorkflows.jsp");
        request.addParameter( "id", String.valueOf( listIds.get( 0 ) ) );
		jspbean = new WorkflowJspBean();
		request.addParameter("action","confirmRemoveWorkflow");
		assertNotNull( jspbean.getModifyWorkflow( request ) );
				
		//do remove Workflow
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setRequestURI("jsp/admin/plugins/example/ManageWorkflowts.jsp");
		//important pour que MVCController sache quelle action effectuer, sinon, il redirigera vers createWorkflow, qui est l'action par défaut
		request.addParameter("action","removeWorkflow");
		request.addParameter( "token", SecurityTokenService.getInstance( ).getToken( request, "removeWorkflow" ));
		request.addParameter( "id", String.valueOf( listIds.get( 0 ) ) );
		request.setMethod("POST");
		adminUser = new AdminUser();
		adminUser.setAccessCode("admin");

		try 
		{
			AdminAuthenticationService.getInstance( ).registerUser(request, adminUser);
			html = jspbean.processController( request, response ); 

			// MockResponse object does not redirect, result is always null
			assertNull( html );
		}
		catch (AccessDeniedException e)
		{
			fail("access denied");
		}
		catch (UserNotSignedException e) 
		{
			fail("user not signed in");
		}	
     
     }
}
