/**
 * Classic Maven build
 * @param no parameter
 * @return
 */
def call(Map parameters = [:]) {

   


            try {

                sh "echo ça Marche !!!!!!! "
            } catch (err) {
                currentBuild.result = 'FAILED'
                throw err
            }
}
