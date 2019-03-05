#!groovy
package com.els.itops.services

class DevOpsConf implements Serializable {

    private static final long serialVersionUID = 1L

    /**
     * Common properties
     */

    public final static String NEXUS_THIRD_PARTY = 'https://nexus.lefebvre-sarrut.eu/repository/thirdparty/'
    public final static String ITOPS_SETTINGS_FILE = 'com/els/pipeline/itops/settings.xml'
    public final static String MAVEN_INSTALLATION = 'maven-3.6.0'



    /**
     * itops Project
     */



    public final static String itops_SLACK_TOKEN = 'EBUvAvx55kUxjL8ZFzIclnPs'


}
