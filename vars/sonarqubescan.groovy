import com.pwc.dfy.services.DevOpsConf

/**
 * clone git project with git url in parameters
 * @param gitUrl : the git link to clone into the job(this param must exist in the pipeline script)
 * @return
 */
def call(Map parameters = [:]) {


    def mvn = tool(DevOpsConf.MAVEN_INSTALLATION)
    withSonarQubeEnv("${DevOpsConf.SONARQUBE_SERVER}") {
        echo 'pom'
        sh "${mvn}/bin/mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
    }
}