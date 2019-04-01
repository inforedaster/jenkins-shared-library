import com.els.itops.services.Utilities

/**
 * Clone git project
 * @param parameters
 * @return
 */
def call(Map parameters = [:]) {

    //${branch} is a job parameter
    def utilities = new Utilities()
    def projectname = utilities.getBuildInfo().artifactId

        def userRemoteConfigs = scm.userRemoteConfigs
        if(env.gitlabSourceRepoURL != null) {
            userRemoteConfigs = [[
                         refspec: "+refs/heads/${branch}:refs/remotes/origin/${branch}",
                         url: "${env.gitlabSourceRepoURL}"]]
        }
        checkout([$class: 'GitSCM',
                  branches: [[name: "origin/${branch}"]],
                  doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
                  extensions: scm.extensions,
                  userRemoteConfigs: userRemoteConfigs])

        env.JOB_LASTCOMMIT = sh(script: 'git rev-parse refs/remotes/origin/'+ "$branch" +'^{commit}', returnStdout: true).toString().trim()
        println 'lastcommit:'+env.JOB_LASTCOMMIT+':'

}