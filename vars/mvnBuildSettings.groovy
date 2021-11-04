import com.pwc.dfy.services.DevOpsConf

/**
 * build project with settings.xml
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def param = parameters.get('param')
    def mvn = tool(DevOpsConf.MAVEN_INSTALLATION)


    try {


                //settings.xml
                def filePath = DevOpsConf.ITOPS_SETTINGS_FILE
                def contentfile = libraryResource filePath
                def pathManifest = './home/jenkins/.m2/' + 'settings.xml'
                writeFile file: pathManifest, text: contentfile

               // sh "${mvn}/bin/mvn clean ${param} -s ${pathManifest}"
                sh "${mvn}/bin/mvn clean install -s ${pathManifest}"


    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}