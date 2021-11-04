import com.pwc.dfy.services.Utilities

/**
 * Clone git project
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def utilities = new Utilities()
    def mvn = tool(DevOpsConf.MAVEN_INSTALLATION)
    def nexus = parameters.get('nexus')

    sh "${mvn}/bin/mvn clean deploy  -Dnexus=${nexus}"
    /*sh """
           ${mvn}/bin/mvn deploy:deploy-file \
          -DgroupId="com.file.nexus" \
          -DartifactId="jenkins" \
          -Dversion="1.0.${BUILD_NUMBER}" \
          -DgeneratePom=false \
          -DrepositoryId="maven-snapshots" \
          -Durl="${nexus}/content/repositories/maven-snapshots/" \
          -Dfile="myfile.zip"
      """*/
    //utilities.deployArtifactToNexusWithPackaging("com.file.nexus", "tok", "1.0.${BUILD_NUMBER}", "zip", "thirdparty", "http://172.17.0.3:8081/content/repositories/maven-snapshots/", "myfile.zip")


}