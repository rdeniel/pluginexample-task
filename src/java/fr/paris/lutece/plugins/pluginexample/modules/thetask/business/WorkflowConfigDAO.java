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

package fr.paris.lutece.plugins.pluginexample.modules.thetask.business;

import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfigDAO;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for WorkflowConfig objects
 */
public final class WorkflowConfigDAO implements ITaskConfigDAO<WorkflowConfig>
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_workflowconfig, default_message FROM pluginexamplethetask_workflowconfig WHERE id_workflowconfig = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO pluginexamplethetask_workflowconfig ( default_message ) VALUES ( ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM pluginexamplethetask_workflowconfig WHERE id_workflowconfig = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE pluginexamplethetask_workflowconfig SET id_workflowconfig = ?, default_message = ? WHERE id_workflowconfig = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_workflowconfig, default_message FROM pluginexamplethetask_workflowconfig";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_workflowconfig FROM pluginexamplethetask_workflowconfig";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( WorkflowConfig workflowconfig)
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, WorkflowUtils.getPlugin( ) ) )
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++ , workflowconfig.getDefaultMessage( ) );
            
            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) ) 
            {
                workflowconfig.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public WorkflowConfig load( int nKey )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, WorkflowUtils.getPlugin( ) ) )
        {
	        daoUtil.setInt( 1 , nKey );
	        daoUtil.executeQuery( );
	        WorkflowConfig workflowconfig = null;
	
	        if ( daoUtil.next( ) )
	        {
	            workflowconfig = new WorkflowConfig();
	            int nIndex = 1;
	            
	            workflowconfig.setId( daoUtil.getInt( nIndex++ ) );
	            workflowconfig.setDefaultMessage( daoUtil.getString( nIndex ) );            
	        }
	
	        daoUtil.free( );
	        return workflowconfig;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, WorkflowUtils.getPlugin( ) ) )
        {
	        daoUtil.setInt( 1 , nKey );
	        daoUtil.executeUpdate( );
	        daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( WorkflowConfig workflowconfig )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, WorkflowUtils.getPlugin( ) ) )
        {
	        int nIndex = 1;
	        
	        daoUtil.setInt( nIndex++ , workflowconfig.getId( ) );
	        daoUtil.setString( nIndex++ , workflowconfig.getDefaultMessage( ) );
	        daoUtil.setInt( nIndex , workflowconfig.getId( ) );
	
	        daoUtil.executeUpdate( );
	        daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    public List<WorkflowConfig> selectWorkflowConfigsList( )
    {
        List<WorkflowConfig> workflowconfigList = new ArrayList<>(  );
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, WorkflowUtils.getPlugin( ) ) )
        {
	        daoUtil.executeQuery(  );
	
	        while ( daoUtil.next(  ) )
	        {
	            WorkflowConfig workflowconfig = new WorkflowConfig(  );
	            int nIndex = 1;
	            
	            workflowconfig.setId( daoUtil.getInt( nIndex++ ) );
	            workflowconfig.setDefaultMessage( daoUtil.getString( nIndex ) );            
	
	            workflowconfigList.add( workflowconfig );
	        }
	
	        daoUtil.free( );
	        return workflowconfigList;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    public List<Integer> selectIdWorkflowConfigsList( )
    {
        List<Integer> workflowconfigList = new ArrayList<>( );
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, WorkflowUtils.getPlugin( ) ) )
        {
	        daoUtil.executeQuery(  );
	
	        while ( daoUtil.next(  ) )
	        {
	            workflowconfigList.add( daoUtil.getInt( 1 ) );
	        }
	
	        daoUtil.free( );
	        return workflowconfigList;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    public ReferenceList selectWorkflowConfigsReferenceList( )
    {
        ReferenceList workflowconfigList = new ReferenceList();
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, WorkflowUtils.getPlugin( ) ) )
        {
	        daoUtil.executeQuery(  );
	
	        while ( daoUtil.next(  ) )
	        {
	            workflowconfigList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
	        }
	
	        daoUtil.free( );
	        return workflowconfigList;
    	}
    }
}
