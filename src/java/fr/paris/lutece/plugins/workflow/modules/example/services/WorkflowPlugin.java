package fr.paris.lutece.plugins.workflow.modules.example.services;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginDefaultImplementation;
import fr.paris.lutece.portal.service.plugin.PluginService;

public class WorkflowPlugin extends PluginDefaultImplementation{
	public static final String PLUGIN_NAME = "pluginexample-thetask";
    public static final String BEAN_TRANSACTION_MANAGER = PLUGIN_NAME + ".transactionManager";

    /**
     * Get the plugin
     * 
     * @return the plugin
     */
    public static Plugin getPlugin( )
    {
        return PluginService.getPlugin( PLUGIN_NAME );
    }
}
