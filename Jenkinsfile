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
                githubNotify account: 'mohand-djouadi',
                             repo: 'biblio-api',
                             sha: "${env.GIT_COMMIT}",
                             status: 'SUCCESS',
                             description: 'pipeline completed',
                             context: 'jenkins cicd',
                             credentialsId: 'github_token_global'
            }
        }
        failure {
            script {
                githubNotify account: 'mohand-djouadi',
                             repo: 'biblio-api',
                             sha: "${env.GIT_COMMIT}",
                             status: 'FAILURE',
                             description: 'pipeline failed',
                             context: 'jenkins/cicd',
                             credentialsId: 'github_token_global'
            }
        }
    }
}