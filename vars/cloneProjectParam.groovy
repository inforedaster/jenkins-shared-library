import com.pwc.dfy.services.DevOpsConf

/**
 * clone git project with git url in parameters
 * @param gitUrl : the git link to clone into the job(this param must exist in the pipeline script)
 * @return
 */
def call(Map parameters = [:]) {

    //def gitUrl = parameters.get('gitUrl')
    def git = tool("Default")

        //$branch pram is a job param

            //git branch: "$branch", url: "$gitUrl"
    sh "${git} clone https://github.com/inforedaster/jenkins-shared-library.git "

}