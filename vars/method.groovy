import com.pwc.dfy.services.*

/**
 * clone git project with git url in parameters
 * @param gitUrl : the git link to clone into the job(this param must exist in the pipeline script)
 * @return
 */
def call(Map parameters = [:]) {

    def nom = parameters.get('nom')
    def utilities = new Utilities()
    utilities.calling("${nom}", "${prenom}")
}
