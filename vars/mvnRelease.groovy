import com.pwc.dfy.services.Utilities
import com.pwc.dfy.services.DevOpsConf

/**
 * Release Maven project
 * List of Jenkins job parameters who use this class
 * @param deployToNexus : boolean (true or false) (if true the released artifact will be deployed to nexus)
 * @param increment : (Major or minor) if null the Patch version will be incrementing (1.1.X) if major (x.1.1) if minor (1.x.1)
 * @param : branch : the project branch will be released (default master)
 * @return
 */

def call(Map parameters = [:]) {

    def utilities = new Utilities()
    def mvn = tool(DevOpsConf.MAVEN_INSTALLATION)
    def deployToNexus = parameters.get('deployToNexus')
    def version = utilities.getMSPOMVersion()
    def msTimeStamp = utilities.getTimeStamp("yyyyMMdd")
    def nextVersion
    def versionToIncrement = "${increment}"


            /**
             * Maven verify to check the dependencies
             */

            sh "${mvn}/bin/mvn clean verify "

            //Modify pom version

                utilities.modifyVersionBuildFiles("${version}")


            //tag project and checkout the same tag
            sh "git tag -a ${version}.$BUILD_NUMBER-$msTimeStamp -m 'tagging release Version ${version}.$BUILD_NUMBER-$msTimeStamp'"
            sh "git checkout ${version}.$BUILD_NUMBER-$msTimeStamp"

            //maven deploy to Nexus or Not
            if("${deployToNexus}" == "yes"){

                sh "${mvn}/bin/mvn clean deploy"

            }

            //Push tag to git
            sh "git remote -v"
            sh "git push origin ${version}.$BUILD_NUMBER-$msTimeStamp"

            //modify next version
            if ( versionToIncrement == "Major" ){
                nextVersion = utilities.incrementMajor(version)
            }
            if ( versionToIncrement == "Minor" ){
                nextVersion = utilities.incrementMinor(version)
            }
            if ( versionToIncrement != "Minor" && versionToIncrement != "Major" ){
                nextVersion = utilities.incrementPatch(version)
            }

           //get master branch
            sh "git checkout ${msbranch}"

            utilities.modifyVersionBuildFiles("${nextVersion}-SNAPSHOT")


            //Push the nextVersion in master
            sh "git add . && git commit -m 'Make next development iteration ${nextVersion}'"
            sh "git push origin ${msbranch}"

}
