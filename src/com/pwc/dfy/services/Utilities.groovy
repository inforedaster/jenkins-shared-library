#!groovy
package com.pwc.dfy.services


/**
 * Getting a pom.xml version
 * @return pom version
 */
def getPOMVersion() {
    def matcher = readFile('pom.xml') =~ '<revision>(.+)-\\$\\{BuildNumber\\}</revision>'
    matcher ? matcher[0][1] : null
}



/**
 * Clean docker image in Jenkins
 * @param imagename
 * @return
 */
def cleanDockerimages(String imagename) {

    def dockerImagePath = DevOpsConf.DOCKER_REGISTRY_URL + "/sgdbf-dod/"
    sh 'docker rmi $(docker images -aq --filter ' + "reference=$dockerImagePath$imagename*" + ") || true"
}


/**
 * Modify pom.xml
 * @param version
 * @return
 */
def modifyVersionBuildFiles(String version) {

    def mvn = tool(DevOpsConf.MAVEN_INSTALLATION)
    sh "${mvn}/bin/mvn -B org.codehaus.mojo:versions-maven-plugin:2.4:set -DgenerateBackupPoms=false -DnewVersion=${version}"

}

/**
 * Get Pom version from maven pom file
 * @return
 */
def getMSPOMVersion() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)-SNAPSHOT</version>'
    matcher ? matcher[0][1] : null
}


/**
 * Deploy artifact to Nexus
 * @param groupId
 * @param artifactId
 * @param artifactVersion
 * @param repositoryId
 * @param repositoryUrl
 * @param fileName
 * @return
 */
def deployArtifactToNexus(String groupId, String artifactId, String artifactVersion, String repositoryId, String repositoryUrl, String fileName){

    sh """
           mvn deploy:deploy-file \
          -DgroupId=${groupId} \
          -DartifactId=${artifactId}\
          -Dversion=${artifactVersion} \
          -DgeneratePom=false \
          -DrepositoryId=${repositoryId} \
          -Durl=${repositoryUrl} \
          -Dfile=${fileName}
      """
}
/**
 * Deploy artifact to Nexus with classifer
 * @param groupId
 * @param artifactId
 * @param artifactVersion
 * @param repositoryId
 * @param repositoryUrl
 * @param fileName
 * @param classifier
 * @param files
 * @param types
 * @param classifiers
 * @return
 */

def deployArtifactToNexusWithClassifier(String groupId, String artifactId, String artifactVersion, String repositoryId, String repositoryUrl, String fileName,String classifier, String files, String types, String classifiers){

    sh """
           mvn deploy:deploy-file \
          -DgroupId=${groupId} \
          -DartifactId=${artifactId}\
          -Dversion=${artifactVersion} \
          -DgeneratePom=false \
          -DrepositoryId=${repositoryId} \
          -Durl=${repositoryUrl} \
          -Dfile=${fileName} \
          -Dclassifier=${classifier} \\
          -Dfiles=${files} \\
          -Dtypes=${types} \\
          -Dclassifiers=${classifiers}
      """
}

/**
 * With pakaging param
 * @param groupId
 * @param artifactId
 * @param artifactVersion
 * @param packaging
 * @param repositoryId
 * @param repositoryUrl
 * @param fileName
 * @return
 */
def deployArtifactToNexusWithPackaging(String groupId, String artifactId, String artifactVersion, String packaging, String repositoryId, String repositoryUrl, String fileName){

    sh """
           mvn deploy:deploy-file \
          -DgroupId=${groupId} \
          -DartifactId=${artifactId}\
          -Dversion=${artifactVersion} \
          -DgeneratePom=false \
          -Dpackaging=${packaging} \\
          -DrepositoryId=${repositoryId} \
          -Durl=${repositoryUrl} \
          -Dfile=${fileName}
      """
}
/**
 * Get pom version with regex param
 * @param name
 * @param regex
 * @return
 */

def getVersionFromPom(String name, String regex){

    def matcher = readFile('pom.xml') =~ "<${name}>${regex}</${name}>"
    matcher ? matcher[0][1] : null
}

/**
 * Increment the major
 * @param version
 * @return
 */
def incrementMajor(String version){

    def major = getVersion(version, 1)
    def nextVersion = (major.toInteger()+1)+'.0.0'
    return nextVersion
}

/**
 * Increment the minor
 * @param version
 * @return
 */
def incrementMinor(String version){

    def major = getVersion(version, 1)
    def minor = getVersion(version, 2)
    def nextVersion = major+'.'+(minor.toInteger()+1)+'.0'
    return nextVersion
}

/**
 * Increment patch
 * @param version
 * @return
 */
def incrementPatch(String version){

    def major = getVersion(version, 1)
    def minor = getVersion(version, 2)
    def patch = getVersion(version, 3)
    def nextVersion = major+'.'+minor+'.'+ (patch.toInteger() + 1)
    return nextVersion
}


/**
 * Slack notification
 * @param Deployment_slack_channel
 * @param token
 * @return
 */
def slackMSNotification(String Deployment_slack_channel, String message) {


        slackSend channel: "#$Deployment_slack_channel", color: 'good', message: "$message"

}

def calling(String nom, String prenom) {

    bat  ' mon nom est : ' + "${nom}" + ' et mon prenom est ' + "${prenom}"

}
