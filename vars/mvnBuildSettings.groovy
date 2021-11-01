import com.pwc.dfy.services.DevOpsConf

/**
 * build project with settings.xml
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    def param = parameters.get('param')

    try {

                //Settings-security
//                def filesecurityPath = DevOpsConf.DFY_SETTINGS_SECURITY_FILE
//                def contentSecurityFile = libraryResource filesecurityPath
//                def pathSecurityManifest = './home/jenkins/.m2/' + 'settings-security.xml'
//                writeFile file: pathSecurityManifest, text: contentSecurityFile

                //settings.xml
                def filePath = DevOpsConf.ITOPS_SETTINGS_FILE
                def contentfile = libraryResource filePath
                def pathManifest = './home/jenkins/.m2/' + 'settings.xml'
                writeFile file: pathManifest, text: contentfile

                sh "mvn ${param} -s ${pathManifest}"


    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}