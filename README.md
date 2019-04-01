# Jenkins Sharedlib template

Template for the Jenkins Shared Libraries

## Getting Started

This template will get you a copy of the project up and running on your Jenkins instance for development, testing and deployment purposes.

### Prerequisites

Jenkins 2.0 and more, using Declarative Jenkins pipeline.


### How to use

#### Declaration of the Shared library

```
@Library('sharedlib-declare') _```

```

#### Core of Jenkinsfile with the Shared library

```
@Library('sharedlib-declare') _

pipeline {
    agent { label 'node' }
    tools {
        jdk 'jdk-8'
    }

    stages {
          stage('Preparation') {
            steps {
                git 'https://bitbucket.org/elsgestion/maven-example.git'
            }
        }

        stage('build') {
            steps {
                mvnBuildClassic()
            }
        }
         stage('release') {
            steps {
                mvnRelease()
            }
        }
    }
}
```

#### different classes of the Shared library

 * cloneProject : Used into a jenkinsfile to clone git project and keep the git refrence into the job workspace.
 * cloneProjectParam : Clone git project whene you use the script pipeline into a Job.

##### Example
        ```
        @Library('sharedlib-declare') _

          pipeline {
              agent { label 'node' }

              stages {
                    stage('Git clone') {
                      steps {
                           cloneProjectParam : ("git@bitbucket.org:elsgestion/git-code-source.git")                               }
                  }

                  stage('build') {
                      steps {
                          // Building class
                      }
                  }
              }
          }

        ```

 * mvnBuildClassic : build a simple maven project
 * mvnRelease ; a simple way to release maven project

 ### contribution

The project is open to all ELS members to update or improve this template