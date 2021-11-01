import com.pwc.dfy.services.Utilities

/**
 * Clone git project
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def utilities = new Utilities()
    utilities.deployArtifactToNexusWithPackaging("com.file.nexus", "tok", "1.0.${BUILD_NUMBER}", "zip", "thirdparty", "http://172.17.0.3:8081/content/repositories/maven-snapshots/", "myfile.zip")


}