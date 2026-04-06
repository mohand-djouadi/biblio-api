pipeline {
    agent any

    triggers {
        githubPush()
    }

    options {
        skipDefaultCheckout(true)
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage ('Checkout') {
            steps {
                def scmVars = checkout scm
                env.GIT_COMMIT_REV = scmVars.GIT_COMMIT
                sh 'git log --oneline -5'
                echo "${env.GIT_COMMIT}"
                script {
                    githubNotify(
                        credentialsId: 'github_token',
                        account: 'mohand-djouadi',
                        repo: 'biblio-api',
                        sha: "${env.GIT_COMMIT}",
                        status: 'SUCCESS',
                        context: 'jenkins/cicd'
                    )
                }
            }
        }
        stage ('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
    post {
        success {
            script {
                githubNotify(
                    credentialsId: 'github_token',
                    account: 'mohand-djouadi',
                    repo: 'biblio-api',
                    sha: "${env.GIT_COMMIT}",
                    status: 'SUCCESS',
                    context: 'jenkins/cicd'
                )
            }
        }
        failure {
            script {
                githubNotify(
                    credentialsId: 'github_token',
                    account: 'mohand-djouadi',
                    repo: 'biblio-api',
                    sha: "${env.GIT_COMMIT}",
                    status: 'FAILURE',
                    context: 'jenkins/cicd'
                )
            }
        }
    }
}