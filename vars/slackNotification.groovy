import com.pwc.dfy.services.DevOpsConf

/**
 * clone git project with git url in parameters
 * @param gitUrl : the git link to clone into the job(this param must exist in the pipeline script)
 * @return
 */
def call(Map parameters = [:]) {

    def utilities = new Utilities()
    def token = DevOpsConf.IMPULSE_SLACK_TOKEN
    def message = parameters.get('message')
    def slackChannel = parameters.get('slackChannel')

    utilities.slackMSNotification("${slackChannel}", "${message}")

}