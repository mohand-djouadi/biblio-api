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
            githubNotify account: 'mohand-djouadi',
                         repo: 'biblio-api',
                         status: 'SUCCESS',
                         description: 'pipeline completed',
                         context: 'jenkins cicd',
                         credentialsId: 'github_token'
        }
        failure {
            githubNotify account: 'mohand-djouadi',
                         repo: 'biblio-api',
                         status: 'FAILURE',
                         description: 'pipeline failed',
                         context: 'jenkins cicd',
                         credentialsId: 'github_token'
        }
    }
}