/**
 * clone git project with git url in parameters
 * @param gitUrl
 * @return
 */
def call(Map parameters = [:]) {

    def gitUrl = parameters.get('gitUrl')

    stage ("Clone project") {
        //$branch pram exist in the job param

            git branch: "$branch", url: "$gitUrl"

    }
}