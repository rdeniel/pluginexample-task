
--
-- Structure for table pluginexamplethetask_workflow
--

DROP TABLE IF EXISTS pluginexamplethetask_workflow;
CREATE TABLE pluginexamplethetask_workflow (
id_workflow int AUTO_INCREMENT,
id_task INT DEFAULT 0 NOT NULL,
message varchar(255) default '' NOT NULL,
PRIMARY KEY (id_workflow)
);

--
-- Structure for table pluginexamplethetask_workflowconfig
--

DROP TABLE IF EXISTS pluginexamplethetask_workflowconfig;
CREATE TABLE pluginexamplethetask_workflowconfig (
id_workflowconfig int AUTO_INCREMENT,
default_message varchar(255) default '',
PRIMARY KEY (id_workflowconfig)
);
