import com.pwc.dfy.services.Utilities

/**
 * Clone git project
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def utilities = new Utilities()
    utilities.deployArtifactToNexusWithPackaging("com.file.nexus", tok, "1.0.${BUILD_NUMBER}", "jar", "thirdparty", "http://localhost:8081/repository/maven-snapshots/", "myfile.jar")


}