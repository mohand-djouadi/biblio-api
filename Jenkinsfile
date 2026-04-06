pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage ('Checkout') {
            steps {
                checkout scm
                sh 'git log --oneline -5'
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