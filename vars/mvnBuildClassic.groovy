import com.els.itops.services.DevOpsConf


/**
 * Classic Maven build
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def mvn = tool(DevOpsConf.MAVEN_INSTALLATION)


            try {

                sh "${mvn}/bin/mvn clean install "
            } catch (err) {
                currentBuild.result = 'FAILED'
                throw err
            }
}